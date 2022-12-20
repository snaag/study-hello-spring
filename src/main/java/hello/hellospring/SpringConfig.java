package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
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

    // aop 는 service, repository 처럼 정형화된게 아니기 때문에,
    // 이렇게 적어주면 (컴포넌트 스캔 대신), 다른 개발자가 봤을 때, '아 aop 쓰는구나' 하고 알 수 있음
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }
}
