package com.ccm.api.service.channel.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccm.api.dao.channel.ChannelgoodsDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.service.channel.ChannelGoodsExportManager;
import com.ccm.api.util.CommonUtil;


@Service("channelgoodsExportManager")
public class ChannelGoodsExportManagerImpl implements ChannelGoodsExportManager{
	
	@Autowired
	private ChannelgoodsDao channelgoodsDao;

	@Autowired
	private HotelDao hotelDao; 
	
	/**
	 * 获取要导出的数据
	 * @param cgvo
	 */
	public List<String[]> getChannelGoodsData(ChannelGoodsVO cgvo){
		//获得数据
		List<ChannelGoodsVO> channelGoodsList = channelgoodsDao.searchChannelgoodsByChannelHotelGroupByRoomType(cgvo);
		
		//由于需要根据房型导入房价,则需要去除房型重复的情况
		List<ChannelGoodsVO> exportGoodsList = new ArrayList<ChannelGoodsVO>();
		for (ChannelGoodsVO channelGoodsVO : channelGoodsList) {
			boolean flag = false;
			for (ChannelGoodsVO exGoods : exportGoodsList) {
				//如果酒店+渠道+房型相等
				if(channelGoodsVO.getChannelId().equals(exGoods.getChannelId())
						&&channelGoodsVO.getRoomTypeId().equals(exGoods.getRoomTypeId())
						&&channelGoodsVO.getRatePlanCode().equals(exGoods.getRatePlanCode())){
					flag = true;
					break;
				}
			}
			
			if(flag)continue;
			//如果不重复,添加进list中
			exportGoodsList.add(channelGoodsVO);
		}
		
		//保存导出的数据列表
		List<String[]> dataList = new ArrayList<String[]>();

		//获取酒店信息
		HotelVO hotelVo = hotelDao.getHotelI18nChainByHotelId(cgvo.getHotelId());
		HotelVO hotelVoE = hotelDao.getHotelI18nChainByHotelId(cgvo.getHotelId(), LanguageCode.EN_US);

		//转换日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		for (ChannelGoodsVO cgv : exportGoodsList) {
			String[] cgvArry = new String[11];
			cgvArry[0] = hotelVo.getHotelCode();
			cgvArry[1] = hotelVoE != null ? hotelVoE.getHotelName() : "";  //酒店英文名
			cgvArry[2] = hotelVo.getHotelName();
			cgvArry[3] = cgv.getRoomTypeCode();
			cgvArry[4] = cgv.getRoomTypeName();
			cgvArry[5] = cgv.getRatePlanCode();
			cgvArry[6] = CommonUtil.filterHtml(cgv.getDescription());
			cgvArry[7] = cgv.getChannelCode();
			cgvArry[8] = cgv.getRoomTypeCode();
			cgvArry[9] = cgv.getRoomTypeName();
			
			if(CommonUtil.isEmpty(cgv.getExpireDate())){
				Calendar c = Calendar.getInstance();
				c.setTime(new Date()); 
				cgvArry[10] = "12/31/"+c.get(Calendar.YEAR);
			}else{
				cgvArry[10] = sdf.format(cgv.getExpireDate());
			}
			dataList.add(cgvArry);
		}
		return dataList;
	}
	
	
	
}
