package com.acme.cxf;

import org.example.acme.cxf.impl.HelloServiceImpl;
import com.acme.cxf.security.UTPasswordCallback;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;

import java.util.HashMap;
import java.util.Map;

public class SecureServer {

    public static void main(String[] args) {

        Map<String, Object> inProps = new HashMap<>();

        inProps.put("action", "UsernameToken");

        inProps.put("passwordType", "PasswordText");

        inProps.put("passwordCallbackRef", new UTPasswordCallback(Map.of("student", "secret123")));

        WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);

        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();

        factory.setServiceClass(HelloServiceImpl.class);

        factory.setAddress("http://localhost:8080/services/hello-secure");

        Server server = factory.create();

        server.getEndpoint().getInInterceptors().add(wssIn);

        System.out.println("Secure WSDL: http://localhost:8080/services/hello-secure?wsdl");
        System.out.println("Server started. Press Ctrl+C to stop.");

        // Add shutdown hook for graceful shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutting down secure server...");
            if (server != null) {
                server.stop();
            }
        }));

        // Keep server running
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Server stopping...");
            if (server != null) {
                server.stop();
            }
        }

    }

}

