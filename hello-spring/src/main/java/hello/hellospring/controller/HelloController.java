package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") //HTTP에서 get방식. hello url에 매칭됨.
    public String hello(Model model){
        model.addAttribute("data","spring!!!!"); //key가 "data", value가 "spring!!"
        return "hello"; //resources > templates에 있는 hello.html로 가서 렌더링 해라.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String n, Model model){
        n += "이야!";
        model.addAttribute("name", n);
        return "hello-template";
    }

    @GetMapping("hello-spring")
    @ResponseBody //HTTP의 Body에 이 데이터를 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    } //json으로 넘겨준다. key : value 방식

    static class Hello { //class안에 static으로 class를 만들면 쓸 수 있음. HelloController.Hello 로 접근
        private String name;

        public String getName() {  //단축키 Art+Insert
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }
}
