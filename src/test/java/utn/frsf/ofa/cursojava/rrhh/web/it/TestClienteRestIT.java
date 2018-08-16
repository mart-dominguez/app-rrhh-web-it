/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.rrhh.web.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author mdominguez
 */
public class TestClienteRestIT {

    public TestClienteRestIT() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetCliente() throws IOException {
        HttpGet httpget = new HttpGet("http://localhost:8080/rrhh-web/api/cliente");
        CloseableHttpClient cliente = HttpClients.createDefault();
        CloseableHttpResponse response1 = cliente.execute(httpget);
        HttpEntity entity1 = response1.getEntity();            
        String resultado = entidadToString(entity1.getContent());
        assertEquals("GET", resultado.toUpperCase());
        EntityUtils.consume(entity1);            
        response1.close();
    }
    
    @Test
    public void testPostCliente() throws IOException {
        HttpPost httpPost = new HttpPost("http://localhost:8080/rrhh-web/api/cliente");        
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode clienteJson = mapper.createObjectNode();
        clienteJson.put("id", 1);
        clienteJson.put("nombre", "martin");
        clienteJson.put("correo", "mdo@mail.com");
        clienteJson.put("cuit", "203040");
        
        StringEntity postingString = new StringEntity(clienteJson.toString());
        httpPost.setEntity(postingString);
        httpPost.setHeader("Content-type", "application/json");
        
        CloseableHttpClient cliente = HttpClients.createDefault();
        CloseableHttpResponse response1 = cliente.execute(httpPost);
        HttpEntity entity1 = response1.getEntity();            
        String resultado = entidadToString(entity1.getContent());
        assertEquals("POST", resultado.toUpperCase());
        EntityUtils.consume(entity1);            
        response1.close();
    }

    private String entidadToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}
