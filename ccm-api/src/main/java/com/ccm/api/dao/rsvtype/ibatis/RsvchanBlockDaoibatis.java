package com.ccm.api.dao.rsvtype.ibatis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.rsvtype.RsvchanBlockDao;
import com.ccm.api.model.rsvtype.RsvchanBlock;
import com.ccm.api.model.rsvtype.RsvtypeChannel;

@Repository("rsvchanBlockDao")
public class RsvchanBlockDaoibatis extends
		GenericDaoiBatis<RsvchanBlock, String> implements RsvchanBlockDao {

	public RsvchanBlockDaoibatis() {
		super(RsvchanBlock.class);
	}

	@Override
	public RsvchanBlock saveRsvchanBlock(RsvchanBlock rsvchanBlock) {
		// TODO Auto-generated method stub
		return (RsvchanBlock)getSqlMapClientTemplate().insert("addRsvchanBlock",rsvchanBlock);
	}

	@Override
	public Integer updateRsvchanBlock(RsvchanBlock rsvchanBlock) {
		// TODO Auto-generated method stub
		return  getSqlMapClientTemplate().update("updateRsvchanBlock", rsvchanBlock);
	}

	@Override
	public RsvchanBlock getRsvchanBlockByParam(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return (RsvchanBlock)getSqlMapClientTemplate().queryForObject("getRsvchanBlockByParam",paramMap);
	}


	@Override
	public Map<String, List<RsvchanBlock>> getBlockChannelAilable(
			List<RsvtypeChannel> rcList) {
		// TODO Auto-generated method stub
		Map<String, List<RsvchanBlock>> rsvChanBlockMap = new HashMap<String, List<RsvchanBlock>>();
		Map<String,Object>paramMap=new HashMap<String,Object>();
		List<String>rsvtypeChannelList=new ArrayList<String>();
		paramMap.put("rsvtypeChannelIds",rsvtypeChannelList);
		for(RsvtypeChannel rc:rcList){
			rsvtypeChannelList.add(rc.getRsvtypeChannelId());
		}
		if(rsvtypeChannelList.size()==0){
			return rsvChanBlockMap;
		}
		List<RsvchanBlock> blockList = getRsvchanBlocksByRsvchanelIds(paramMap);
			
		if (blockList!=null) {
			for (RsvchanBlock block : blockList) {
				List<RsvchanBlock> channelBlockList = rsvChanBlockMap.get(block.getRsvtypeChannelId());
				if (channelBlockList == null) {
					channelBlockList = new ArrayList<RsvchanBlock>();
					rsvChanBlockMap.put(block.getRsvtypeChannelId(), channelBlockList);
					channelBlockList.add(block);
				} else {
					channelBlockList.add(block);
				}
				Collections.sort(channelBlockList, new Comparator<RsvchanBlock>() {
					public int compare(RsvchanBlock arg0, RsvchanBlock arg1) {
						return arg0.getBlockCode().compareTo(arg1.getBlockCode());
					}
				});
			}
		}
		return rsvChanBlockMap;
	}

	@Override
	public Integer updateRsvchanBlockSold(RsvchanBlock rsvchanBlock) {
		// TODO Auto-generated method stub
		rsvchanBlock.setLastModifyTime(new Date());
		return getSqlMapClientTemplate().update("updateRsvchanBlockSold", rsvchanBlock);
	}

	@Override
	public List<RsvchanBlock> getRsvchanBlocksByRsvchanelIds(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(
				"getRsvchanBlocksByRsvchanelIds", paramMap);
	}

	@Override
	public RsvchanBlock getBlockListByRsvChanIdAndBlockCode(
			String rsvtypeChannelId,String blockCode) {
		// TODO Auto-generated method stub
		Map<String,Object>paramMap=new HashMap<String,Object>();
		paramMap.put("rsvtypeChannelId", rsvtypeChannelId);
		paramMap.put("blockCode", blockCode);
		return (RsvchanBlock)getSqlMapClientTemplate().queryForObject(
				"getBlockListByRsvChanIdAndBlockCode", paramMap);
	}

	@Override
	public Integer removeRsvchanBlock(String hotelCode,String channelCode, Date inventoryDate,
			String roomType, String invBlockGroupingCode) {
		// TODO Auto-generated method stub
		Map<String,Object>paramMap=new HashMap<String,Object>();
		paramMap.put("blockCode", invBlockGroupingCode);
		paramMap.put("hotelCode", hotelCode);
		paramMap.put("type", roomType);
		paramMap.put("date", inventoryDate);
		paramMap.put("channelCode", channelCode);
		return getSqlMapClientTemplate().delete("deleteRsvchanBlockByCondition",paramMap);
	}

	@Override
	public List<RsvchanBlock> getIsSendToPMSRsvchanBlock(String hotelCode,String channelCode, List<String> dateList, String roomType) {
		Map<String,Object>paramMap=new HashMap<String,Object>();
		paramMap.put("hotelCode", hotelCode);
		paramMap.put("type", roomType);
		paramMap.put("dateList", dateList);
		paramMap.put("channelCode", channelCode);
		return getSqlMapClientTemplate().queryForList("getIsSendToPMSRsvchanBlock", paramMap);
	}

	@Override
	public List<RsvchanBlock> getHandSendRsvchanBlock(String hotelCode,String channelCode, String date, String roomType) {
		Map<String,Object>paramMap=new HashMap<String,Object>();
		paramMap.put("hotelCode", hotelCode);
		paramMap.put("type", roomType);
		paramMap.put("date", date);
		paramMap.put("channelCode", channelCode);
		return getSqlMapClientTemplate().queryForList("getHandSendRsvchanBlock", paramMap);
	}
	
	@Override
	public List<RsvchanBlock> getRsvchanBlocksByRsvchanelId(String rsvchanelId) {
		// TODO Auto-generated method stub
		Map<String,Object>paramMap=new HashMap<String,Object>();
		paramMap.put("rsvtypeChannelId", rsvchanelId);
		return getSqlMapClientTemplate().queryForList("getRsvchanBlocksByRsvchanelId",paramMap);
	}

	@Override
	public Integer removeRsvchanBlocksByRsvchanelId(String rsvchannelId) {
		// TODO Auto-generated method stub
		Map<String,Object>paramMap=new HashMap<String,Object>();
		paramMap.put("rsvtypeChannelId", rsvchannelId);
		return getSqlMapClientTemplate().delete("deleteRsvchanBlocksByRsvchanelId",paramMap);
	}

	@Override
	public List<RsvchanBlock> getRsvchanBlocksByOrder(String hotelCode,
			String channelCode, List<String> dateList, String roomType) {
		// TODO Auto-generated method stub
		Map<String,Object>paramMap=new HashMap<String,Object>();
		paramMap.put("hotelCode", hotelCode);
		paramMap.put("type", roomType);
		paramMap.put("dateList", dateList);
		paramMap.put("channelCode", channelCode);
		return getSqlMapClientTemplate().queryForList("getRsvchanBlocksByOrder", paramMap);
	}

	@Override
	public RsvchanBlock getRsvchanBlockNum(String channelCode, String type,String hotelCode, Date date) {
		Map<String,Object>paramMap=new HashMap<String,Object>();
		paramMap.put("hotelCode", hotelCode);
		paramMap.put("type", type);
		paramMap.put("date", date);
		paramMap.put("channelCode", channelCode);
		return (RsvchanBlock) getSqlMapClientTemplate().queryForObject("getRsvchanBlockNum", paramMap);
	}

	@Override
	public Integer removeRsvchanBlockByRsvchannelIdAndBlockCode(
			String rsvchannelId, String blockCode) {
		// TODO Auto-generated method stub
		Map<String,Object>paramMap=new HashMap<String,Object>();
		paramMap.put("rsvtypeChannelId", rsvchannelId);
		paramMap.put("blockCode", blockCode);
		return getSqlMapClientTemplate().delete("removeRsvchanBlockByRsvchannelIdAndBlockCode",paramMap);
	}
	@Override
	public Integer getBlockCountWithoutRates(String rsvtypeChannelId){
		Map<String,Object>paramMap=new HashMap<String,Object>();
		paramMap.put("rsvtypeChannelId", rsvtypeChannelId);
		return (Integer) getSqlMapClientTemplate().queryForObject("getBlockCountWithoutRates",paramMap);
	}

	@Override
	public List<RsvchanBlock> getRsvchanBlocksByChannelAndBlock(String hotelCode,
			String channelCode, String blockCode) {
		// TODO Auto-generated method stub
			Map<String,Object>paramMap=new HashMap<String,Object>();
			paramMap.put("hotelCode", hotelCode);
			paramMap.put("channelCode", channelCode);
			paramMap.put("blockCode", blockCode);
			return getSqlMapClientTemplate().queryForList("getRsvchanBlocksByChannelAndBlock",paramMap);
	}
}
