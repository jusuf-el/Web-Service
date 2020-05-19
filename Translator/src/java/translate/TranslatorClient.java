/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translate;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:Translate
 * [/TranslateService/{word}/{lang1}/{lang2}]<br>
 * USAGE:
 * <pre>
 *        TranslatorClient client = new TranslatorClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author jusuf
 */
public class TranslatorClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/Translator/resource";

    public TranslatorClient(String word, String lang1, String lang2) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        String resourcePath = java.text.MessageFormat.format("TranslateService/{0}/{1}/{2}", new Object[]{word, lang1, lang2});
        webTarget = client.target(BASE_URI).path(resourcePath);
    }

    public void setResourcePath(String word, String lang1, String lang2) {
        String resourcePath = java.text.MessageFormat.format("TranslateService/{0}/{1}/{2}", new Object[]{word, lang1, lang2});
        webTarget = client.target(BASE_URI).path(resourcePath);
    }

    /*public String getMessage() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.get(String.class);
    }*/

    public void close() {
        client.close();
    }
    
}
