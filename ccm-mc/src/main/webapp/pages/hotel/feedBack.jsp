<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
        <div class="title_wp"><fmt:message key="ccm.CustomerFeedback.CustomerFeedbackDetails"/> </div>        
        <div class="c_whitebg pdA12">
          <div class="mgB24">
            <ul class="list_input">
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.CustomerFeedback.CompanyName"/>：</span></div>
                <div class="i_input">
                  <s:textfield key="fb.companyName" cssClass="fxt w120" readonly="true"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.ReservationsManagment.GuestName"/>：</span></div>
                <div class="i_input">
                  <s:textfield key="fb.name" cssClass="fxt w240" readonly="true"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"><fmt:message key="ccm.CustomerFeedback.MobilePhone"/>：</span></div>
                <div class="i_input">
                  <s:textfield key="fb.mobile" cssClass="fxt w240" readonly="true"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.CustomerFeedback.Email"/>：</span></div>
                <div class="i_input">
                  <s:textfield name="fb.email" cssClass="fxt w240" readonly="true"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.CustomerFeedback.CustomerMessage"/>：</span></div>
                <div class="i_input">
                  <s:textarea key="fb.message" cssClass="fxt w360 h80" readonly="true"></s:textarea>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.ReservationMonitorReport.CreatedTime"/>：</span></div>
                <div class="i_input">
                  <s:textfield key="fb.createdTime" cssClass="fxt w240" readonly="true"></s:textfield>
                </div>
              </li>
            </ul>
          </div>
          <hr class="dashed">
          <div class="listinputCtrl">
            <a class="btn_1 white" href="javascript:history.go(-1);"><fmt:message key="common.Return"/></a>
          </div>
        </div>