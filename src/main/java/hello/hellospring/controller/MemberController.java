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

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        /**
         * form.setName 을 하지 않아도 되었던 이유 :
         *   createMemberForm.html 의 8번째줄에 있는 input tag 의 name="name" 이라서,
         *   MemberForm.java 안의 name 에 대한 setter 인 setName 이 호출되었기 때문.
         *
         *   따라서, input tag 의 name="name2" 라면, MemberForm.java 의 setName 은 호출 되지 않음
         */
        System.out.println("member.getName() = " + member.getName());

        memberService.join(member); // memberService 에서 join logic 수행함

        return "redirect:/"; // home 화면으로 redirect 시킴
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
