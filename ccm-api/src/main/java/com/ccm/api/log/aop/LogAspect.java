package com.ccm.api.log.aop;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.base.ibatis.IBatisDaoUtils;
import com.ccm.api.log.service.ILogService;
import com.ccm.api.log.util.AopUtil;
import com.ccm.api.log.util.ObjectComparator;
import com.ccm.api.log.vo.LogVO;
import com.ccm.api.model.common.Menu;
import com.ccm.api.model.email.vo.EmailVO;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.common.MenuManager;
import com.ccm.api.service.email.EmailManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

public class LogAspect {
	protected static final Log log = LogFactory.getLog(LogAspect.class);
	@Resource
	private ILogService logService;
	@Resource
	private MenuManager menuManager;
	@Resource
	private EmailManager emailManager;

	/**
	 * 记录日志信息 监测dao层的所有方法
	 * 
	 * @param jp
	 */
	@SuppressWarnings("unchecked")
	public void saveLog(JoinPoint jp) {
		B2BUser curLoginUser = null;// 取登陆用户信息
		try {
			curLoginUser = SecurityHolder.getB2BUser();
		} catch (Exception e1) {
		}
		String methodName = jp.getSignature().getName();// 获取目标方法签名 ;

		if (methodName.equals("batchSave")) {
			return;
		}
		if (methodName.indexOf("ChannelGoods") > -1) {
			return;
		}

		int operateType = 0;// 取操作类型
		if (methodName.indexOf("save") == 0 || methodName.indexOf("add") == 0) {
			operateType = 1;
		} else if (methodName.indexOf("update") == 0) {
			operateType = 2;
		} else if (methodName.indexOf("delete") == 0 || methodName.indexOf("remove") == 0) {
			operateType = 3;
		} else {
			operateType = 0;
		}
		// 如果新增/修改/删除并且登陆对象不为空时切入
		if (operateType != 0 && null != curLoginUser) {
			try {
				Object[] parames = jp.getArgs();// 获取目标方法体参数
				String parameName = parames[0].getClass().getName();// 参数类型全名称
				String entityName = AopUtil.getParameName(parames[0]);// 参数类型名称
				if (AopUtil.excludeTable(parames)) {
					String primaryKeyName = IBatisDaoUtils.getPrimaryKeyFieldName(parames[0]);// 取主键名称
					Object primaryKey = IBatisDaoUtils.getPrimaryKeyValue(parames[0]);// 取主键
					if (null == primaryKey) {
						primaryKey = "";
					}
					HttpSession session = SecurityHolder.getSession();// 获取Session
					String topMenu = (String) session.getAttribute("menuId");// 一级菜单
					String twoMenu = (String) session.getAttribute("tmenuId");// 二级菜单
					// 判断二级菜单是否存在
					Menu menu = menuManager.getMenuByParentId(twoMenu, topMenu);
					if (null == menu) {
						twoMenu = "";
						return;
					}
					HotelVO hotelvo = curLoginUser.getHotelvo();// 取酒店信息
					Map<String, Object> oldMap = new HashMap<String, Object>();// 老数据
					Map<String, Object> newMap = new HashMap<String, Object>();// 新数据
					Object obj = null;
					if (operateType == 1) {
						obj = Class.forName(parameName).newInstance();// 创建空对象
						oldMap = PropertyUtils.describe(obj);// 对象转Map
					} else {
						try {
							oldMap = logService.searchObjectInfo(entityName, primaryKeyName, primaryKey);
						} catch (Exception e) {
							entityName = AopUtil.stringConvert(entityName);
							oldMap = logService.searchObjectInfo(entityName, primaryKeyName, primaryKey);
						}
						if (null == oldMap) {
							oldMap = new HashMap<String, Object>();
						}
					}
					newMap = PropertyUtils.describe(parames[0]);// Java对象转换为Map

					List<String> changeList = ObjectComparator.mapComparator(newMap, oldMap);// 比较对象
					// 组装日志保存对象
					LogVO logvo = new LogVO();
					logvo.setOperaterId(curLoginUser.getUserId());
					logvo.setHotelId(hotelvo.getHotelId());
					logvo.setLastLoginTime(curLoginUser.getLastLoginTime());
					logvo.setOperateTime(new Date());
					logvo.setBusinessId(topMenu);
					logvo.setFunctionId(twoMenu);
					logvo.setOperateType(String.valueOf(operateType));
					logvo.setChangeList(changeList);
					logvo.setEntityName(AopUtil.getParameName(parames[0]));
					logvo.setPrimaryKey(primaryKey.toString());
					logService.insertLog(logvo);
				}
			} catch (Exception e) {
				log.error(methodName + "!!!!!!AOP保存日志出错:" + e.getMessage());
				// e.printStackTrace();
			}
		}
	}

	public void throwAdvice(JoinPoint jp, Exception e) {
		String methodName = jp.getSignature().getName();// 获取目标方法签名 ;
		String className = jp.getSignature().getDeclaringType().getCanonicalName();
		String errTime = DateUtil.getDateTime(DateUtil.getDateTimePattern(), new Date());
		StringBuffer errMsg = new StringBuffer();
		Object[] args = jp.getArgs();// 获取目标方法体参数
		errMsg.append("*******************************************************************************");
		errMsg.append("\nError happened in method: " + className + "." + methodName);
		errMsg.append("\nTime: " + errTime);
		for (int i = 0; i < args.length; i++) {
			errMsg.append("\narg[" + i + "]: " + args[i]);
		}
		errMsg.append("\nException type: " + e.getClass().getSimpleName());
		errMsg.append("\nException msg: " + CommonUtil.getExceptionMsg(e, "ccm"));
		errMsg.append("*******************************************************************************");
		// 发送邮件
		if (e instanceof SQLException) {
			EmailVO emailVO = new EmailVO();
			emailVO.setTo("maintain@lesuke.com");
			emailVO.setContent(errMsg.toString());
			emailVO.setSubject(catchLocalIP() + " CCM-MC SYSTEM: " + e.getClass().getSimpleName() + " " + errTime);
			emailManager.sendText(emailVO);
		}
		System.out.println(errMsg);
	}

	// 取得本机IP地址
	public InetAddress catchLocalIP() {
		InetAddress LocalIP = null;
		try {
			LocalIP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			log.info(e.getMessage());
		}
		return LocalIP;
	}
}
