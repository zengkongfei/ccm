/**
 * 
 */
package com.ccm.api.service.order.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.common.exception.BizException;
import com.ccm.api.model.constant.OWSConstant;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterPackage;
import com.ccm.api.model.order.MasterRate;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.service.order.OrderBusinessVerifyManager;
import com.ccm.api.service.order.OrderManager;
import com.ccm.api.service.ratePlan.PriceCalcManager;
import com.ccm.api.util.AmountUtil;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.DateUtils;
import com.ccm.reservation.Amount;
import com.ccm.reservation.PackageElement;
import com.ccm.reservation.Rate;
import com.ccm.reservation.RateList;
import com.ccm.reservation.RoomRate;
import com.ccm.reservation.RoomStay;

/**
 * @author Jenny
 * 
 */
@Service("orderBusinessVerifyManager")
public class OrderBusinessVerifyManagerImpl implements OrderBusinessVerifyManager {

	private Log log = LogFactory.getLog("reservation");

	@Resource
	private PriceCalcManager priceCalcManager;
	@Resource
	private OrderManager orderManager;

	/**
	 * OWS订单验证房价并获取房价与包价
	 */
	public double verifyRoomRate(PriceCalc pc, Master m, RoomStay rs, int validRoomPrice) {
		double totalAmount = 0;
		RoomRate rr = rs.getRoomRates().getRoomRate().get(0);
		if (!StringUtils.hasText(m.getMasterId()) || m.getGstno().intValue() != m.getOgstno() || m.getChild().intValue() != m.getOchild() || m.getArr().compareTo(m.getOcheckinDate()) != 0 || m.getDep().compareTo(m.getOcheckoutDate()) != 0 || !m.getRatePlanCode().equals(m.getOratePlanCode()) || !m.getType().equals(m.getOtype()) || !m.getChainCode().equals(m.getOchainCode()) || !m.getHotelCode().equals(m.getOhotelCode()) || !m.getChannel().equals(m.getOchannel())) {
			// 验证房价是否可用
			List<PriceCalc> pcList = priceCalcManager.getRoomPriceOws(pc);
			if (pcList == null) {
				throw new BizException("4");
			}

			m.setCurrencyCode(OWSConstant.currencyCode);
			// 每日房价详情与总房价
			List<MasterRate> mrList = new ArrayList<MasterRate>();
			Map<String,PriceCalc> priceMap = new HashMap<String,PriceCalc>();
			for (int i = 0; i < pcList.size(); i++) {
				priceMap.put(pcList.get(i).getDate(),pcList.get(i));
			}
			// 渠道设置为校验房价或渠道房价时
			if ((validRoomPrice == 1 || validRoomPrice == 2) && rr.getRates() != null) {
				List<Rate> rList = rr.getRates().getRate();
				for (int i = 0; i < rList.size(); i++) {
					Rate r = rList.get(i);
					Date date = DateUtils.xmlDate2Date(r.getEffectiveDate());
					// switch 传过来的房价
					BigDecimal value = new BigDecimal(r.getBase().getValue());
					//switch 传过来的 每日优惠
					BigDecimal discount = new BigDecimal("0");
					if(r.getDiscount()!=null){
						discount = new BigDecimal(r.getDiscount().getValue());
						discount = discount.setScale(2, BigDecimal.ROUND_FLOOR); 
					}
					MasterRate mr = new MasterRate();
					//需要优惠 
					if(!m.getIsDiscount()){
						discount = new BigDecimal("0");
					}
					String dateStr = DateUtil.convertDateToString(date);
					
					PriceCalc priceCalc = priceMap.get(dateStr);
					log.info(priceCalc);
					/**ccm 税后价格*/
					BigDecimal afterccmPrice = new BigDecimal("0");
					/**ccm 税前价格*/
					BigDecimal beforeccmPrice = new BigDecimal("0");
					/**ccm switch税前价格*/
					BigDecimal switchbeforeccmPrice = new BigDecimal("0");
					BigDecimal switchafterccmPrice = new BigDecimal("0");
					/**税前discount*/
					BigDecimal discountbeforeccm = new BigDecimal("0");
					/**不启用税费，税后价格=ccm房价*/
					if(!priceCalc.getUseAmountAfterTax()){
						discountbeforeccm = discount;
						beforeccmPrice = priceCalc.getAmount().subtract(discountbeforeccm);;
						afterccmPrice = priceCalc.getAmountAfterTax();
						switchbeforeccmPrice = value.subtract(discountbeforeccm);
						switchafterccmPrice = value.subtract(discountbeforeccm);
					}else{
						BigDecimal taxFeeN = priceCalc.getTaxFeeN();//固定税费  每间房每晚
						BigDecimal taxFeeS = priceCalc.getTaxFeeS();//固定税费 每间房每次入住
						BigDecimal taxFeePS= priceCalc.getTaxFeePS();//固定税费 每人每次入住
						BigDecimal taxFeePN= priceCalc.getTaxFeePN();//固定税费 每人每晚
						/**taxRateN  这个费用为税费 */
						BigDecimal taxRateN = priceCalc.getTaxRateN();//税率 每间房每晚
						BigDecimal taxRateS = priceCalc.getTaxRateS();//税率 每间房每次入住
						BigDecimal taxRatePS= priceCalc.getTaxRatePS();//税率 每人每次入住
						BigDecimal taxRatePN= priceCalc.getTaxRatePN();//税率 每人每晚
						
						BigDecimal numberOfUnits= new BigDecimal(pc.getNumberOfUnits());//税率 每人每晚
						/**总税率*/
						BigDecimal taxRateTotal= taxRateN.add(taxRateS).add(taxRatePS.multiply(numberOfUnits)).add(taxRatePN.multiply(numberOfUnits));
						/**-固定税费总和*/
						BigDecimal taxFeeTotal= taxFeeN.add(taxFeeS).add(taxFeePS.multiply(numberOfUnits)).add(taxFeePN.multiply(numberOfUnits));
						taxFeeTotal = taxFeeTotal.setScale(2, BigDecimal.ROUND_FLOOR); 
						/**discount > 0 */
						if(discount.compareTo(BigDecimal.ZERO)==1){
							discountbeforeccm = AmountUtil.convert2ByFloorMode(discount.divide(taxRateTotal.add(new BigDecimal("1")),4));
						}
						beforeccmPrice = priceCalc.getAmount().subtract(discountbeforeccm);
						afterccmPrice = priceCalc.getAmountAfterTax();
						
						/**baseRate-discount=rate 后转换为税前价格   (rate- taxFee) /(1+taxRate) - taxFee  一般情况beforeccmPrice=switchbeforeccmPrice，误差除外*/
						switchbeforeccmPrice =value.subtract(discount).subtract(taxFeeTotal).divide(taxRateTotal.add(new BigDecimal("1")),4);
						switchbeforeccmPrice = switchbeforeccmPrice.setScale(2, BigDecimal.ROUND_FLOOR);
						
						switchafterccmPrice = value.subtract(discount);
						switchafterccmPrice = switchafterccmPrice.setScale(2, BigDecimal.ROUND_FLOOR); 
						
					}
					/**校验房价*/
					if (validRoomPrice == 1) {
						value = AmountUtil.convert2HalfUp(value);
						if (afterccmPrice == null || afterccmPrice.doubleValue() != value.doubleValue()) {
							throw new BizException("32");
						}
					}
				
					/**存储房价快照*/	
					if(validRoomPrice == 1){//校验房价
						totalAmount = totalAmount + AmountUtil.convert2ByFloorMode(beforeccmPrice).doubleValue();
						mr.setSetrate(AmountUtil.convert2ByFloorMode(beforeccmPrice));
						mr.setSetrateAfterTax(AmountUtil.convert2ByFloorMode(afterccmPrice.subtract(discount)));
					}if(validRoomPrice ==2){//使用渠道价格
						totalAmount = totalAmount + AmountUtil.convert2ByFloorMode(switchbeforeccmPrice).doubleValue();
						mr.setSetrate(AmountUtil.convert2ByFloorMode(switchbeforeccmPrice));
						mr.setSetrateAfterTax(AmountUtil.convert2ByFloorMode(switchafterccmPrice));
					}
					
					mr.setRmrate(discount);
					mr.setDate(date);
					mr.setHotelId(m.getHotelId());
					mr.setCby(m.getChannel());
					mr.setChanged(new Date());
					mrList.add(mr);
					if (StringUtils.hasText(r.getBase().getCurrencyCode())) {
						m.setCurrencyCode(r.getBase().getCurrencyCode());
					}
				}
			}
			
			if(validRoomPrice ==3){
				Map<String,BigDecimal> discountMap = new HashMap<String,BigDecimal>();
				//使用ccm房价，设定优惠
				if(rr.getRates() != null){
					List<Rate> rList = rr.getRates().getRate();
					for (int i = 0; i < rList.size(); i++) {
						Rate r = rList.get(i);
						Date date = DateUtils.xmlDate2Date(r.getEffectiveDate());
						String dateStr = DateUtil.convertDateToString(date);
						//switch 传过来的 每日优惠
						BigDecimal discount = new BigDecimal("0");
						if(m.getIsDiscount() && r.getDiscount()!=null){
							discount = new BigDecimal(r.getDiscount().getValue());
							discount = discount.setScale(2, BigDecimal.ROUND_FLOOR); 
							discountMap.put(dateStr, discount);
						}
					}
				}
				RateList rl = new RateList();
				List<Rate> rlist = rl.getRate();
				//计算房价
				for (int i = 0; i < pcList.size(); i++) {
					PriceCalc pcal = pcList.get(i);
					Date date = new Date();
					try {
						date = DateUtil.convertStringToDate(pcal.getDate());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					BigDecimal discount = discountMap.get(pcal.getDate());
					if(discount==null){
						discount = new BigDecimal("0");
					}
					MasterRate mr = new MasterRate();
					
					PriceCalc priceCalc = priceMap.get(pcal.getDate());
					log.info(priceCalc);
					/**ccm 税后价格*/
					BigDecimal afterccmPrice = new BigDecimal("0");
					/**ccm 税前价格*/
					BigDecimal beforeccmPrice = new BigDecimal("0");
					/**税前discount*/
					BigDecimal discountbeforeccm = new BigDecimal("0");
					/**不启用税费，税后价格=ccm房价*/
					if(!priceCalc.getUseAmountAfterTax()){
						discountbeforeccm = discount;
						beforeccmPrice = priceCalc.getAmount().subtract(discountbeforeccm);
						afterccmPrice = priceCalc.getAmountAfterTax();;
					}else{
						BigDecimal taxFeeN = priceCalc.getTaxFeeN();//固定税费  每间房每晚
						BigDecimal taxFeeS = priceCalc.getTaxFeeS();//固定税费 每间房每次入住
						BigDecimal taxFeePS= priceCalc.getTaxFeePS();//固定税费 每人每次入住
						BigDecimal taxFeePN= priceCalc.getTaxFeePN();//固定税费 每人每晚
						/**taxRateN  这个费用为税费 */
						BigDecimal taxRateN = priceCalc.getTaxRateN();//税率 每间房每晚
						BigDecimal taxRateS = priceCalc.getTaxRateS();//税率 每间房每次入住
						BigDecimal taxRatePS= priceCalc.getTaxRatePS();//税率 每人每次入住
						BigDecimal taxRatePN= priceCalc.getTaxRatePN();//税率 每人每晚
						
						BigDecimal numberOfUnits= new BigDecimal(pc.getNumberOfUnits());//税率 每人每晚
						/**总税率*/
						BigDecimal taxRateTotal= taxRateN.add(taxRateS).add(taxRatePS.multiply(numberOfUnits)).add(taxRatePN.multiply(numberOfUnits));
						/**-固定税费总和*/
						BigDecimal taxFeeTotal= taxFeeN.add(taxFeeS).add(taxFeePS.multiply(numberOfUnits)).add(taxFeePN.multiply(numberOfUnits));
						
						/**discount > 0 */
						if(discount.compareTo(BigDecimal.ZERO)==1){
							discountbeforeccm = AmountUtil.convert2ByFloorMode(discount.divide(taxRateTotal.add(new BigDecimal("1")),4));
							discountbeforeccm = discountbeforeccm.setScale(2, BigDecimal.ROUND_FLOOR); 
						}
						beforeccmPrice = priceCalc.getAmount().subtract(discountbeforeccm);
						afterccmPrice = priceCalc.getAmountAfterTax();
					}
				
					totalAmount = totalAmount + AmountUtil.convert2ByFloorMode(beforeccmPrice).doubleValue();
					mr.setSetrate(AmountUtil.convert2ByFloorMode(beforeccmPrice));
					mr.setSetrateAfterTax(AmountUtil.convert2ByFloorMode(afterccmPrice.subtract(discount)));
						
					log.info("每日房价: " + mr);
					Rate rate = new Rate();
					Amount amount = new Amount();
					amount.setValue(afterccmPrice.doubleValue());
					rate.setBase(amount);
					
					Amount discountAmount = new Amount();
					discountAmount.setValue(discount.doubleValue());
					rate.setDiscount(discountAmount);
					rate.setEffectiveDate(DateUtils.dateToXmlDate(date));
					
					rlist.add(rate);
					
					mr.setRmrate(discount);
					mr.setDate(date);
					mr.setHotelId(m.getHotelId());
					mr.setCby(m.getChannel());
					mr.setChanged(new Date());
					mrList.add(mr);
					
				}
				rr.setRates(rl);
			}
			
			
			// 无房价时返回错误信息
			if (totalAmount == 0) {
				throw new BizException("32");
			}
			m.setMrList(mrList);
			m.setSetrate(new BigDecimal(totalAmount));// 房价总和

			// 一房的包价处理
			List<MasterPackage> inPackage = new ArrayList<MasterPackage>();
			if (rs.getPackages() != null) {
				for (PackageElement pe : rs.getPackages().getPackage()) {
					MasterPackage mp = new MasterPackage();
					mp.setPackageCode(pe.getPackageCode());
					mp.setQuantity(pe.getQuantity());
					mp.setIsDynamic(true);
					inPackage.add(mp);
				}
			}
			m.setMpList(inPackage);
			// 获取1间房的动态和1间房的静态包价
			try {
				List<MasterPackage> outPackage = orderManager.getPackageInfo(m, pcList);
				m.setMpList(outPackage);
			} catch (Exception e) {
				log.error(CommonUtil.getExceptionMsg(e, "ccm"));
				throw new BizException("32");
			}
		}// 用于返回房价明细
		else if (StringUtils.hasText(m.getMasterId())) {
			if (rr.getRates() == null || rr.getRates().getRate().isEmpty()) {
				RateList rl = new RateList();
				List<Rate> rlist = rl.getRate();
				List<MasterRate> mrList = orderManager.getMasterRateByOrderNo(m.getMasterId());
				for (int i = 0; i < mrList.size(); i++) {
					Rate r = new Rate();
					BigDecimal aValue = mrList.get(i).getSetrateAfterTax();
					BigDecimal bValue = mrList.get(i).getRmrate();
					BigDecimal cValue = mrList.get(i).getSetrate();
					totalAmount = totalAmount + cValue.doubleValue();
					Amount a = new Amount();
					a.setValue(aValue.add(bValue).doubleValue());
					r.setBase(a);
					r.setEffectiveDate(DateUtils.dateToXmlDate(mrList.get(i).getDate()));
					
					Amount discountAmount = new Amount();
					discountAmount.setValue(bValue.doubleValue());
					r.setDiscount(discountAmount);
					
					rlist.add(r);
				}
				rr.setRates(rl);
			}
			// 一房的包价处理
			List<MasterPackage> inPackage = new ArrayList<MasterPackage>();
			if (rs.getPackages() != null) {
				for (PackageElement pe : rs.getPackages().getPackage()) {
					MasterPackage mp = new MasterPackage();
					mp.setPackageCode(pe.getPackageCode());
					mp.setQuantity(pe.getQuantity());
					mp.setIsDynamic(true);
					inPackage.add(mp);
				}
			}
			m.setMpList(inPackage);
			// 获取1间房的动态和1间房的静态包价
			try {
				m.setMrList(orderManager.getMasterRateByOrderNo(m.getMasterId()));
				List<MasterPackage> outPackage = orderManager.getPackageInfo(m, null);
				m.setMpList(outPackage);
			} catch (Exception e) {
				log.error(CommonUtil.getExceptionMsg(e, "ccm"));
				throw new BizException("32");
			}
		} else {
			totalAmount = m.getSetrate().doubleValue();
		}
		// 用于返回
		Amount a = new Amount();
		a.setValue(totalAmount);
		rs.setTotal(a);
		log.info("总房价 : " + totalAmount);
		return totalAmount;
	}

	/**
	 * WBE订单验证房价并获取房价与包价
	 */
	public void verifyRoomRateWbe(PriceCalc pc, Master master) {
		if (!StringUtils.hasText(master.getMasterId()) || master.getGstno().intValue() != master.getOgstno() || master.getChild().intValue() != master.getOchild() || master.getArr().compareTo(master.getOcheckinDate()) != 0 || master.getDep().compareTo(master.getOcheckoutDate()) != 0 || !master.getRatePlanCode().equals(master.getOratePlanCode()) || !master.getType().equals(master.getOtype()) || !master.getChainCode().equals(master.getOchainCode()) || !master.getHotelCode().equals(master.getOhotelCode()) || !master.getChannel().equals(master.getOchannel())) {
			List<PriceCalc> pcList = priceCalcManager.getRoomPrice(pc);
			if (pcList == null) {
				throw new BizException("R0168");
			}
			// 每日房价详情与总房价
			double totalAmount = 0;
			double totalAmountAfterTax = 0;
			List<MasterRate> mrList = new ArrayList<MasterRate>();
			for (int i = 0; i < pcList.size(); i++) {
				PriceCalc pcal = pcList.get(i);
				Date priceDate = null;
				try {
					priceDate = DateUtil.convertStringToDate(pcal.getDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				MasterRate mr = new MasterRate();
				mr.setDate(priceDate);
				log.info("循环的日期：" + pcal.getDate());
				BigDecimal aValue = pcal.getAmount();
				mr.setSetrate(aValue);
				mr.setSetrateAfterTax(pcal.getAmountAfterTax());
				totalAmount = totalAmount + aValue.doubleValue();
				totalAmountAfterTax=totalAmountAfterTax+pcal.getAmountAfterTax().doubleValue();
				mr.setHotelId(master.getHotelId());
				mr.setCby(master.getChannel());
				mr.setChanged(new Date());
				mrList.add(mr);
				log.info("每日房价: " + mr);
			}
			// 无房价时返回错误信息
			if (totalAmount == 0) {
				throw new BizException("R0168");
			}
			master.setMrList(mrList);
			master.setCurrencyCode(OWSConstant.currencyCode);
			master.setSetrate(new BigDecimal(totalAmount).setScale(4, BigDecimal.ROUND_HALF_UP));// 房价总和
			master.setSetrateAfterTax(new BigDecimal(totalAmountAfterTax).setScale(4, BigDecimal.ROUND_HALF_UP));// 税收房价总和（不存）
			master.setChargeAfterTax(AmountUtil.add(master.getSetrateAfterTax(), master.getRmrate()));
			try {
				// 获取N间房的动态和1间房的静态包价
				List<MasterPackage> mpList = orderManager.getPackageInfo(master, pcList);
				master.setMpList(mpList);
				if (master.getNumberOfUnits() > 1) {
					BigDecimal dynamicPackage = AmountUtil.reduce(master.getRmrate(), master.getStaticPackage());
					master.setStaticPackage(master.getStaticPackage().multiply(new BigDecimal(master.getNumberOfUnits())));// 静态包价之和
					master.setRmrate(AmountUtil.add(master.getStaticPackage(), dynamicPackage));// 总共应收包价
					master.setSetrate(master.getSetrate().multiply(new BigDecimal(master.getNumberOfUnits())));// 房价之和
					master.setSetrateAfterTax(master.getSetrateAfterTax().multiply(new BigDecimal(master.getNumberOfUnits())));// 税后房价之和
					master.setCharge(AmountUtil.add(master.getSetrate(), master.getRmrate()));// 总共应收16
					master.setChargeAfterTax(AmountUtil.add(master.getSetrateAfterTax(), master.getRmrate()));// 税后总金额（不存）
				}
			} catch (Exception e) {
				log.error(CommonUtil.getExceptionMsg(e, new String[] { "ccm" }));
			}
		} else {
			try {
				// 获取N间房的动态和1间房的静态包价
				List<MasterRate> rateList=orderManager.getMasterRateByOrderNo(master.getMasterId());
				BigDecimal setrateAfterTax=new BigDecimal(0);
				for(MasterRate mr:rateList){
					setrateAfterTax=setrateAfterTax.add(mr.getSetrateAfterTax());
				}
				master.setSetrateAfterTax(setrateAfterTax.setScale(4, BigDecimal.ROUND_HALF_UP));
				master.setChargeAfterTax(AmountUtil.add(master.getSetrateAfterTax(), master.getRmrate()));
				master.setMrList(orderManager.getMasterRateByOrderNo(master.getMasterId()));
				List<MasterPackage> mpList = orderManager.getPackageInfo(master, null);
				master.setMpList(mpList);
				if (master.getNumberOfUnits() > 1) {
					BigDecimal dynamicPackage = AmountUtil.reduce(master.getRmrate(), master.getStaticPackage());
					master.setStaticPackage(master.getStaticPackage().multiply(new BigDecimal(master.getNumberOfUnits())));// 静态包价之和
					master.setRmrate(AmountUtil.add(master.getStaticPackage(), dynamicPackage));// 总共应收包价
					master.setSetrate(master.getSetrate().multiply(new BigDecimal(master.getNumberOfUnits())));// 房价之和
					master.setSetrateAfterTax(master.getSetrateAfterTax().multiply(new BigDecimal(master.getNumberOfUnits())));// 税后房价之和
					master.setCharge(AmountUtil.add(master.getSetrate(), master.getRmrate()));// 总共应收16
					master.setChargeAfterTax(AmountUtil.add(master.getSetrateAfterTax(), master.getRmrate()));// 税后总金额（不存）
				}
			} catch (Exception e) {
				log.error(CommonUtil.getExceptionMsg(e, new String[] { "ccm" }));
			}
		}
	}

}
