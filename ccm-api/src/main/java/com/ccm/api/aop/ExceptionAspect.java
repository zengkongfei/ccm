/**
 * 
 */
package com.ccm.api.aop;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.mail.MailSendException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ccm.api.common.exception.BizException;
import com.ccm.api.common.exception.IsSendEmailException;
import com.ccm.api.model.email.vo.EmailVO;
import com.ccm.api.service.email.EmailManager;
import com.ccm.api.service.email.EmailProperties;
import com.ccm.api.util.CommonUtil;

/**
 * @author Jenny
 */
@Aspect
public class ExceptionAspect {

	private Log logger = LogFactory.getLog(ExceptionAspect.class);

	@Resource
	private EmailManager emailManager;

	@AfterThrowing(value = "@annotation(com.ccm.api.aop.ExceptionPointcut)", throwing = "e")
	public void recordException(JoinPoint joinPoint, Exception e) {

		try {
			if (e instanceof BizException || e instanceof IsSendEmailException || e instanceof ObjectRetrievalFailureException || e instanceof UsernameNotFoundException || e instanceof MailSendException || e instanceof TaskRejectedException) {
				return;
			}

			logger.info("recordException start");

			String to = EmailProperties.getExceptionMonitorTo();
			if (!StringUtils.hasText(to)) {
				logger.info("noSetToEmail");
				return;
			}

			EmailVO email = new EmailVO();
			email.setSubject(EmailProperties.getExceptionMonitorAppSubject());
			email.setToArray(to.split(","));

			StringBuffer sb = new StringBuffer();
			sb.append("传入的类方法名:").append(joinPoint.getTarget().getClass()).append(".").append(joinPoint.getSignature().getName()).append("\n");
			Object[] args = joinPoint.getArgs();
			sb.append("目标参数列表:");
			for (int i = 0; i < args.length; i++) {
				Object argStr;
				try {
					argStr = JSONObject.toJSONString(args[i], SerializerFeature.WriteDateUseDateFormat);
				} catch (Exception ei) {
					argStr = args[i];
				}
				if (argStr != null && argStr.toString().length() > 5000) {
					email.setSubject(email.getSubject() + "超大邮件");
					sb.append(argStr.toString().substring(0, 5000));
				} else {
					sb.append(argStr);
				}

			}
			sb.append("\n");
			sb.append("具体原因:").append(CommonUtil.getExceptionMsg(e, new String[] { "ccm", "Exception" }));
			email.setContent(sb.toString());

			emailManager.sendExceptionText(email);

			logger.info("recordException end");
		} catch (Exception ex) {
			logger.error(CommonUtil.getExceptionMsg(ex, "ccm"));
		}
		throw new IsSendEmailException(e);
	}
}
