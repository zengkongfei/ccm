package com.ccm.mc.webapp.action.ratePlan;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.constant.DictName;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.ratePlan.CancelPolicyI18n;
import com.ccm.api.model.ratePlan.vo.CancelPolicyCriteria;
import com.ccm.api.model.ratePlan.vo.CancelPolicyResult;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.ratePlan.CancelPolicyManager;
import com.ccm.api.util.MoreLanguageUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * 取消规则
 * 
 * @author carr
 * 
 */
public class CancelPolicyAction extends BaseAction {

	private static final long serialVersionUID = -6669082229779197391L;

	@Autowired
	private CancelPolicyManager cancelPolicyManager;
	@Autowired
	private DictCodeManager dictCodeManager;

	private CancelPolicyCriteria criteria;
	private CancelPolicyResult result;
	private CancelPolicyVO cancelPolicyvo;
	private List<DictCode> languageList;

	/**
	 * 取消规则列表显示
	 */
	public String list() {

		if (null == criteria) {
			criteria = new CancelPolicyCriteria();
		}
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		criteria.setLanguage(language);
		int pageSize = this.getCurrentPageSize("cancelList");
		int pageNo = this.getCurrentPageNo("cancelList");
		criteria.setPageNum(pageNo);
		criteria.setPageSize(pageSize);
		result = cancelPolicyManager.searchCancelPolicy(criteria);
		return "list";
	}

	/**
	 * 创建/编辑
	 */
	public String toEdit() {
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		languageList = dictCodeManager.searchByDictName(DictName.MORELANGUAGE,language);

		// 筛选不需要的语言
		String[] languageFilter = { language};

		// 将中文编码的多语言去除(默认的就是中国)
		for (int i = 0; i < languageList.size(); i++) {
			for (String lf : languageFilter) {
				if (lf.equalsIgnoreCase(languageList.get(i).getCodeNo())) {
					languageList.remove(i);
				}
			}
		}

		if (null != cancelPolicyvo && null != cancelPolicyvo.getCancelId()) {
			cancelPolicyvo = cancelPolicyManager.getCancelPolicyById(cancelPolicyvo.getCancelId(),language);

			// 设置多语言列表
			List<CancelPolicyI18n> cancelPolicyI18nList = cancelPolicyManager.getCancelPolicyI18ns(cancelPolicyvo.getCancelId());

			if (cancelPolicyI18nList != null && cancelPolicyI18nList.size() > 0) {
				for (int i = 0; i < cancelPolicyI18nList.size(); i++) {
					if (language.equals(cancelPolicyI18nList.get(i).getLanguage())) {
						cancelPolicyI18nList.remove(i);
						break;
					}
				}
			}
			cancelPolicyvo.setCancelPolicyI18nList(cancelPolicyI18nList);
		} else {
			cancelPolicyvo = new CancelPolicyVO();
		}
		
		List<String> offsetDropTime = new ArrayList<String>();
		offsetDropTime.add("BeforeArrival");
		offsetDropTime.add("AfterBooking");
		offsetDropTime.add("AfterConfirmation");
		this.getRequest().setAttribute("offsetDropTime", offsetDropTime);
		
		List<String> offsetUnitMultiplier = new ArrayList<String>();
		offsetUnitMultiplier.add("days");
		offsetUnitMultiplier.add("hours");
		this.getRequest().setAttribute("offsetUnitMultiplier", offsetUnitMultiplier);
		
		List<String> basisType = new ArrayList<String>();
		basisType.add("FullStay");
		basisType.add("Nights");
		basisType.add("FirstLast");
		this.getRequest().setAttribute("basisType", basisType);
		
		
		return "toEdit";
	}

	/**
	 * 保存/修改
	 * 
	 * @throws ParseException
	 */
	public String save() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		if (null != cancelPolicyvo) {
			// 设置日期
			String absoluteTime = cancelPolicyvo.getAbsoluteTime();
			if (null != absoluteTime && !"".equals(absoluteTime)) {
				date = sdf.parse(absoluteTime);
			}
			cancelPolicyvo.setAbsoluteDeadline(date);

			String cancelPolicyI18ns = getRequest().getParameter("f_cancelPolicyI18ns");

			// 保存多语言列表
			List<CancelPolicyI18n> cancelPolicyI18nList = new ArrayList<CancelPolicyI18n>();
			try {
				Locale locale = ActionContext.getContext().getLocale();
				String language = locale.getLanguage()+"_"+locale.getCountry();
				cancelPolicyvo.setLanguage(language);
				// 将默认语言对象添加进去
				cancelPolicyI18nList.add(cancelPolicyManager.getDefaultLanguageI18n(cancelPolicyvo));
				// 组装多语言数据
				if (StringUtils.isNotBlank(cancelPolicyI18ns)) {
					List<Map<String, String>> i18nMapList = MoreLanguageUtil.rebuildI18nMapList(cancelPolicyI18ns);

					for (Map<String, String> i18nMap : i18nMapList) {
						// 创建多语言对象,并且设置值
						CancelPolicyI18n i18n = new CancelPolicyI18n();
						i18n.setLanguage(i18nMap.get("codeNo"));
						i18n.setPolicyName(i18nMap.get("name"));
						i18n.setDescription(i18nMap.get("description"));
						cancelPolicyI18nList.add(i18n);
					}
				}
				// 将多语言设置进去
				cancelPolicyvo.setCancelPolicyI18nList(cancelPolicyI18nList);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (null != cancelPolicyvo.getCancelId() && !"".equals(cancelPolicyvo.getCancelId())) {
				cancelPolicyManager.updateCancelPolicy(cancelPolicyvo);
			} else {
				cancelPolicyManager.saveCancelPolicy(cancelPolicyvo);
			}
		}
		return "save";
	}

	/**
	 * 验证取消规则代码是否存在 true:表示不存在，可以保存；false：不能保存
	 * 
	 * @throws IOException
	 */
	public void isCancelPolicyCode() throws IOException {
		String message = "";
		cancelPolicyvo = cancelPolicyManager.getCancelPolicyByCode(cancelPolicyvo.getCancelPolicyCode());
		if (null == cancelPolicyvo) {
			message = "true";
		} else {
			message = "false";
		}
		this.getResponse().getWriter().print(message);
	}

	/**
	 * 删除
	 * 
	 * @throws IOException
	 */
	public String delete() {
		cancelPolicyvo.setDelFlag(true);
		cancelPolicyManager.deleteCancelPolicy(cancelPolicyvo);
		return "delete";
	}

	public CancelPolicyCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(CancelPolicyCriteria criteria) {
		this.criteria = criteria;
	}

	public CancelPolicyResult getResult() {
		return result;
	}

	public void setResult(CancelPolicyResult result) {
		this.result = result;
	}

	public CancelPolicyVO getCancelPolicyvo() {
		return cancelPolicyvo;
	}

	public void setCancelPolicyvo(CancelPolicyVO cancelPolicyvo) {
		this.cancelPolicyvo = cancelPolicyvo;
	}

	public List<DictCode> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<DictCode> languageList) {
		this.languageList = languageList;
	}

}
