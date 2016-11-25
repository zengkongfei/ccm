package com.ccm.api.dao.rsvtype.ibatis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.rsvtype.RsvchanBlockDao;
import com.ccm.api.dao.rsvtype.RsvtypeChannelDao;
import com.ccm.api.dao.rsvtype.RsvtypeDao;
import com.ccm.api.model.rsvtype.RsvchanBlock;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.model.rsvtype.RsvtypeChannel;
import com.ccm.api.model.rsvtype.RsvtypeChannelCode;
import com.ccm.api.model.rsvtype.vo.RoomStatusVO;
import com.ccm.api.model.rsvtype.vo.RsvtypeChannelCriteria;
import com.ccm.api.model.rsvtype.vo.RsvtypeChannelResult;
import com.ccm.api.util.CommonUtil;
import com.ibatis.sqlmap.client.SqlMapExecutor;

@Repository("rsvtypeChannelDao")
public class RsvtypeChannelDaoibatis extends GenericDaoiBatis<RsvtypeChannel, String> implements RsvtypeChannelDao {
	@Resource
	private RsvtypeDao rsvtypeDao;
	@Resource
	private RsvchanBlockDao rsvchanBlockDao;
    public RsvtypeChannelDaoibatis() {
        super(RsvtypeChannel.class);
    }

    @Override
    public RsvtypeChannel getRsvtypeChannelByRsvIdAndChannelId(String rsvId,
            String channelId) {
        HashMap<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("rsvtypeId", rsvId);
        paramMap.put("channelId", channelId);
        return (RsvtypeChannel)this.getSqlMapClientTemplate().queryForObject("getRsvtypeChannelByRsvIdAndChannelId",paramMap);
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Rsvtype> getRsvtypeByChannelIdsRoomTypeCodes(RoomStatusVO vo) {
    	List<Rsvtype> rsvtypeList = rsvtypeDao.getRsvtypeByDateSpan(vo.getHotelCode(), vo.getRoomTypeCodes(), vo.getStartDate(), vo.getEndDate());
    	List<String> rsvtypeIdList = new ArrayList<String>();
    	
    	HashMap<String,List<RsvtypeChannel>> rsvtypeChannelMap = new HashMap<String, List<RsvtypeChannel>>();
    	
    	for (Rsvtype rsvtype : rsvtypeList) {
    		String rsvTypeId = rsvtype.getRsvtypeId();
    		rsvtypeIdList.add(rsvTypeId);
		}
    	vo.setRsvtypeIdList(rsvtypeIdList);
    	if(rsvtypeIdList.size()>0){
    		List<RsvtypeChannel> rsvtypeChannelList = getSqlMapClientTemplate().queryForList("getRsvtypeChannelByChannelIdRsvtypeId", vo);
    		Map<String,List<RsvchanBlock>>  rsvchanBlocksMap = rsvchanBlockDao.getBlockChannelAilable(rsvtypeChannelList);
    		
        	//分组渠道房量
        	for (RsvtypeChannel rsvtypeChannel : rsvtypeChannelList) {
        		rsvtypeChannel.setRsvchanBlockList(rsvchanBlocksMap.get(rsvtypeChannel.getRsvtypeChannelId()));
        		String rsvTypeId = rsvtypeChannel.getRsvtypeId();
        		if(rsvtypeChannelMap.get(rsvTypeId)==null){
        			List<RsvtypeChannel> rcList = new ArrayList<RsvtypeChannel>();
        			rcList.add(rsvtypeChannel);
        			rsvtypeChannelMap.put(rsvTypeId, rcList);
        		}else{
        			List<RsvtypeChannel> rcList = rsvtypeChannelMap.get(rsvTypeId);
        			rcList.add(rsvtypeChannel);
        		}
    		}
        	//设置渠道房量
        	for (Rsvtype rsvtype : rsvtypeList) {
        		String rsvTypeId = rsvtype.getRsvtypeId();
        		rsvtype.setRcList(rsvtypeChannelMap.get(rsvTypeId));
    		}
    	}
        return rsvtypeList;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<RsvtypeChannel> getRsvtypeChannelAilable(HashMap<String, Object> paramMap) {
		long startMili = System.currentTimeMillis();
		//获得酒店房态信息
		List<Rsvtype> rsvtypeList = rsvtypeDao.getRsvtypeByHotelRoomDate(paramMap);
		List<RsvtypeChannel> rsvtypeChannelList = new ArrayList<RsvtypeChannel>();
		if (rsvtypeList == null || rsvtypeList.isEmpty()) {
			return rsvtypeChannelList;
		}
		//放入房态id以及房态信息copy到渠道信息中
		Map<String, RsvtypeChannel> rsvtypeMap = new HashMap<String, RsvtypeChannel>();
		List<String> rsvtypeIds = new ArrayList<String>();

		for (Rsvtype rsvtype : rsvtypeList) {
			rsvtypeIds.add(rsvtype.getRsvtypeId());
			RsvtypeChannel rc = new RsvtypeChannel();
			BeanUtils.copyProperties(rsvtype, rc);
			rsvtypeMap.put(rsvtype.getRsvtypeId(), rc);
			rsvtypeChannelList.add(rc);
		}
		
		paramMap.put("rsvtypeIds", rsvtypeIds);
		//查询所有关于本酒店房态的渠道信息
		List<RsvtypeChannel> rsvtypeChannelDB = getSqlMapClientTemplate().queryForList("getRsvtypeChannelAilable", paramMap);
		//循环结果将渠道房态信息复制到之前创建的渠道信息中
		for (RsvtypeChannel rsvtypeChannel : rsvtypeChannelDB) {
			if (rsvtypeMap.get(rsvtypeChannel.getRsvtypeId()) != null) {
			//抓取原来保存的房态信息
				RsvtypeChannel rc = rsvtypeMap.get(rsvtypeChannel.getRsvtypeId());
				if (rc.getRsvtypeChannelId() == null) {
					rc.setRsvtypeChannelId(rsvtypeChannel.getRsvtypeChannelId());
					rc.setChannelId(rsvtypeChannel.getChannelId());
					rc.setAllotment(rsvtypeChannel.getAllotment());
					rc.setAllotmentSold(rsvtypeChannel.getAllotmentSold());
					rc.setFreeSell(rsvtypeChannel.getFreeSell());
					rc.setObSold(rsvtypeChannel.getObSold());
					rc.setSold(rsvtypeChannel.getSold());
					rc.setRsvtypeId(rsvtypeChannel.getRsvtypeId());
					rc.setHasBlock(getInt(rsvtypeChannel.getHasBlock()));
					rc.setCutOffDays(rsvtypeChannel.getCutOffDays());
					rc.setCutOffDate(rsvtypeChannel.getCutOffDate());
					rc.setRatePlanCodes(rsvtypeChannel.getRatePlanCodes());
				} else {
					RsvtypeChannel rc2 = new RsvtypeChannel();
					BeanUtils.copyProperties(rc, rc2);
					rc2.setRsvtypeChannelId(rsvtypeChannel.getRsvtypeChannelId());
					rc2.setChannelId(rsvtypeChannel.getChannelId());
					rc2.setAllotment(rsvtypeChannel.getAllotment());
					rc2.setAllotmentSold(rsvtypeChannel.getAllotmentSold());
					rc2.setFreeSell(rsvtypeChannel.getFreeSell());
					rc2.setObSold(rsvtypeChannel.getObSold());
					rc2.setSold(rsvtypeChannel.getSold());
					rc2.setRsvtypeId(rsvtypeChannel.getRsvtypeId());
					rc2.setHasBlock(getInt(rsvtypeChannel.getHasBlock()));
					rc2.setCutOffDays(rsvtypeChannel.getCutOffDays());
					rc2.setCutOffDate(rsvtypeChannel.getCutOffDate());
					rc2.setRatePlanCodes(rsvtypeChannel.getRatePlanCodes());
					rsvtypeChannelList.add(rc2);
				}

			}
		}
		return rsvtypeChannelList;
	}

	@Override
	public void addBatchRsvtypeChannel(List<RsvtypeChannel> rsvcList) {
		if(CommonUtil.isNotEmpty(rsvcList)){
			long t1=System.currentTimeMillis();
			getSqlMapClientTemplate().insert("addBatchRsvtypeChannel", rsvcList);
			System.out.println("addBatchRsvtypeChannel 这段代码运行了:"+ (System.currentTimeMillis()-t1)/1000.0 + "秒！"+"一共  "+rsvcList.size()+" 条");
			}
	}

	@Override
	public void updateBatchRsvtypeChannel(final List<RsvtypeChannel> list) {
		try {
			if (list != null) {
				this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
	                  public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
	                     executor.startBatch();
	                     for (RsvtypeChannel rsvtypeChannel : list) {
	                         executor.update("updateRsvtypeChannel", rsvtypeChannel);
	                     }
	                     executor.executeBatch();
	                     return null;
	                  }
	            });  
	        }   
	    } catch (Exception e) {
	        if (log.isDebugEnabled()) {
	            e.printStackTrace();
	            log.debug("updateBatchRsvtypeChannel error: id [updateRsvtypeChannel], parameterObject ["+ list + "].  Cause: "+ e.getMessage());
	        }
	    }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RsvtypeChannel> getRsvtypeChannelAvailable(String rsvtypeId,
			Set<String> channelIdSet) {
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("rsvtypeId", rsvtypeId);
		paramMap.put("channelIdSet", new ArrayList<String>(channelIdSet));
		return getSqlMapClientTemplate().queryForList("getRsvtypeChannelAilableById", paramMap);
	}

	@Override
	public RsvtypeChannelResult searchRsvtypeChannel(RsvtypeChannelCriteria amc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRsvtypeChannelSendStatus(RsvtypeChannel rc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RsvtypeChannelCode> getRsvtypeChannelCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refreshRsvtypeChannelCode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer removeRsvtypeChannel(String channelId,String rsvtypeId) {
		// TODO Auto-generated method stub
		Map paramMap=new HashMap();
		paramMap.put("channelId", channelId);
		paramMap.put("rsvtypeId", rsvtypeId);
		return	getSqlMapClientTemplate().delete("deleteRsvtypeChannel", paramMap);
	}
	
	@Override
	public void addRsvtypeChannel(RsvtypeChannel rsvtypeChannel){
		getSqlMapClientTemplate().insert("addRsvtypeChannel", rsvtypeChannel);
	}
	@Override
	public List<RsvtypeChannel> getRsvChannelListByHeaderDetail(String hotelCode,String channelId,String roomType,Date inventoryDate){
		Map paramMap=new HashMap();
		paramMap.put("channelId", channelId);
		paramMap.put("inventoryDate",inventoryDate);
		paramMap.put("roomType",roomType);
		paramMap.put("hotelCode",hotelCode);  
		return getSqlMapClientTemplate().queryForList("getRsvChannelCountByHeaderDetail",paramMap);
	}
	@Override
	public Integer updateAllotmentToRsvtypeChannel(RsvtypeChannel rsvtypeChannel){
		return getSqlMapClientTemplate().update("updateAllotmentToRsvtypeChannel",rsvtypeChannel);
	}
	private int getInt(Object obj) {
		return obj != null ? Integer.parseInt(obj.toString()) : 0;
	}

	@Override
	public Integer updateRsvtypeForDeduct(Rsvtype rsvtype) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update("updateRsvtypeForDeduct",rsvtype);
	}

	@Override
	public Integer updateRsvtypeChannelForDeduct(Rsvtype RsvtypeChannel) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update("updateRsvtypeChannelForDeduct",RsvtypeChannel);
	}
}
