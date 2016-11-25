package com.ccm.mc.webapp.action.common;

import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.ccm.api.util.AesEncrypt;
import com.ccm.api.util.CommonUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
/**
 * 
 * @author chay
 *
 */
public class UniqueCodeAction extends BaseAction {
	private Log log = LogFactory.getLog(UniqueCodeAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -4850984591577847181L;

	/**
	 * 生成一个加密后的唯一码，
	 */
	public void uniqueCode(){
		try {
			String key = CommonUtil.getRoundCode(6);
			String uuid =UUID.randomUUID().toString();
			String code = AesEncrypt.encrypt(uuid, key);
			
			getSession().setAttribute("loginKey", key);
			getSession().setAttribute("loginUniqueCode", code);
			ajaxResponse(uuid);
		} catch (Exception e) {
			ajaxResponse("");
			log.error("生成登入唯一码失败："+e);
		}
	}
	
	/**
	 * 校验唯一验证码
	 */
	public void checkUniqueCode(){
		try {
			String uuid = getRequest().getParameter("uuid");
			//无uuid ，清空session 中的唯一码
			if(!StringUtils.hasText(uuid)){
				getSession().removeAttribute("loginKey");
				getSession().removeAttribute("loginUniqueCode");
				return;
			}
			
			String key = (String) getSession().getAttribute("loginKey");;
			String code = (String) getSession().getAttribute("loginUniqueCode");
			
			String encryptCode = AesEncrypt.encrypt(uuid, key);
			//session 中的唯一码与加密后的唯一码不一致，清空session 中的唯一码
			if(!encryptCode.equalsIgnoreCase(code)){
				getSession().removeAttribute("loginKey");
				getSession().removeAttribute("loginUniqueCode");
				return;
			}
			getSession().setAttribute("loginUniqueCodeType", "ok");
		} catch (Exception e) {
			log.error("校验唯一验证码失败："+e);
		}
		
	}
	
}
