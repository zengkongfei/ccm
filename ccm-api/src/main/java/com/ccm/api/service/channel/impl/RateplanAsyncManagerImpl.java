package com.ccm.api.service.channel.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.DELETE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.channel.RateplanDao;
import com.ccm.api.dao.ratePlan.RateAmountDao;
import com.ccm.api.dao.ratePlan.RateDetailDao;
import com.ccm.api.dao.ratePlan.RoomPackageDao;
import com.ccm.api.dao.ratePlan.RoomRateDao;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.ratePlan.RateAmount;
import com.ccm.api.model.ratePlan.vo.RateDetailVO;
import com.ccm.api.model.ratePlan.vo.RoomRateVO;
import com.ccm.api.service.channel.RateplanAsyncManager;
import com.ccm.api.service.ratePlan.PriceCalcManager;
import com.ccm.api.util.CommonUtil;
@Service("rateplanAsyncManager")
public class RateplanAsyncManagerImpl implements RateplanAsyncManager {
    @Autowired
    private RateplanDao rateplanDao;
    @Resource
    private PriceCalcManager priceCalcManager;
    @Resource
    private RateDetailDao rateDetailDao;
    @Resource
    private RoomRateDao roomRateDao;
    @Resource
    private RoomPackageDao roomPackageDao;
    @Resource
    private RateAmountDao rateAmountDao;
    
    //暂未使用
    @Async
    public void updateRefRateplan(RatePlanVO ratePlanVO) {
        Rateplan rp = ratePlanVO.getRp();
        List<Rateplan> rateplanList = rateplanDao.getRefRateplan(rp.getHotelId(),ratePlanVO.getRatePlanId());
        if (CommonUtil.isEmpty(rateplanList)) {
            rateplanList = new ArrayList<Rateplan>();
        }
        for (Rateplan rateplan : rateplanList) {
          //删除该价格定义 PriceCalc
            priceCalcManager.deletePriceCalcByRateRateplan(rateplan);
            //保存现在的PriceCalc
            try {
                RatePlanVO rpv = new RatePlanVO();
                rateplan.setInheritRatePlanId(rp.getRatePlanId());//设置成父detail
                rpv.setRp(rateplan);
                rpv.setRateDetailVOList(getRateDetailVOList(rateplan));
                priceCalcManager.addPriceCalc(rpv,true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**获取房价明细*/
    public List<RateDetailVO> getRateDetailVOList(Rateplan rp){
        String rpId=null;
        if(CommonUtil.isNotEmpty(rp) && CommonUtil.isNotEmpty(rp.getInheritRatePlanId())){
            rpId = rp.getInheritRatePlanId();
        }else{
            rpId = rp.getRatePlanId();
        }
        List<RateDetailVO> rateDetailVOList = rateDetailDao.getRateDetailVOByRatePlanId(rpId);
        for (RateDetailVO rateDetailVO : rateDetailVOList) {
            //房型
            List<RoomRateVO> roomRateList = roomRateDao.getRoomRateVOByRateDetailId(rateDetailVO.getRateDetailId());
            for (RoomRateVO roomRateVO : roomRateList) {
                //房型打包服务
                roomRateVO.setRoomPackageList(roomPackageDao.getRoomPackageByRoomRateId(roomRateVO.getRoomRateId()));
            }
            rateDetailVO.setRoomRateList(roomRateList);
            List<RateAmount> rateAmountList =null;
            //房价
            if(CommonUtil.isNotEmpty(rp) && CommonUtil.isNotEmpty(rp.getInheritRatePlanId())){
                rateAmountList = rateAmountDao.getRateAmountByRateDetailId(rateDetailVO.getRateDetailId(),rp);
            }else{
                rateAmountList = rateAmountDao.getRateAmountByRateDetailId(rateDetailVO.getRateDetailId());
            }
            rateDetailVO.setRateAmountList(rateAmountList);
        }
        return rateDetailVOList;
    }
}
