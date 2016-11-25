package com.ccm.api.service.email;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ccm.api.dao.sms.SmsSendDao;
import com.ccm.api.model.constant.EmailType;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.SmsType;
import com.ccm.api.model.email.vo.EmailVO;
import com.ccm.api.model.sms.SmsSend;
import com.ccm.api.service.base.BaseManagerTestCase;
import com.ccm.api.util.CommonUtil;

public class EmailMamagerTest extends BaseManagerTestCase{
	@Resource
	private EmailManager emailManager;
//	@Test
	public void sendForgetPasswordEmail(){
		emailManager.sendForgetPasswordEmail("Chay.Huang@shijinet.com.cn", "test", "test");
	}
	@Rollback(false)
	@Test
	public void testSaveEmail(){
		String content="<html><body>温馨提示：按照现在的预订的情况，您的信用额度的余额只有${balance}<font color='red'>（总额的20%）</font>。</br>Please be advise: Your balance of credit is only 100.00 by your booking now <font color='red'>（20% of original balance）</font>.</br></br>Shanghai Disney Resort</body></html>";
		emailManager.creditLimitlogForsmsSend(EmailType.CreditLimitNotice,"TAOBAO","TESTCCM","畅联测试酒店", "saul.luan@shijinet.com.cn","title TAOBAO",content,null);
	}
//	@Test
	public void sendEmail(){
		log.info("begin sendCreditLimitEmail");
		String str1="<html><body>温馨提示：按照现在的预订的情况，您的信用额度的余额只有${balance}<font color='red'>（总额的20%）</font>。</br>Please be advise: Your balance of credit is only 100.00 by your booking now <font color='red'>（20% of original balance）</font>.</br></br>Shanghai Disney Resort</body></html>";
		String str3="<html><body>温馨提示：按照现在的预订情况，您的信用额度余额不足。您无法继续预订直到您的信用额度被恢复。</br>Please attention: Your balance of credit cannot cover your current booking now. You won’t be able to do the booking till your credit recovery.</br></br>Shanghai Disney Resort</body></html>";
		String str4="<html><body>提示：您的保留房配额还有余额。您此次的预定(预定号：${orderNo})使用了FREE SELL的库存，违反我们的预定规则。我们将从您的保留房配额扣除此次预定。</br>Attention: You still have allotment balance. Per our business rules, you are required to use up all your allotment balance before booking rooms from Free Sell. Thus, your current booking will be deducted from your allotment.</br></br>Shanghai Disney Resort</body></html>";
				EmailVO emailVO = new EmailVO();
				List<String>emailList=new ArrayList<String>();
				convertEmailArrayFromStringField("Chay.Huang@shijinet.com.cn",emailList);
				convertEmailArrayFromStringField("maggie.lu@shijinet.com.cn",emailList);
				String[]emailArray=new String[emailList.size()];
				emailVO.setToArray(emailList.toArray(emailArray));
				
				emailVO.setSubject("信用额度提醒(${channelCode})".replace("${channelCode}","taobao"));
				emailVO.setContent(str1.replace("${balance}", "100.00"));
				log.info("begin to invoke sendText");
				emailManager.sendHtml(emailVO);
				log.info("end to invoke sendText");
	}
	
	private void convertEmailArrayFromStringField(String emails,List<String>emailList){
		if(emails.contains(";")){
			emailList.addAll(Arrays.asList(emails.split(";")));
		}else if(emails.contains(",")){
			emailList.addAll(Arrays.asList(emails.split(",")));
		}else{
			emailList.add(emails);
		}
	}
}
