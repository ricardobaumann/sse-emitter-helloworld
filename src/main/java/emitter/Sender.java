package emitter;

import org.springframework.web.client.RestTemplate;

public class Sender {


    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        while (true) {
            restTemplate.postForObject("http://localhost:8080",null,Void.class);
            Thread.sleep(1000);
        }

    }

}
