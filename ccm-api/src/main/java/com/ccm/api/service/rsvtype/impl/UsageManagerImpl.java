package com.ccm.api.service.rsvtype.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.redis.RedisDao;
import com.ccm.api.dao.rsvtype.UsageDao;
import com.ccm.api.model.rsvtype.Usage;
import com.ccm.api.model.rsvtype.vo.UsageCriteria;
import com.ccm.api.model.rsvtype.vo.UsageResult;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.rsvtype.UsageManager;
import com.ccm.api.util.DateUtil;


@Service("usageManager")
public class UsageManagerImpl extends GenericManagerImpl<Usage, String> implements UsageManager {

	@Autowired
	private UsageDao usageDao;
    
	@Autowired
	private HotelManager hotelManager;
	
	@Resource
	private RedisDao redisDao;
	
	@Resource
    public void setUsageDao(UsageDao usageDao) {
        this.dao = usageDao;
        this.usageDao = usageDao;
    }

	@Override
	public UsageResult getUsages(UsageCriteria usageCriteria) {
		UsageResult result=new UsageResult();
		List<Usage> usageList=usageDao.getUsages(usageCriteria);
		result.setResultList(usageList);
		result.setTotalCount(usageDao.getUsagesCount(usageCriteria));
		if(null!=usageList&&usageList.size()>0){
			for(int i=0;i<usageList.size();i++){
				Usage usage=usageList.get(i);
				
				//减去已售保留房
				Integer f=usage.getFreeSellSold();
				if(f!=null){
					Integer s=usage.getAllotmentSold();
					if(s==null){
						s=0;
					}
					usage.setFreeSellSold((f-s));
				}
				
		
				/*
				//组装key并获取Redis内的数据
				String hotelCode=usage.getHotelCode();
				String roomType=usage.getRoomType();
				Date resvDate=usage.getResvDate();
				String key=hotelCode+"|"+roomType+"|"+DateUtil.getMonthOfYear(resvDate);
				Long v=redisDao.readLongValueFromHash(key, DateUtil.convertDateToString(resvDate));
				//获得mysql数据库查到的值
				Integer f=usage.getFreeSellSold();
				if(f==null||f<0){
					f=0;
				}
				//Redis和mysql的值相加
				if(null!=v && v>0){
					int fv=f+v.intValue();
					usage.setFreeSellSold(fv);
				}
				*/
				//遍历月份，然后Redis和mysql的值相加
				/*
				Map<String, Long> map=redisDao.readLongValueFromMap(key);
				if(null!=map){
					Long p=map.get(DateUtil.convertDateToString(resvDate));
					if(p!=null&&p>0){
						int v=Integer.parseInt(p+"");
						Integer f=usage.getFreeSellSold();
						if(f!=null&&f>0){
							usage.setFreeSellSold(v+f);
						}
					}
				}
				*/
				
			}
		}
	
		//如果是导出，则在结尾加上指定记录的总计数
		/*
		if(!usageCriteria.isNeedPage()){
			Usage u=usageDao.getUsagesSum(usageCriteria);
			if(null!=u){
				result.setAllotmentSum(u.getAllotmentSum());
				result.setAllotmentRemainSum(u.getAllotmentRemainSum());
				result.setAvailableSum(u.getAvailableSum());
			}
		}
		*/
		return result;
	}

	
}
