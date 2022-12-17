package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // 이러면, spring jpa 가 만들어놓은 구현체가 알아서 들어감
    // 그리고 알아서 spring bean 만들어둠
    // 그러면 Spring Config 에서 알아서 injection 받으면 됨
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // spring bean 에 등록하라는 뜻
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
}
