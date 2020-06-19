/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk_skuska1_restapp_client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:skuskaResource [skuska]<br>
 * USAGE:
 * <pre>
 *        NewJerseyClient client = new NewJerseyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Peter
 */
public class NewJerseyClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/sk_skuska1_restApp/webresources";

    public NewJerseyClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("skuska");
    }

    public void addStudent(Object requestEntity, String predmet) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{predmet})).request(javax.ws.rs.core.MediaType.TEXT_PLAIN).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.TEXT_PLAIN));
    }

    public String getSkCount(String predmet) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{predmet}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String getText(String student) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (student != null) {
            resource = resource.queryParam("student", student);
        }
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String newSkuska(Object requestEntity) throws ClientErrorException {
//        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML)
//        .post(javax.ws.rs.client.Entity.entity(requestEntity,
//        javax.ws.rs.core.MediaType.APPLICATION_XML), String.class);
        return webTarget.request(javax.ws.rs.core.MediaType.TEXT_PLAIN)
                .post(javax.ws.rs.client.Entity.entity(requestEntity,
                        javax.ws.rs.core.MediaType.APPLICATION_XML), String.class);
    }

    public <T> T getSkuska(Class<T> responseType, String predmet) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{predmet}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
