package com.ccm.api.model.channel.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.compass.core.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.RateCancelRelationship;
import com.ccm.api.model.ratePlan.RateGuaranteeRelationship;
import com.ccm.api.model.ratePlan.RatePackage;
import com.ccm.api.model.ratePlan.RatePlanI18n;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyVO;
import com.ccm.api.model.ratePlan.vo.PackageVO;
import com.ccm.api.model.ratePlan.vo.RateDetailVO;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.MoreLanguageUtil;

public class RatePlanVO implements Serializable {

	private static final long serialVersionUID = 3046538723433155033L;
	public static final String RATE_SESSION_NAME = "ratePlan";
	private RatePlanI18n rpi18n;
	private Rateplan rp;
	private String ratePlanType;
	private String sourceDescription;
	private String marketDescription;
	private String roomTypeCode;// 第一步房型 code1,code2
	private String packageId;// 房价打包服务id a,b,c

	private String guaranteeJsonArr;// 担保规则json字符串
	private String cancelRuleJsonArr;// 取消规则json字符串
	private String rateDetailListStr;// 房价json字符串
	private String languageJsonArr;  //多语言json字符串
	private String ratePlanId;
	private String ratePlanName;
	private String ratePlanCode;// 房价代码
	private Integer cancelType;// 取消类型，TB使用
	private String language;   //语言
	private BigDecimal price;  //价格(均价)

	private List<RatePackage> ratePackageList;
	private List<RateGuaranteeRelationship> guarrnteeList;
	private List<RateCancelRelationship> cancelRuleList;
	private List<RateDetailVO> rateDetailVOList;
	private List<PackageVO> packageVOList;
	private List<RatePlanI18n> ratePlanI18nList; //多语言列表
	private List<PriceCalc> priceCalcList;   //房价日历列表
	private List<RoomTypeVO> roomTypeVoList; //房型Vo列表
	private List<GuaranteePolicyVO> guaranteePolicyVoList; //担保规则列表
	private List<CancelPolicyVO> cancelPolicyVoList; //取消规则列表
	private Date maxDetailExpireDate;
	private String exception;// 异常错误
	
	private List<String> customList;
	private String hotelCode;
	private String chainCode;
	private String handleRateDetailType;
	private Date effectiveDate;
	private Date expireDate;
	private String productStatusType;//静态全量推送，上线/下线  非数据库字段
	
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getChainCode() {
		return chainCode;
	}

	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}

	public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public RatePlanI18n getRpi18n() {
		return rpi18n;
	}

	public void setRpi18n(RatePlanI18n rpi18n) {
		this.rpi18n = rpi18n;
	}

	public Date getMaxDetailExpireDate() {
        return maxDetailExpireDate;
    }

    public void setMaxDetailExpireDate(Date maxDetailExpireDate) {
        this.maxDetailExpireDate = maxDetailExpireDate;
    }

    public String getRoomTypeCode() {
		return roomTypeCode;
	}

	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}

    public String getPackageId() {
        if (CommonUtil.isNotEmpty(getRatePackageList())) {
            String pkIds = "";
            for (int i=0;i<getRatePackageList().size();i++) {
                RatePackage rp = getRatePackageList().get(i);
                pkIds += rp.getPackageId();
                if(i!= getRatePackageList().size()){
                    pkIds += ",";
                }
            }
            packageId = pkIds;
        }
        return packageId;
    }

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public List<RatePackage> getRatePackageList() {
		if (this.ratePackageList != null && this.ratePackageList.size() > 0) {
			for (RatePackage ratePackage : this.ratePackageList) {
				if(!StringUtils.hasText(ratePackage.getRatePlanId())){
					ratePackage.setRatePlanId(rp.getRatePlanId());
				}
			}
		} else {
			if (StringUtils.hasText(packageId)) {
				ratePackageList = new ArrayList<RatePackage>();
				String[] pks = packageId.split(",");
				for (String pk : pks) {
					RatePackage ratePackage = new RatePackage();
					ratePackage.setPackageId(pk);
					ratePackage.setRatePlanId(rp.getRatePlanId());
					ratePackageList.add(ratePackage);
				}
			}
			
		}
		return this.ratePackageList;
	}

	public void setRatePackageList(List<RatePackage> ratePackageList) {
		this.ratePackageList = ratePackageList;
	}

	public String getGuaranteeJsonArr() {
		return guaranteeJsonArr;
	}

	public void setGuaranteeJsonArr(String guaranteeJsonArr) {
		this.guaranteeJsonArr = guaranteeJsonArr;
	}

	public String getCancelRuleJsonArr() {
		return cancelRuleJsonArr;
	}

	public void setCancelRuleJsonArr(String cancelRuleJsonArr) {
		this.cancelRuleJsonArr = cancelRuleJsonArr;
	}

	public List<RateGuaranteeRelationship> getGuarrnteeList() {
		if (this.guarrnteeList != null && this.guarrnteeList.size() > 0) {
			return this.guarrnteeList;
		}
		return StringUtils.hasText(guaranteeJsonArr) ? JSON.parseObject(guaranteeJsonArr, new TypeReference<ArrayList<RateGuaranteeRelationship>>() {
		}) : new ArrayList<RateGuaranteeRelationship>();
	}

	public void setGuarrnteeList(List<RateGuaranteeRelationship> guarrnteeList) {
		this.guarrnteeList = guarrnteeList;
	}

	public Rateplan getRp() {
		return rp;
	}

	public void setRp(Rateplan rp) {
		this.rp = rp;
	}

	public String getRateDetailListStr() {
		return rateDetailListStr;
	}

	public void setRateDetailListStr(String rateDetailListStr) {
		this.rateDetailListStr = rateDetailListStr;
	}

	public List<RateDetailVO> getRateDetailVOList() {
		if (this.rateDetailVOList != null && this.rateDetailVOList.size() > 0) {
			return this.rateDetailVOList;
		}
		return StringUtils.hasText(rateDetailListStr) ? JSON.parseObject(rateDetailListStr, new TypeReference<ArrayList<RateDetailVO>>() {
		}) : new ArrayList<RateDetailVO>();
	}

	public void setRateDetailVOList(List<RateDetailVO> rateDetailVOList) {
		this.rateDetailVOList = rateDetailVOList;
	}

	public List<RateCancelRelationship> getCancelRuleList() {
		if (cancelRuleList != null && cancelRuleList.size() > 0) {
			return cancelRuleList;
		}
		return StringUtils.hasText(cancelRuleJsonArr) ? JSON.parseObject(cancelRuleJsonArr, new TypeReference<ArrayList<RateCancelRelationship>>() {
		}) : new ArrayList<RateCancelRelationship>();
	}

	public void setCancelRuleList(List<RateCancelRelationship> cancelRuleList) {
		this.cancelRuleList = cancelRuleList;
	}

	public String getSourceDescription() {
		return sourceDescription;
	}

	public void setSourceDescription(String sourceDescription) {
		this.sourceDescription = sourceDescription;
	}

	public String getMarketDescription() {
		return marketDescription;
	}

	public void setMarketDescription(String marketDescription) {
		this.marketDescription = marketDescription;
	}

	public String getRatePlanType() {
		return ratePlanType;
	}

	public void setRatePlanType(String ratePlanType) {
		this.ratePlanType = ratePlanType;
	}

	public String getRatePlanId() {
		return ratePlanId;
	}

	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}

	public Integer getCancelType() {
		return cancelType;
	}

	public void setCancelType(Integer cancelType) {
		this.cancelType = cancelType;
	}

	public List<PackageVO> getPackageVOList() {
		return packageVOList;
	}

	public void setPackageVOList(List<PackageVO> packageVOList) {
		this.packageVOList = packageVOList;
	}

	public String getRatePlanName() {
        return ratePlanName;
    }

    public void setRatePlanName(String ratePlanName) {
        this.ratePlanName = ratePlanName;
    }

    public List<String> getCustomList() {
		return customList;
	}

	public void setCustomList(List<String> customList) {
		this.customList = customList;
	}
	
	public List<RatePlanI18n> getRatePlanI18nList() {
		//如果存在记录
		if(ratePlanI18nList!=null&&ratePlanI18nList.size()>0){
			return ratePlanI18nList;
		}
		
		//如果具有json格式的多语言字符串
		if(StringUtils.hasText(languageJsonArr)){
			List<Map<String, String>> i18nMapList = MoreLanguageUtil.rebuildI18nMapList(languageJsonArr);
			List<RatePlanI18n> i18nList = new ArrayList<RatePlanI18n>();
			
			for (Map<String, String> i18nMap : i18nMapList) {
				//创建多语言对象,并且设置值
				RatePlanI18n i18n = new RatePlanI18n();
				i18n.setLanguage(i18nMap.get("codeNo"));
				i18n.setRatePlanName(i18nMap.get("ratePlanName"));
				i18n.setDescription(i18nMap.get("description"));
				i18nList.add(i18n);
			}
			return i18nList;
		}
		
		return new ArrayList<RatePlanI18n>();
	}

	public void setRatePlanI18nList(List<RatePlanI18n> ratePlanI18nList) {
		this.ratePlanI18nList = ratePlanI18nList;
	}

	public String getLanguageJsonArr() {
		return languageJsonArr;
	}

	public void setLanguageJsonArr(String languageJsonArr) {
		this.languageJsonArr = languageJsonArr;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<PriceCalc> getPriceCalcList() {
		if(priceCalcList==null){
			priceCalcList = new ArrayList<PriceCalc>();
		}
		return priceCalcList;
	}

	public void setPriceCalcList(List<PriceCalc> priceCalcList) {
		this.priceCalcList = priceCalcList;
	}

	public List<RoomTypeVO> getRoomTypeVoList() {
		if(roomTypeVoList==null){
			roomTypeVoList = new ArrayList<RoomTypeVO>();
		}
		return roomTypeVoList;
	}

	public void setRoomTypeVoList(List<RoomTypeVO> roomTypeVoList) {
		this.roomTypeVoList = roomTypeVoList;
	}

	public List<GuaranteePolicyVO> getGuaranteePolicyVoList() {
		return guaranteePolicyVoList;
	}

	public void setGuaranteePolicyVoList(
			List<GuaranteePolicyVO> guaranteePolicyVoList) {
		this.guaranteePolicyVoList = guaranteePolicyVoList;
	}

	public List<CancelPolicyVO> getCancelPolicyVoList() {
		return cancelPolicyVoList;
	}

	public void setCancelPolicyVoList(List<CancelPolicyVO> cancelPolicyVoList) {
		this.cancelPolicyVoList = cancelPolicyVoList;
	}

	public String getHandleRateDetailType() {
		return handleRateDetailType;
	}

	public void setHandleRateDetailType(String handleRateDetailType) {
		this.handleRateDetailType = handleRateDetailType;
	}

	public String getRatePlanCode() {
		return ratePlanCode;
	}

	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}

	public String getProductStatusType() {
		return productStatusType;
	}

	public void setProductStatusType(String productStatusType) {
		this.productStatusType = productStatusType;
	}

	@Override
	public String toString() {
		return "RatePlanVO [rpi18n=" + rpi18n + ", rp=" + rp
				+ ", ratePlanType=" + ratePlanType + ", sourceDescription="
				+ sourceDescription + ", marketDescription="
				+ marketDescription + ", roomTypeCode=" + roomTypeCode
				+ ", packageId=" + packageId + ", guaranteeJsonArr="
				+ guaranteeJsonArr + ", cancelRuleJsonArr=" + cancelRuleJsonArr
				+ ", rateDetailListStr=" + rateDetailListStr
				+ ", languageJsonArr=" + languageJsonArr + ", ratePlanId="
				+ ratePlanId + ", ratePlanName=" + ratePlanName
				+ ", ratePlanCode=" + ratePlanCode + ", cancelType="
				+ cancelType + ", language=" + language + ", price=" + price
				+ ", ratePackageList=" + ratePackageList + ", guarrnteeList="
				+ guarrnteeList + ", cancelRuleList=" + cancelRuleList
				+ ", rateDetailVOList=" + rateDetailVOList + ", packageVOList="
				+ packageVOList + ", ratePlanI18nList=" + ratePlanI18nList
				+ ", priceCalcList=" + priceCalcList + ", roomTypeVoList="
				+ roomTypeVoList + ", guaranteePolicyVoList="
				+ guaranteePolicyVoList + ", cancelPolicyVoList="
				+ cancelPolicyVoList + ", maxDetailExpireDate="
				+ maxDetailExpireDate + ", exception=" + exception
				+ ", customList=" + customList + ", hotelCode=" + hotelCode
				+ ", chainCode=" + chainCode + ", handleRateDetailType="
				+ handleRateDetailType + ", effectiveDate=" + effectiveDate
				+ ", expireDate=" + expireDate + ", productStatusType="
				+ productStatusType + "]";
	}



}
