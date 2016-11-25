/**
 * CCMServicesSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface CCMServicesSoap extends java.rmi.Remote {

    /**
     * 添加CCM的Case案例【返回值 ->0:用户合法验证失败, 1：添加成功，返回添加成功的CaseID，2：添加失败，3：没操作权限号1003，4：参数必须输入,不能为空，5：Priority参数比需是整形类型,6:
     * PropertyID不存在,7:ProblemType不存在,8:Attention不存在，9:该酒店不存在Suite,10:该酒店不存在ProductID,11:Suite下没有该ProductID】
     */
    public java.lang.String newCase(java.lang.String propertyID, java.lang.String suite, java.lang.String prodcutID, int priority, java.lang.String briefDescription, java.lang.String fullDescription, java.lang.String problemType, java.lang.String assignTo) throws java.rmi.RemoteException;

    /**
     * Hello World
     */
    public java.lang.String helloWorld() throws java.rmi.RemoteException;

    /**
     * 关闭CCM的Case【返回值 ->0:用户合法验证失败, 1：成功关闭Case，2：关闭Case失败（参数合法性验证），3：没操作权限号1003】
     */
    public java.lang.String closeCase(java.lang.String caseID, java.lang.String reasonCode, java.lang.String solution) throws java.rmi.RemoteException;

    /**
     * Authenticates the user credentials in the SOAP header.
     */
    public boolean authenticate() throws java.rmi.RemoteException;

    /**
     * Authenticates the user credentials in the SOAP header.
     */
    public boolean authenticate2(org.tempuri.SoapHeaderOut soapHeader) throws java.rmi.RemoteException;
}
