package com.ccm.api.service.taobaoAPI2.impl;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ccm.api.SecurityHolder;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.GoodsVO;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.common.vo.InvokeResponse;
import com.ccm.api.model.constant.TaobaoApi;
import com.ccm.api.model.ratePlan.RatePlanI18n;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.service.taobaoAPI2.TaobaoApiManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.taobao.api.FileItem;
import com.taobao.api.domain.Rate;
import com.taobao.api.domain.RatePlan;
import com.taobao.api.domain.XHotel;
import com.taobao.api.domain.XRoom;
import com.taobao.api.domain.XRoomType;
import com.taobao.api.request.XhotelAddRequest;
import com.taobao.api.request.XhotelConfirmRequest;
import com.taobao.api.request.XhotelGetRequest;
//import com.taobao.api.request.XhotelOrderAlipayfaceUpdateRequest;
import com.taobao.api.request.XhotelOrderAlipayfaceUpdateRequest;
import com.taobao.api.request.XhotelRateAddRequest;
import com.taobao.api.request.XhotelRateGetRequest;
import com.taobao.api.request.XhotelRateUpdateRequest;
import com.taobao.api.request.XhotelRateplanAddRequest;
import com.taobao.api.request.XhotelRateplanGetRequest;
import com.taobao.api.request.XhotelRateplanUpdateRequest;
import com.taobao.api.request.XhotelRatesUpdateRequest;
import com.taobao.api.request.XhotelRoomAddRequest;
import com.taobao.api.request.XhotelRoomGetRequest;
import com.taobao.api.request.XhotelRoomUpdateRequest;
import com.taobao.api.request.XhotelRoomtypeAddRequest;
import com.taobao.api.request.XhotelRoomtypeConfirmRequest;
import com.taobao.api.request.XhotelRoomtypeGetRequest;
import com.taobao.api.request.XhotelRoomtypeUpdateRequest;
import com.taobao.api.request.XhotelUpdateRequest;
import com.taobao.api.response.XhotelAddResponse;
import com.taobao.api.response.XhotelConfirmResponse;
import com.taobao.api.response.XhotelGetResponse;
//import com.taobao.api.response.XhotelOrderAlipayfaceUpdateResponse;
import com.taobao.api.response.XhotelOrderAlipayfaceUpdateResponse;
import com.taobao.api.response.XhotelRateAddResponse;
import com.taobao.api.response.XhotelRateGetResponse;
import com.taobao.api.response.XhotelRateUpdateResponse;
import com.taobao.api.response.XhotelRateplanAddResponse;
import com.taobao.api.response.XhotelRateplanGetResponse;
import com.taobao.api.response.XhotelRateplanUpdateResponse;
import com.taobao.api.response.XhotelRatesUpdateResponse;
import com.taobao.api.response.XhotelRoomAddResponse;
import com.taobao.api.response.XhotelRoomGetResponse;
import com.taobao.api.response.XhotelRoomUpdateResponse;
import com.taobao.api.response.XhotelRoomtypeAddResponse;
import com.taobao.api.response.XhotelRoomtypeConfirmResponse;
import com.taobao.api.response.XhotelRoomtypeGetResponse;
import com.taobao.api.response.XhotelRoomtypeUpdateResponse;
import com.taobao.api.response.XhotelUpdateResponse;
 
@Service("taobaoApiManager")
public class TaobaoApiManagerImpl implements TaobaoApiManager {
    private Log log = LogFactory.getLog(TaobaoApiManagerImpl.class);
  //同步调用
    @Override
    public InvokeResponse hotelAdd(XhotelAddRequest req)throws Exception {
        return this.hotelAdd(req, getSessionKey());
    }
  //异步调用
    @Override
    public InvokeResponse hotelAdd(XhotelAddRequest req,String sessionKey)throws Exception {
        XhotelAddResponse response = null;
            InvokeResponse invokeRes = new InvokeResponse();
            response = TaobaoApi.taobaoClient.execute(req, sessionKey);
            if(null!=response.getErrorCode()){
                invokeRes.setSuccess(false);
                if(CommonUtil.isEmpty(response.getSubMsg())){
                    if(CommonUtil.isNotEmpty(response.getMsg()))
                    invokeRes.setErrMsg(response.getMsg().substring(response.getMsg().indexOf(":")+1));
                }else{
                    invokeRes.setErrMsg(response.getSubMsg());
                }
                invokeRes.setErrCode(response.getSubCode());
                log.error("淘宝发布酒店失败："+response.getSubMsg());
            }else{
                invokeRes.setSuccess(true);
                invokeRes.setResObj(response.getXhotel());
            }
            logInfo(req,response,invokeRes);
            return invokeRes;
    }
    
    @Override
    public XHotel hotelGet(String hid) throws Exception {
        XhotelGetRequest req = new XhotelGetRequest();
        req.setHid(Long.parseLong(hid));
        XhotelGetResponse response = TaobaoApi.taobaoClient.execute(req, getSessionKey());
        return response.getXhotel();
    }
    @Override
    public String getSessionKey(){
        if("21754351".equals(TaobaoApi.APP_KEY)){
            return SecurityHolder.getB2BUser().getSessionKey();
        }else if("1021754351".equals(TaobaoApi.APP_KEY)){
            return "6102107ad7fb0891744f22b9a4e50a270d14f04d1e1653f2049618273";
        }
        return "";
    }

    @Override
    public InvokeResponse hotelConfirm(String hid, Long confirm)
            throws Exception {
        InvokeResponse invokeRes = new InvokeResponse();
        XhotelConfirmRequest req = new XhotelConfirmRequest();
        req.setHid(Long.parseLong(hid));
        req.setConfirm(confirm);
        XhotelConfirmResponse response = TaobaoApi.taobaoClient.execute(req, getSessionKey());
        if(null != response.getErrorCode()){
            invokeRes.setSuccess(false);
            if(CommonUtil.isEmpty(response.getSubMsg())){
                if(CommonUtil.isNotEmpty(response.getMsg()))
                invokeRes.setErrMsg(response.getMsg().substring(response.getMsg().indexOf(":")+1));
            }else{
                invokeRes.setErrMsg(response.getSubMsg());
            }
            invokeRes.setErrCode(response.getSubCode());
            log.error("淘宝酒店确认失败："+response.getSubMsg());
            logInfo(req,response,invokeRes);
        }else{
//            1：确认成功 2：确认失败，原因为不需要确认
            if(1==response.getConfirmResult()){
                invokeRes.setSuccess(true);
            }else{
                invokeRes.setSuccess(false);
                invokeRes.setErrMsg("确认失败，原因为不需要确认");
            }
        }
        return invokeRes;
    }

    @Override
    public InvokeResponse hotelUpdate(XhotelUpdateRequest req,String sessionKey) throws Exception {
        XhotelUpdateResponse response = null;
        InvokeResponse invokeRes = new InvokeResponse();
        response = TaobaoApi.taobaoClient.execute(req, sessionKey);
        if(null!=response.getErrorCode()){
            invokeRes.setSuccess(false);
            if(CommonUtil.isEmpty(response.getSubMsg())){
                if(CommonUtil.isNotEmpty(response.getMsg()))
                invokeRes.setErrMsg(response.getMsg().substring(response.getMsg().indexOf(":")+1));
            }else{
                invokeRes.setErrMsg(response.getSubMsg());
            }
            invokeRes.setErrCode(response.getSubCode());
            log.error("淘宝更新酒店失败："+response.getSubMsg());
        }else{
            invokeRes.setSuccess(true);
            invokeRes.setResObj(response.getXhotel());
        }
        logInfo(req,response,invokeRes);
        return invokeRes;
    }

    @Override
    public InvokeResponse roomTypeAdd(RoomTypeVO roomTypeVO)throws Exception {
        InvokeResponse invokeRes = new InvokeResponse();
        XhotelRoomtypeAddRequest req = new XhotelRoomtypeAddRequest();
        req.setOuterId(roomTypeVO.getOuterId());
        req.setHid(Long.parseLong(roomTypeVO.getChannelHotelCode()));
        req.setName(roomTypeVO.getRoomTypeName());
        req.setMaxOccupancy(roomTypeVO.getMaxOccupancy().longValue());
        req.setArea(roomTypeVO.getArea());
        req.setFloor(roomTypeVO.getFloor());
        req.setBedType(roomTypeVO.getBed_type());
        req.setBedSize(roomTypeVO.getBed_size());
        req.setInternet(roomTypeVO.getInternet());
        req.setService(roomTypeVO.getServiceJson());
        XhotelRoomtypeAddResponse response = TaobaoApi.taobaoClient.execute(req, getSessionKey());
        if(null!=response.getErrorCode()){
            invokeRes.setSuccess(false);
            if(CommonUtil.isEmpty(response.getSubMsg())){
                if(CommonUtil.isNotEmpty(response.getMsg()))
                invokeRes.setErrMsg(response.getMsg().substring(response.getMsg().indexOf(":")+1));
            }else{
                invokeRes.setErrMsg(response.getSubMsg());
            }
            invokeRes.setErrCode(response.getSubCode());
            log.error("淘宝房型添加失败："+response.getSubMsg());
        }else{
            invokeRes.setSuccess(true);
            invokeRes.setResObj(response.getXroomtype());
        }
        logInfo(req,response,invokeRes);
        return invokeRes;
    }

    @Override
    public InvokeResponse roomTypeConfirm(String rid, Long confirm)
            throws Exception {
        InvokeResponse invokeRes = new InvokeResponse();
        XhotelRoomtypeConfirmRequest req = new XhotelRoomtypeConfirmRequest();
        req.setRid(Long.parseLong(rid));
        req.setConfirm(confirm);
        XhotelRoomtypeConfirmResponse response = TaobaoApi.taobaoClient.execute(req, getSessionKey());
        if(null != response.getErrorCode()){
            invokeRes.setSuccess(false);
            if(CommonUtil.isEmpty(response.getSubMsg())){
                if(CommonUtil.isNotEmpty(response.getMsg()))
                invokeRes.setErrMsg(response.getMsg().substring(response.getMsg().indexOf(":")+1));
            }else{
                invokeRes.setErrMsg(response.getSubMsg());
            }
            invokeRes.setErrCode(response.getSubCode());
            log.error("淘宝房型确认失败：rid："+rid+" "+response.getSubMsg());
            logInfo(req,response,invokeRes);
        }else{
//            1：确认成功 2：确认失败，原因为不需要确认
            if(1==response.getConfirmResult()){
                invokeRes.setSuccess(true);
            }else{
                invokeRes.setSuccess(false);
                invokeRes.setErrMsg("确认失败，原因为不需要确认");
            }
        }
        return invokeRes;
    }

    @Override
    public InvokeResponse roomTypeUpdate(RoomTypeVO roomTypeVO)
            throws Exception {
        InvokeResponse invokeRes = new InvokeResponse();
        XhotelRoomtypeUpdateRequest req = new XhotelRoomtypeUpdateRequest();
        req.setRid(Long.parseLong(roomTypeVO.getChannelRoomTypeCode()));
        req.setName(roomTypeVO.getRoomTypeName());
        req.setMaxOccupancy(roomTypeVO.getMaxOccupancy().longValue());
        req.setArea(roomTypeVO.getArea());
        req.setFloor(roomTypeVO.getFloor());
        req.setBedType(roomTypeVO.getBed_type());
        req.setBedSize(roomTypeVO.getBed_size());
        req.setInternet(roomTypeVO.getInternet());
        req.setService(roomTypeVO.getServiceJson());
        XhotelRoomtypeUpdateResponse response = TaobaoApi.taobaoClient.execute(req, getSessionKey());
        if(null!=response.getErrorCode()){
            invokeRes.setSuccess(false);
            if(CommonUtil.isEmpty(response.getSubMsg())){
                if(CommonUtil.isNotEmpty(response.getMsg()))
                invokeRes.setErrMsg(response.getMsg().substring(response.getMsg().indexOf(":")+1));
            }else{
                invokeRes.setErrMsg(response.getSubMsg());
            }
            invokeRes.setErrCode(response.getSubCode());
            log.error("淘宝房型更新失败:"+response.getSubMsg());
        }else{
            invokeRes.setSuccess(true);
            invokeRes.setResObj(response.getXroomtype());
        }
        logInfo(req,response,invokeRes);
        return invokeRes;
    }

    @Override
    public XRoomType roomTypeGet(Long rid) throws Exception {
        log.info("taobao roomTypeGet.... rid "+rid);
        XhotelRoomtypeGetRequest req = new XhotelRoomtypeGetRequest();
        req.setRid(rid);
        XhotelRoomtypeGetResponse res = TaobaoApi.taobaoClient.execute(req, getSessionKey());
        logInfo(req,res,new InvokeResponse());
        return res.getXroomtype();
    }

    @Override
    public InvokeResponse roomAdd(RoomTypeVO roomTypeVO) throws Exception {
        InvokeResponse invokeRes = new InvokeResponse();
        XhotelRoomAddRequest req = new XhotelRoomAddRequest();
        req.setHid(Long.parseLong(roomTypeVO.getChannelHotelCode()));
        req.setRid(Long.parseLong(roomTypeVO.getChannelRoomTypeCode()));
        req.setTitle(roomTypeVO.getBabyName());
        req.setGuide(roomTypeVO.getGuide());
        req.setDesc(roomTypeVO.getDescription());
        req.setHasReceipt(roomTypeVO.getHas_receipt());
        req.setReceiptType(roomTypeVO.getReceipt_type());
        req.setReceiptInfo(roomTypeVO.getReceipt_info());
        
        FileItem fItem = new FileItem(new File(roomTypeVO.getRoomPicUrl()));
        req.setPic(fItem);
        XhotelRoomAddResponse response = TaobaoApi.taobaoClient.execute(req, getSessionKey());
        if(null!=response.getErrorCode()){
            invokeRes.setSuccess(false);
            if(CommonUtil.isEmpty(response.getSubMsg())){
                if(CommonUtil.isNotEmpty(response.getMsg()))
                invokeRes.setErrMsg(response.getMsg().substring(response.getMsg().indexOf(":")+1));
            }else{
                invokeRes.setErrMsg(response.getSubMsg());
            }
            invokeRes.setErrCode(response.getSubCode());
            log.error("淘宝宝贝添加失败:"+response.getSubMsg());
            
        }else{
            invokeRes.setSuccess(true);
            invokeRes.setResObj(response.getGid());
        }
        req.setPic(null);//图片会长度过大记录日志时清空
        logInfo(req,response,invokeRes);
        return invokeRes;
    }
    private void logInfo(Object req, Object res, InvokeResponse invokeRes){
        invokeRes.setReqData(JSON.toJSONString(req));
        log.info("****** taobaoApiReq:"+JSON.toJSONString(req));
        if (CommonUtil.isNotEmpty(res)) {
            invokeRes.setResDate(JSON.toJSONString(res));
            log.info("****** taobaoApiRes:"+JSON.toJSONString(res));
        }else{
            log.info("****** taobaoApiRes:null");
        }
    }
    @Override
    public InvokeResponse roomUpdate(RoomTypeVO roomTypeVO) throws Exception {
        InvokeResponse invokeRes = new InvokeResponse();
        XhotelRoomUpdateRequest req = new XhotelRoomUpdateRequest();
        req.setGid(Long.parseLong(roomTypeVO.getChannelGoodsCode()));
        req.setTitle(roomTypeVO.getBabyName());
        req.setGuide(roomTypeVO.getGuide());
        req.setDesc(roomTypeVO.getDescription());
        req.setHasReceipt(roomTypeVO.getHas_receipt());
        req.setReceiptType(roomTypeVO.getReceipt_type());
        req.setReceiptInfo(roomTypeVO.getReceipt_info());
        
        FileItem fItem = new FileItem(new File(roomTypeVO.getRoomPicUrl()));
        req.setPic(fItem);
        XhotelRoomUpdateResponse response = TaobaoApi.taobaoClient.execute(req, getSessionKey());
        if(null!=response.getErrorCode()){
            invokeRes.setSuccess(false);
            if(CommonUtil.isEmpty(response.getSubMsg())){
                if(CommonUtil.isNotEmpty(response.getMsg()))
                invokeRes.setErrMsg(response.getMsg().substring(response.getMsg().indexOf(":")+1));
            }else{
                invokeRes.setErrMsg(response.getSubMsg());
            }
            invokeRes.setErrCode(response.getSubCode());
            log.error("淘宝宝贝更新失败:"+response.getSubMsg());
        }else{
            invokeRes.setSuccess(true);
            invokeRes.setResObj(response.getGid());
        }
        req.setPic(null);//图片会长度过大记录日志时清空
        logInfo(req,response,invokeRes);
        return invokeRes;
    }

    @Override
    public XRoom roomGet(Long gid) throws Exception {
        XhotelRoomGetRequest req = new XhotelRoomGetRequest();
        req.setGid(gid);
        XhotelRoomGetResponse res = TaobaoApi.taobaoClient.execute(req, getSessionKey());
        return res.getRoom();
    }

    @Override
    public InvokeResponse rateplanAdd(RatePlanVO ratePlanVO) throws Exception {
        InvokeResponse invokeRes = new InvokeResponse();
        XhotelRateplanAddRequest req = new XhotelRateplanAddRequest();
        Rateplan rp = ratePlanVO.getRp();
        RatePlanI18n  rpi18n = ratePlanVO.getRpi18n();
        req.setRateplanCode(rp.getOuterId());
        req.setName(rpi18n.getRatePlanName());
        req.setPaymentType(convertLongToObj(rp.getPaymentType()));
        req.setBreakfastCount(convertLongToObj(rp.getBreakfastCount()));
        req.setFeeBreakfastCount(convertLongToObj(rp.getFee_breakfast_count()));
        if(convertLongToObj(rp.getFee_breakfast_amount())!= null){
            req.setFeeBreakfastAmount(convertLongToObj(rp.getFee_breakfast_amount())*100);
        }
        if(convertLongToObj(rp.getFee_gov_tax_amount())!=null){
            req.setFeeGovTaxAmount(convertLongToObj(rp.getFee_gov_tax_amount()*100));
        }
        if(convertLongToObj(rp.getFee_service_amount())!=null){
            req.setFeeServiceAmount(convertLongToObj(rp.getFee_service_amount()*100));
        }
        req.setFeeGovTaxPercent(convertLongToObj(rp.getFee_gov_tax_percent()));
        req.setFeeServicePercent(convertLongToObj(rp.getFee_service_percent()));
        req.setMinDays(convertLongToObj(rp.getMin_days()));
        req.setMaxDays(convertLongToObj(rp.getMax_days()));
        req.setMinAmount(convertLongToObj(rp.getMin_amount()));
        req.setMinAdvHours(convertLongToObj(rp.getMin_adv_hours()));
        req.setMaxAdvHours(convertLongToObj(rp.getMax_adv_hours()));
        req.setStartTime(DateUtil.getDateTime("HH:mm", rp.getStartTime()));
        req.setEndTime(DateUtil.getDateTime("HH:mm", rp.getEndTime()));
        req.setCancelPolicy(ratePlanVO.getCancelRuleJsonArr()); //取消规则
        req.setStatus(convertLongToObj(rp.getAvailabilityStatus()));
        req.setEnglishName(rpi18n.getEnglishName());
        
        XhotelRateplanAddResponse response = TaobaoApi.taobaoClient.execute(req, getSessionKey());
        if(null!=response.getErrorCode()){
            invokeRes.setSuccess(false);
            invokeRes.setErrCode(response.getSubCode());
            
            if(CommonUtil.isEmpty(response.getSubMsg())){
                if(CommonUtil.isNotEmpty(response.getMsg()))
                invokeRes.setErrMsg(response.getMsg().substring(response.getMsg().indexOf(":")+1));
            }else{
                invokeRes.setErrMsg(response.getSubMsg());
            }
            log.error("淘宝房价添加失败："+invokeRes.getErrMsg());
        }else{
            invokeRes.setSuccess(true);
            invokeRes.setResObj(response.getRpid());
        }
        logInfo(req,response,invokeRes);
        return invokeRes;
    }
    
    /**避免空转换异常*/
    private Long convertLongToObj(Object obj){
        if(obj != null){
           return new Long(obj.toString());
        }
        return null;
    }
    
    @Override
    public InvokeResponse rateplanUpdate(RatePlanVO ratePlanVO)
            throws Exception {
        InvokeResponse invokeRes = new InvokeResponse();
        XhotelRateplanUpdateRequest req = new XhotelRateplanUpdateRequest();
        Rateplan rp = ratePlanVO.getRp();
        RatePlanI18n  rpi18n = ratePlanVO.getRpi18n();
        req.setRpid(rp.getRpId());
        req.setName(rpi18n.getRatePlanName());
        req.setBreakfastCount(convertLongToObj(rp.getBreakfastCount()));
        req.setFeeBreakfastCount(convertLongToObj(rp.getFee_breakfast_count()));
        if(convertLongToObj(rp.getFee_breakfast_amount())!= null){
            req.setFeeBreakfastAmount(convertLongToObj(rp.getFee_breakfast_amount())*100);
        }
        if(convertLongToObj(rp.getFee_gov_tax_amount())!=null){
            req.setFeeGovTaxAmount(convertLongToObj(rp.getFee_gov_tax_amount()*100));
        }
        if(convertLongToObj(rp.getFee_service_amount())!=null){
            req.setFeeServiceAmount(convertLongToObj(rp.getFee_service_amount()*100));
        }
        req.setFeeGovTaxPercent(convertLongToObj(rp.getFee_gov_tax_percent()));
        req.setFeeServicePercent(convertLongToObj(rp.getFee_service_percent()));
        req.setMinDays(convertLongToObj(rp.getMin_days()));
        req.setMaxDays(convertLongToObj(rp.getMax_days()));
        req.setMinAmount(convertLongToObj(rp.getMin_amount()));
        req.setMinAdvHours(convertLongToObj(rp.getMin_adv_hours()));
        req.setMaxAdvHours(convertLongToObj(rp.getMax_adv_hours()));
        req.setStartTime(DateUtil.getDateTime("HH:mm", rp.getStartTime()));
        req.setEndTime(DateUtil.getDateTime("HH:mm", rp.getEndTime()));
        req.setCancelPolicy(ratePlanVO.getCancelRuleJsonArr()); //取消规则
        req.setStatus(convertLongToObj(rp.getAvailabilityStatus()));
        req.setEnglishName(rpi18n.getEnglishName());
        XhotelRateplanUpdateResponse response = TaobaoApi.taobaoClient.execute(req, getSessionKey());
        if(null != response.getErrorCode()){
            invokeRes.setSuccess(false);
            invokeRes.setErrCode(response.getSubCode());
            
            if(CommonUtil.isEmpty(response.getSubMsg())){
                if(CommonUtil.isNotEmpty(response.getMsg()))
                invokeRes.setErrMsg(response.getMsg().substring(response.getMsg().indexOf(":")+1));
            }else{
                invokeRes.setErrMsg(response.getSubMsg());
            }
            log.error("淘宝房价更新失败："+invokeRes.getErrMsg());
        }else{
            invokeRes.setSuccess(true);
            invokeRes.setResObj(response.getRpid());
        }
        logInfo(req, response, invokeRes);
        return invokeRes;
    }

    @Override
    public RatePlan rateplanGet(String rpid) throws Exception {
        XhotelRateplanGetRequest req = new XhotelRateplanGetRequest();
        req.setRpid(Long.parseLong(rpid));
        XhotelRateplanGetResponse res = TaobaoApi.taobaoClient.execute(req, getSessionKey());
        return res.getRateplan();
    }

    @Override
    public InvokeResponse rateAdd(GoodsVO gsVo) throws Exception {
        XhotelRateAddRequest req = new XhotelRateAddRequest();
        req.setGid(Long.parseLong(gsVo.getChannelGoodsCode()));
        req.setRpid(Long.parseLong(gsVo.getRpId()));
        req.setName(gsVo.getProduceName());
        req.setAddBed(gsVo.getAddBed());
        req.setAddBedPrice(gsVo.getAddBedPrice());
        req.setCurrencyCode(gsVo.getCurrencyCode());
        req.setShijiaTag(gsVo.getShijiaTag());
        req.setInventoryPrice(gsVo.getInventoryPrice());
        InvokeResponse invokeRes = new InvokeResponse();
        XhotelRateAddResponse response = TaobaoApi.taobaoClient.execute(req, getSessionKey());
        if(null != response.getErrorCode()){
            invokeRes.setSuccess(false);
            invokeRes.setErrCode(response.getSubCode());
            
            if(CommonUtil.isEmpty(response.getSubMsg())){
                if(CommonUtil.isNotEmpty(response.getMsg()))
                invokeRes.setErrMsg(response.getMsg().substring(response.getMsg().indexOf(":")+1));
            }else{
                invokeRes.setErrMsg(response.getSubMsg());
            }
            log.error("淘宝产品添加失败："+invokeRes.getErrMsg());
        }else{
            invokeRes.setSuccess(true);
            invokeRes.setResObj(response.getGidAndRpid());
        }
        req.setInventoryPrice(null);
        logInfo(req,response,invokeRes);
        return invokeRes;
    }

    @Override
    public InvokeResponse rateUpdate(GoodsVO gsVo) throws Exception {
        XhotelRateUpdateRequest req = new XhotelRateUpdateRequest();
        req.setGid(Long.parseLong(gsVo.getChannelGoodsCode()));
        req.setRpid(Long.parseLong(gsVo.getRpId()));
        req.setName(gsVo.getProduceName());
        req.setAddBed(gsVo.getAddBed());
        req.setAddBedPrice(gsVo.getAddBedPrice());
        req.setCurrencyCode(gsVo.getCurrencyCode());
        req.setShijiaTag(gsVo.getShijiaTag());
        req.setInventoryPrice(gsVo.getInventoryPrice());
        InvokeResponse invokeRes = new InvokeResponse();
        String sessionKey = getSessionKey();
        XhotelRateUpdateResponse response = TaobaoApi.taobaoClient.execute(req,sessionKey);
        
        if(null != response.getErrorCode()){
            invokeRes.setSuccess(false);
            invokeRes.setErrCode(response.getSubCode());
            
            if(CommonUtil.isEmpty(response.getSubMsg())){
                if(CommonUtil.isNotEmpty(response.getMsg()))
                invokeRes.setErrMsg(response.getMsg().substring(response.getMsg().indexOf(":")+1));
            }else{
                invokeRes.setErrMsg(response.getSubMsg());
            }
            log.error("淘宝产品更新失败："+invokeRes.getErrMsg());
        }else{
            invokeRes.setSuccess(true);
            invokeRes.setResObj(response.getGidAndRpid());
        }
        req.setInventoryPrice(null);
        logInfo(req, response, invokeRes);
        return invokeRes;
    }

    @Override
    public Rate rateGet(Long gid, Long rpId) throws Exception {
        XhotelRateGetRequest req = new XhotelRateGetRequest();
        req.setGid(gid);
        req.setRpid(rpId);
        XhotelRateGetResponse res = TaobaoApi.taobaoClient.execute(req, getSessionKey());
        return res.getRate();
    }

    @Override
    public InvokeResponse ratesUpdate(String rate_inventory_price_map)
            throws Exception {
        return this.ratesUpdate(rate_inventory_price_map, getSessionKey());
    }

    @Override
    public InvokeResponse ratesUpdate(String rate_inventory_price_map,
            String sessionKey) throws Exception {
        XhotelRatesUpdateRequest req = new XhotelRatesUpdateRequest();
        req.setRateInventoryPriceMap(rate_inventory_price_map);
        XhotelRatesUpdateResponse response = TaobaoApi.taobaoClient.execute(req, sessionKey);
        InvokeResponse invokeRes = new InvokeResponse();
        if(null != response.getErrorCode()){
            invokeRes.setSuccess(false);
            invokeRes.setErrCode(response.getSubCode());
            if(CommonUtil.isEmpty(response.getSubMsg())){
                if(CommonUtil.isNotEmpty(response.getMsg()))
                invokeRes.setErrMsg(response.getMsg().substring(response.getMsg().indexOf(":")+1));
            }else{
                invokeRes.setErrMsg(response.getSubMsg());
            }
            log.error("淘宝产品批量更新失败："+invokeRes.getErrMsg());
            logInfo(req, response, invokeRes);
        }else{
            invokeRes.setSuccess(true);
            invokeRes.setResObj(response.getGidAndRpids());
        }
//      req.setRateInventoryPriceMap("");
//      logInfo(req, response, invokeRes);
        return invokeRes;
    }
    
    
    public InvokeResponse alipayfaceUpdate(XhotelOrderAlipayfaceUpdateRequest req,String sessionKey) throws Exception {
        //XhotelOrderAlipayfaceUpdateRequest req = new XhotelOrderAlipayfaceUpdateRequest();
//        req.setTid(0l); 暂时未用
//        req.setOptType(0l);
//        req.setOid(0l);
//        req.setReasonType(0l);
//        req.setReasonText("");
        
        XhotelOrderAlipayfaceUpdateResponse response = TaobaoApi.taobaoClient.execute(req, sessionKey);
        InvokeResponse invokeRes = new InvokeResponse();
        if(null != response.getErrorCode()){
            invokeRes.setSuccess(false);
            invokeRes.setErrCode(response.getSubCode());
            if(CommonUtil.isEmpty(response.getSubMsg())){
                if(CommonUtil.isNotEmpty(response.getMsg()))
                invokeRes.setErrMsg(response.getMsg().substring(response.getMsg().indexOf(":")+1));
            }else{
                invokeRes.setErrMsg(response.getSubMsg());
            }
            log.error("离店付订单状态更新接口失败："+invokeRes.getErrMsg());
        }else{
            invokeRes.setSuccess(true);
            invokeRes.setResObj(response.getResult());//返回值
        }
        logInfo(req, response, invokeRes);
        return invokeRes;
    }
    
}
