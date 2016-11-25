package com.ccm.api.service.shijicare;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.ccm.api.dao.shijiCare.ShijiCareDao;
import com.ccm.api.model.shijicare.ShijiCare;
import com.ccm.api.model.shijicare.vo.ShijicareCriteria;
import com.ccm.api.model.shijicare.vo.ShijicareSearchResult;
import com.ccm.api.service.base.BaseManagerTestCase;
import com.ccm.api.service.system.ShijiCareCaseManage;

public class ShijiCareTest extends BaseManagerTestCase{
	@Autowired
	private ShijiCareCaseManage shijiCareCaseManage;
	@Autowired
	private ShijiCareDao shijiCareDao;
	
	@Autowired
	private ShijiCareManager shijiCareManager;
	
	
	@Test
	@Rollback(false)
	public void test(){
		ShijiCare sc = new ShijiCare();
		sc.setHotelCode("aaaaa");
		sc.setFullDescription("南昌瑞颐酒店 酒店接口断开,请查找原因,");
		sc.setBriefDescription("南昌瑞颐酒店 酒店接口断开,");
		sc.setSuite(ShijiCare.SUITE_COL);
		sc.setProductId("sdfsdf");
		sc.setProblemType(ShijiCare.PROBLEMTYPE_PMSINTEFACE);
		sc.setAssignTo(sc.getProblemAssignTo(ShijiCare.PROBLEMTYPE_PMSINTEFACE));
		sc.setResultMsg("参数必须输入,不能为空,");
		sc.setStatus("FAIL");
		sc.setProblemType("接口机");
		sc.setAssignTo("tsi");
		sc.setSuite("COL");
		shijiCareDao.save(sc);
	}
	
	@Test
	public void testSearch(){
		ShijicareCriteria sc = new ShijicareCriteria();
		List<String> hotelIdList = new ArrayList<String>();
		hotelIdList.add("SIHJI");
		sc.setHotelCodeList(hotelIdList);
		ShijicareSearchResult result = shijiCareManager.searchShijiCareVO(sc);
		System.out.println(result.getTotalCount());
		System.out.println(result.getResultList());
		System.out.println(result.getResultList().get(0).getStatusResult());
		System.out.println(result.getResultList().get(0).getSpaceSec());
	}
	
	@Test
	public void sendShijiCare(){
		ShijiCare sc = new ShijiCare();
		sc.setCreatedTime(new Date());
		sc.setHotelCode("TEST");
		sc.setFullDescription("测试新地址是否可用");
		sc.setBriefDescription("测试新地址是否可用");
		sc.setSuite(ShijiCare.SUITE_COL);
		sc.setProductId("TEST");
		sc.setProblemType(ShijiCare.PROBLEMTYPE_PMSINTEFACE);
		sc.setAssignTo(sc.getProblemAssignTo(ShijiCare.PROBLEMTYPE_PMSINTEFACE));
		
		//建立shiji care
		shijiCareCaseManage.newCase(sc);
	}
	@Test
	@Rollback(false)
	public void closeShijicare(){
		shijiCareCaseManage.closeCase("26b3a6bf0d2d4c38bdb8721ef8a52de9","665317");
	}
	
	@Test
	public void shijiCareList(){
		ShijiCare shijiCare = new ShijiCare();
		shijiCare.setAssignTo(shijiCare.getProblemAssignTo(ShijiCare.PROBLEMTYPE_PMSINTEFACE));
		shijiCare.setHotelCode("SIHJI");
		shijiCare.setStatus("FAIL");
		shijiCare.setIsclose(false);
		List<ShijiCare> list = shijiCareDao.shijiCareList(shijiCare);
		for(ShijiCare s :list){
			System.out.println(s);
		}
	}
	
}
