/**
 * 
 */
package com.ccm.api.service.ota;

import java.util.List;

import org.opentravel.ota._2003._05.HotelRatePlanType.BookingRules;
import org.opentravel.ota._2003._05.HotelRatePlanType.Rates.Rate;
import org.opentravel.ota._2003._05.ParagraphType;
import org.opentravel.ota._2003._05.SellableProductsType;
import org.opentravel.ota._2003._05.TPAExtensionsType;

import com.ccm.api.model.ratePlan.GuaranteePolicy;
import com.ccm.api.model.ratePlan.SoldableCondition;

/**
 * @author Jenny
 * 
 */
public interface HotelRatePlanManager {

	BookingRules buildBookingRules(List<SoldableCondition> scList, SellableProductsType sellableProducts, Rate rate) throws Exception;

	ParagraphType buildDescription(String ratePlanName, String language);

	TPAExtensionsType buildTPAExtensions(String rateCode, String ratePlanId, String channelId, String language, Rate rate, GuaranteePolicy gp) throws Exception;

}
