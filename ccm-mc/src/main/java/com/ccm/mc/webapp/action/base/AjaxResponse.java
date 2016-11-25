package com.ccm.mc.webapp.action.base;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.JSONUtils;

public class AjaxResponse {
    
    /**
     * Transient log to prevent session synchronization issues - children can use instance for logging.
     */
    public final transient Log log = LogFactory.getLog(getClass());
    
    private Boolean sucess;
    private String result;
    private String  error;
    public static AjaxResponse sucess(String result) {
        AjaxResponse response = new AjaxResponse();
        response.setResult(result);
        response.setSucess(Boolean.TRUE);
        return response;
    }
    public static AjaxResponse fail(String error) {
        AjaxResponse response = new AjaxResponse();
        response.setResult(error);
        response.setSucess(Boolean.FALSE);
        return response;
    }
    public Boolean getSucess() {
        return sucess;
    }
    public void setSucess(Boolean sucess) {
        this.sucess = sucess;
    }
    
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    @Override
    public String toString() {
        try {
            return JSONUtils.writeJson(this);
        } catch (JsonGenerationException e) {
            log.error(CommonUtil.getExceptionMsg(e,new String[]{"ccm"}));
        } catch (JsonMappingException e) {
            log.error(CommonUtil.getExceptionMsg(e,new String[]{"ccm"}));
        } catch (IOException e) {
            log.error(CommonUtil.getExceptionMsg(e,new String[]{"ccm"}));
        }
        return "error";
    }

}
