package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //스프링이 자동으로 스프링 빈으로 등록한다. (내부에 @Component가 있어서 컴포넌트 스캔의 대상이됨)
/* @Controller대신 이 2개가 있어도 됨(클래스 레벨에만)
@Component
@RequestMapping
 */
public class SpringMemberFormControllerV1 {
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
