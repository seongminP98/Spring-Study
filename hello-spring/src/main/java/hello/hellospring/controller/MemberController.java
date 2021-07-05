package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }

    @GetMapping("/members/new") //home에서 회원가입 링크가 members/new로 되어있어서.
    public String createForm() {
        return "members/createMemberForm"; //members/createMemberForm 으로 이동함 templates에서 찾음
    }

    @PostMapping("/members/new")
        public String create(MemberForm form){ //MemberForm의 name에 placeholder에 입력한 값이 들어옴.
        System.out.println("form name = " + form.getName());
        Member member = new Member();
        member.setName(form.getName()); //고객이 입력한 이름 저장. form에 저장된 name을 member에 저장.

        System.out.println("member name = " + member.getName());

        memberService.join(member); //회원가입 서비스

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members); //뷰에서 key값인 "members"로 members이용가능.
        return "members/memberList";
    }
}
