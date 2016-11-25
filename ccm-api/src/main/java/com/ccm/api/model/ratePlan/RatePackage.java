package com.ccm.api.model.ratePlan;

import com.ccm.api.model.base.BaseObject;

/**
 * 房价打包
 * @author carr
 *
 */
public class RatePackage extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4581586362491303117L;
	
	private String ratePackageId;//序号    
	private String ratePlanId;//房价序号   
	private String packageId;//打包服务序号
	private String packageName;//描述   仅用于显示
	private String packageCode;//code   仅用于显示
	public String getRatePackageId() {
		return ratePackageId;
	}
	public void setRatePackageId(String ratePackageId) {
		this.ratePackageId = ratePackageId;
	}
	public String getRatePlanId() {
		return ratePlanId;
	}
	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	
    public String getPackageName() {
        return packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    
    public String getPackageCode() {
        return packageCode;
    }
    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }
    @Override
	public String toString() {
		return "RatePackage [ratePackageId=" + ratePackageId + ", ratePlanId="
				+ ratePlanId + ", packageId=" + packageId + "]";
	}
}
