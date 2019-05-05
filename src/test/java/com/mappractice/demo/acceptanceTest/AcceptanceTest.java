package com.mappractice.demo.acceptanceTest;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {

    @Autowired
    private TestRestTemplate template;

    public TestRestTemplate template(){
        return this.template;
    }

    protected String createResoure(String uri, Object bodyPayLoad){
        return Objects.requireNonNull(sendPost(uri, bodyPayLoad, Void.class)
                .getHeaders()
                .getLocation())
                .getPath();
    }

    protected <T> ResponseEntity<T> sendPost(String uri, Object bodyPayLoad, Class<T> responseType){
        return template().postForEntity(uri, bodyPayLoad, responseType);
    }

    protected <T> ResponseEntity<T> sendGet(String uri, Class<T> responseType) {
        return template().getForEntity(uri, responseType);
    }

    protected <T> ResponseEntity<T> sendDelete(String uri, Class<T> responseType){
        return template().exchange(uri, HttpMethod.DELETE, createHttpEntity(null), responseType);

    }

    private HttpEntity<Object> createHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }


}