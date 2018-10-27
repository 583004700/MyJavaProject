package com.demo.mydemo.cxf.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.6
 * 2018-08-29T16:21:00.917+08:00
 * Generated source version: 3.2.6
 *
 */
@WebService(targetNamespace = "http://server.cxf.mydemo.demo.com/", name = "IHelloService")
public interface IHelloService {

    @WebMethod
    @RequestWrapper(localName = "sayX", targetNamespace = "http://server.cxf.mydemo.demo.com/", className = "com.demo.mydemo.cxf.client.SayX")
    @ResponseWrapper(localName = "sayXResponse", targetNamespace = "http://server.cxf.mydemo.demo.com/", className = "com.demo.mydemo.cxf.client.SayXResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String sayX(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
