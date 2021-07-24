package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseView() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }

    //@ResponseBody //리턴값이 뷰로안가고 문자로 바로 보인다.
    @RequestMapping("/response-view-v2") //리턴 값이 뷰로감.
    public String responseViewV2(Model model) {
        model.addAttribute("data","hello!");
        return "response/hello";
    }

    @RequestMapping("/response/hello") //권장x
    //경로 이름이랑 리턴해서 뷰로 가는이름이 똑같으면 리턴 없어도됨.
    public void responseViewV3(Model model) {
        model.addAttribute("data","hello!");
    }
}
