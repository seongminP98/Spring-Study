package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j  //lombok이 제공
@RestController
public class LogTestController {
    //private final Logger log = LoggerFactory.getLogger(getClass()); //getClass()는 현재 나의 클래스 (LogTestController)
    //@Slf4j 사용하면 이거 자동으로 해줌.

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        log.trace(" trace log={}", name);
        log.debug(" debug log={}", name);
        log.info(" info log={}",name);
        log.info(" info log="+name); //이렇게해도 나오는데 사용x
        //만약 debug 레벨인데 log.trace(" trace log="+name)을 하면 trace는 출력되지 않지만, 괄호 안 연산이 실행됨.


        log.warn(" warn log={}", name);
        log.error(" error log={}", name);

        return "ok";
    }

}
