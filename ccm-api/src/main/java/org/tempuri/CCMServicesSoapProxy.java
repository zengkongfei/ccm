package org.tempuri;

public class CCMServicesSoapProxy implements org.tempuri.CCMServicesSoap {
  private String _endpoint = null;
  private org.tempuri.CCMServicesSoap cCMServicesSoap = null;
  
  public CCMServicesSoapProxy() {
    _initCCMServicesSoapProxy();
  }
  
  public CCMServicesSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initCCMServicesSoapProxy();
  }
  
  private void _initCCMServicesSoapProxy() {
    try {
      cCMServicesSoap = (new org.tempuri.CCMServicesLocator()).getCCMServicesSoap();
      if (cCMServicesSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cCMServicesSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cCMServicesSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cCMServicesSoap != null)
      ((javax.xml.rpc.Stub)cCMServicesSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.CCMServicesSoap getCCMServicesSoap() {
    if (cCMServicesSoap == null)
      _initCCMServicesSoapProxy();
    return cCMServicesSoap;
  }
  
  public java.lang.String newCase(java.lang.String propertyID, java.lang.String suite, java.lang.String prodcutID, int priority, java.lang.String briefDescription, java.lang.String fullDescription, java.lang.String problemType, java.lang.String assignTo) throws java.rmi.RemoteException{
    if (cCMServicesSoap == null)
      _initCCMServicesSoapProxy();
    return cCMServicesSoap.newCase(propertyID, suite, prodcutID, priority, briefDescription, fullDescription, problemType, assignTo);
  }
  
  public java.lang.String helloWorld() throws java.rmi.RemoteException{
    if (cCMServicesSoap == null)
      _initCCMServicesSoapProxy();
    return cCMServicesSoap.helloWorld();
  }
  
  public java.lang.String closeCase(java.lang.String caseID, java.lang.String reasonCode, java.lang.String solution) throws java.rmi.RemoteException{
    if (cCMServicesSoap == null)
      _initCCMServicesSoapProxy();
    return cCMServicesSoap.closeCase(caseID, reasonCode, solution);
  }
  
  public boolean authenticate() throws java.rmi.RemoteException{
    if (cCMServicesSoap == null)
      _initCCMServicesSoapProxy();
    return cCMServicesSoap.authenticate();
  }
  
  public boolean authenticate2(org.tempuri.SoapHeaderOut soapHeader) throws java.rmi.RemoteException{
    if (cCMServicesSoap == null)
      _initCCMServicesSoapProxy();
    return cCMServicesSoap.authenticate2(soapHeader);
  }
  
  
}