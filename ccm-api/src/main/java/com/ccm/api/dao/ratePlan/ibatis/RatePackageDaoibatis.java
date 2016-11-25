package com.ccm.api.dao.ratePlan.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.ccm.api.model.ratePlan.Package;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.ratePlan.RatePackageDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.ratePlan.RatePackage;

@Repository("ratePackageDao")
public class RatePackageDaoibatis extends GenericDaoiBatis<RatePackage, String> implements RatePackageDao {

	public RatePackageDaoibatis() {
		super(RatePackage.class);
	}

    @Override
    public RatePackage addRatePackage(RatePackage ratePackage) {
        return (RatePackage) this.getSqlMapClientTemplate().insert("addRatePackage", ratePackage);
    }

    @Override
    public void deleteRatePackageByRatePlanId(String ratePlanId) {
        getSqlMapClientTemplate().delete("deleteRatePackageByRatePlanId",ratePlanId);
    }

    @Override
    public List<RatePackage> getRatePackageByRatePlanId(String ratePlanId) {
        return this.getRatePackageByRatePlanId(ratePlanId, LanguageCode.MAIN_LANGUAGE_CODE);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<RatePackage> getRatePackageByRatePlanId(String ratePlanId,String language) {
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("ratePlanId", ratePlanId);
    	params.put("language", language);
        return getSqlMapClientTemplate().queryForList("getRatePackageByRatePlanId", params);
    }

    @Override
    public List<Package> getSvcTaxPackageByRatePlanId(String ratePlanId) {
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("ratePlanId", ratePlanId);
        return getSqlMapClientTemplate().queryForList("getSvcTaxPackageByRatePlanId", params);
    }
    
    @Override
    public void saveRatePackageList(List<RatePackage> ratePackageList) {
        if(ratePackageList != null && ratePackageList.size()>0){
            for (RatePackage ratePackage : ratePackageList) {
                this.save(ratePackage);
            }
        }
    }
    
    @Override
    public List<Package> getSvcOrTaxFromRateDetail(Map paramMap){
    	 return getSqlMapClientTemplate().queryForList("getSvcOrTaxFromRateDetail", paramMap);
    }
}
