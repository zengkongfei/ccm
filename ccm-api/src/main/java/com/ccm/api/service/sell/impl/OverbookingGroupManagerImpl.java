package com.ccm.api.service.sell.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.dao.channel.ChannelHotelDao;
import com.ccm.api.dao.sell.OverbookingGroupDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.ChannelHotel;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.rsvtype.RsvtypeChannel;
import com.ccm.api.model.sell.OverbookingGroup;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.rsvtype.AsyncSendManager;
import com.ccm.api.service.rsvtype.RsvtypeChannelManager;
import com.ccm.api.service.sell.OverbookingGroupManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

@Service("overbookingGroupManager")
public class OverbookingGroupManagerImpl extends GenericManagerImpl<OverbookingGroup, String> implements OverbookingGroupManager {
	@Resource
	private OverbookingGroupDao overbookingGroupDao;
	@Resource
	private ChannelHotelDao channelHotelDao;
	@Resource
	private RsvtypeChannelManager rsvtypeChannelManager;
	@Resource
    private ChannelDao channelDao;
	@Resource
	private ChannelgoodsManager channelgoodsManager;
	@Resource
	private AsyncSendManager asyncSendManager;
	@Resource
	public void setOverbookingGroupDao(OverbookingGroupDao overbookingGroupDao) {
		this.dao = overbookingGroupDao;
	}
	@Override
	public boolean saveChannelGroup(String channelGroupStr,boolean isPush) {
		String hotelId = SecurityHolder.getB2BUser().getHotelvo().getHotelId();
		List<OverbookingGroup> obGroupList2 = JSON.parseObject(channelGroupStr, new TypeReference<ArrayList<OverbookingGroup>>() {});
		List<OverbookingGroup> obGroupList1 = null;
		if(isPush){
            obGroupList1 = this.getObGroupByHotelId(hotelId);
            for (OverbookingGroup overbookingGroup : obGroupList1) {//仅用于在即时加载
                List<ChannelHotel> chList = overbookingGroup.getChannelHotelList();
                for (ChannelHotel ch : chList) {
                }
            }
        } 
		
		for (OverbookingGroup overbookingGroup : obGroupList2) {
			overbookingGroup.setHotelId(hotelId);
			overbookingGroup = save(overbookingGroup);
			String groupId = overbookingGroup.getGroupId();
			List<ChannelHotel> channelHotelList = overbookingGroup.getChannelHotelList();

			channelHotelDao.deleteCHByGroupIdAndHotelId(groupId,hotelId);//先删除渠道分组
			for (ChannelHotel ch : channelHotelList) {
				ch.setHotelId(hotelId);
				ch.setGroupId(groupId);
				channelHotelDao.save(ch);
			}
		}
		if(isPush){
            this.compareObGroupListAndSendChangeOBAvai(obGroupList1, obGroupList2);
        } 
		
		return true;
	}
	/**比较不同的ob分类后，并发送房量消息*/
	public void compareObGroupListAndSendChangeOBAvai(List<OverbookingGroup> obGroupList1,List<OverbookingGroup> obGroupList2){
	    Map<String, String> obGroupPer1 = new HashMap<String, String>();
	    Map<String, String> obGroupPer2 = new HashMap<String, String>();
	    
	    Map<String, HashSet<String>> channelMap1 = new HashMap<String,HashSet<String>>();
	    Map<String, HashSet<String>> channelMap2 = new HashMap<String,HashSet<String>>();
	    for (OverbookingGroup overbookingGroup : obGroupList1) {
	        String groupCode = overbookingGroup.getGroupCode();
	        obGroupPer1.put(groupCode, overbookingGroup.getPercent()+"");
	        
	        HashSet<String> channelSet = new HashSet<String>();
	        List<ChannelHotel> chList = overbookingGroup.getChannelHotelList();
	        for (ChannelHotel ch : chList) {
	            channelSet.add(ch.getChannelCode());
            }
	        channelMap1.put(groupCode,channelSet);
        }
	    if(CommonUtil.isEmpty(channelMap1)){//初始化第1次内容
            for (Entry<String, HashSet<String>> e : channelMap2.entrySet()) {
                channelMap1.put(e.getKey(), new HashSet<String>());
            }
        }
	    for (OverbookingGroup overbookingGroup : obGroupList2) {
            String groupCode = overbookingGroup.getGroupCode();
            obGroupPer2.put(groupCode, overbookingGroup.getPercent()+"");
            
            HashSet<String> channelSet = new HashSet<String>();
            List<ChannelHotel> chList = overbookingGroup.getChannelHotelList();
            for (ChannelHotel ch : chList) {
                channelSet.add(ch.getChannelCode());
            }
            channelMap2.put(groupCode,channelSet);
        }
	    
	    Map<String, Object> obGroupCompResult = compareMap(obGroupPer1,obGroupPer2);
	    /**比较渠道配额百分比*/
	    Map<String, String> obGroupChangeMap = (Map<String, String>)obGroupCompResult.get("change");
	    for (Entry<String, String> e : obGroupChangeMap.entrySet()) {
            String groupCode = e.getKey();
            String groupPer = e.getValue();
            String[] groupPers = groupPer.split(",");
            double oldPer = Double.parseDouble("null".equals(groupPers[0]) ? "0" : groupPers[0]);
            double newPer = Double.parseDouble("null".equals(groupPers[1]) ? "0" : groupPers[1]);
            System.out.println("****************************"+groupCode+" 组OB配额百分比从 "+oldPer+" 设置为 "+newPer);
            //将该组的渠道ob房量相应更新
            HashSet<String> channelSet = channelMap2.get(groupCode);
            this.sendChangeOBChannelRoomOBAvai(channelSet);
        }
	    
	    /**比较ABCDE组渠道添加移除*/
	    Map<String, HashSet<String>> addChannelMap = new HashMap<String,HashSet<String>>();
	    Map<String, HashSet<String>> delChannelMap = new HashMap<String,HashSet<String>>();
	    HashMap<String, String> channelMoveMap = new HashMap<String, String>();
	    
	    
	    for (Entry<String, HashSet<String>> e : channelMap1.entrySet()) {
	        String groupCode = e.getKey();
	        HashSet<String> channelSet1 = e.getValue();
            HashSet<String> channelSet2 = channelMap2.get(groupCode);
            Map<String, Object> groupChannelSet = compareSet(channelSet1, channelSet2);
            HashSet<String> addChannelSet = (HashSet<String>) groupChannelSet.get("add");
            HashSet<String> delChannelSet = (HashSet<String>) groupChannelSet.get("del");
            addChannelMap.put(groupCode, addChannelSet);
            delChannelMap.put(groupCode, delChannelSet);
	    }
	    
	    /**找出从某组移动到某租的渠道*/
	    for (Entry<String, HashSet<String>> e : delChannelMap.entrySet()) {
	        String delGroupCode = e.getKey();
	        HashSet<String> delChannelSet = e.getValue();
	        for (String delChannel : delChannelSet) {
	            for (Entry<String, HashSet<String>> adde : addChannelMap.entrySet()) {
	                String addGroupCode = adde.getKey();
    	            HashSet<String> addChannelSet = addChannelMap.get(addGroupCode);
    	            if(addChannelSet.contains(delChannel)){
    	                addChannelSet.remove(delChannel);
    	                delChannelSet.remove(delChannel);
    	                channelMoveMap.put(delChannel, delGroupCode+","+addGroupCode);
    	                System.out.println("****************************将 "+delChannel+" 从"+delGroupCode+" 添加到 "+addGroupCode+" 组");
    	                HashSet<String> channelSet = new HashSet<String>();
    	                channelSet.add(delChannel);
    	                this.sendChangeOBChannelRoomOBAvai(channelSet);
    	            }
	            }
            }
        }
	    
	    /**已删除的渠道*/
	    System.out.println("****************************已删除的渠道"+delChannelMap);
	    for (Entry<String, HashSet<String>> e : delChannelMap.entrySet()) {
	        HashSet<String> delChannelSet = e.getValue();
	        if(CommonUtil.isNotEmpty(delChannelSet)){
	            this.sendChangeOBChannelRoomOBAvai(delChannelSet);
	        }
        }
	    /**某组新加的渠道*/
	    System.out.println("****************************新加的渠道"+addChannelMap);
	    for (Entry<String, HashSet<String>> e : addChannelMap.entrySet()) {
            HashSet<String> addChannelSet = e.getValue();
            if(CommonUtil.isNotEmpty(addChannelSet)){
                this.sendChangeOBChannelRoomOBAvai(addChannelSet);
            }
        }
	}
	/**将该渠道所有房型房量推送出去**/
	public void sendChangeOBChannelRoomOBAvai(HashSet<String> channelCodeSet){
	    String hotelCode = SecurityHolder.getB2BUser().getHotelvo().getHotelCode();
        try {
            for (String channelCode : channelCodeSet) {
                Channel channel = channelDao.getChannelByChannelCode(channelCode);
                if(channel != null && channel.getMaxPushDay() != null){
                    Date pushDate = DateUtil.addDays(DateUtil.currentDate(), channel.getMaxPushDay()+1);
                    
                    ChannelGoodsVO cgvo = new ChannelGoodsVO();
                    cgvo.setChannelCode(channelCode);
                    List<ChannelGoodsVO> crpList = channelgoodsManager.getEnabledChannelGoods(cgvo);
                    HashSet<String> channelRoomSet = new HashSet<String>();
                    for (ChannelGoodsVO channelGoodsVO : crpList) {
                    	String channelRoom = channelCode+channelGoodsVO.getRoomTypeCode();
                    	if(!channelRoomSet.contains(channelRoom)){
                    		List<RsvtypeChannel> rcList = rsvtypeChannelManager.getRsvtypeChannelAvailable(channelGoodsVO.getRoomTypeCode(), hotelCode, DateUtil.currentDate(), pushDate, channelCode);
                            for (RsvtypeChannel rsvtypeChannel : rcList) {
                            	rsvtypeChannel.setChannelId(channel.getChannelId());
                                int avai = rsvtypeChannelManager.calcSendChannelAvailable(rsvtypeChannel);
                                rsvtypeChannel.setSendAvailable(avai);
                                rsvtypeChannel.setChannel(channelCode);
                                asyncSendManager.sendRoomStatusMsgToJms(rsvtypeChannel,false);
                                channelRoomSet.add(channelRoom);
                            }
                    	}
					}
                }
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public Map<String, Object> compareMap(Map<String, String> mapA,Map<String, String> mapB){
        Map<String, Object> mapAll = new HashMap<String, Object>();
        Map<String, String> mapAdd = new HashMap<String, String>(mapB);
        Map<String, String> mapDel = new HashMap<String, String>();
        Map<String, String> mapChange = new HashMap<String, String>();
        
        for(Entry<String, String> e : mapA.entrySet()) {
            String keyA = e.getKey();
            String valA = e.getValue();
            
            if (mapB.containsKey(keyA)) {
                String valB = mapB.get(e.getKey());
                if (valB != null && !valB.equals(valA)) {
                    mapChange.put(keyA, valA+","+valB);
                }
            } else {
                mapDel.put(e.getKey(), e.getValue());
            }
        }
        
        mapAdd.keySet().removeAll(mapA.keySet());
        
        mapAll.put("add", mapAdd);
        mapAll.put("del", mapDel);
        mapAll.put("change", mapChange);
        System.out.println(mapAll);
        return mapAll;
    }
    public Map<String, Object> compareSet(Set<String> set1,Set<String> set2){
        Map<String, Object> mapAll = new HashMap<String, Object>();
        Set<String> setAdd = new HashSet<String>(set2);
        Set<String> setDel = new HashSet<String>();
        
        for (String str : set1) {
            if(!set2.contains(str)){
                setDel.add(str);
            }
        }
        
        setAdd.removeAll(set1);
        mapAll.put("add", setAdd);
        mapAll.put("del", setDel);
        return mapAll;
    }
	
	@Override
	public List<OverbookingGroup> getObGroupByHotelId(String hotelId) {
		List<OverbookingGroup> obGroupList = overbookingGroupDao.getObGroupByHotelId(hotelId);
		return obGroupList;
	}
}
