package org.example.acme.cxf.impl;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import org.example.acme.cxf.api.HelloService;
import org.example.acme.cxf.model.Person;

@WebService(
        serviceName = "HelloService",
        portName = "HelloServicePort",
        endpointInterface = "org.example.acme.cxf.api.HelloService",
        targetNamespace = "http://api.cxf.acme.com/"
)

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Bonjour, " + (name == null ? "inconnu" : name);
    }

    @Override
    public Person findPersonById(String id) {
        return new Person(id, "Ada Lovelace", 36); // maquette
    }
}
