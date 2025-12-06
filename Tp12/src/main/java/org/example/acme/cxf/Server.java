package org.example.acme.cxf;

import org.example.acme.cxf.impl.HelloServiceImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class Server {
    public static void main(String[] args) {
        String address = "http://localhost:8080/services/hello";
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setServiceClass(HelloServiceImpl.class);
        factory.setAddress(address);
        org.apache.cxf.endpoint.Server cxfServer = factory.create();
        System.out.println("WSDL: " + address + "?wsdl");
        System.out.println("Server started. Press Ctrl+C to stop.");
        
        // Add shutdown hook for graceful shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutting down server...");
            if (cxfServer != null) {
                cxfServer.stop();
            }
        }));
        
        // Keep server running
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Server stopping...");
            if (cxfServer != null) {
                cxfServer.stop();
            }
        }
    }
}
