package emitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@RestController
public class SseEmitterHelloWorldApplication {

    private static final List<SseEmitter> SSE_EMITTERS = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(SseEmitterHelloWorldApplication.class,args);
        //SpringApplication.run(ClaimserviceApplication.class, args);
    }

    @RequestMapping(method = RequestMethod.GET,path = "/")
    public SseEmitter getSseEmitter() {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        SSE_EMITTERS.add(sseEmitter);
        return sseEmitter;
    }

    @RequestMapping(method = RequestMethod.POST,path = "/")
    public void post() {
        SSE_EMITTERS.stream().forEach(sseEmitter -> {
            try {
                sseEmitter.send("message "+new Date().getTime());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
