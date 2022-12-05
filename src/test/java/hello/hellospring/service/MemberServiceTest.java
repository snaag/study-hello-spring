package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @AfterEach
    public void afterEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // then
        IllegalStateException e = assertThrows(IllegalStateException.class, () ->  memberService.join(member2));
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 에러 메시지 검증
        assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // '오른쪽을 수행했을 때 왼쪽 예외가 발생해야 함' 을 의미

        /**
        try {
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch(IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
         */

    }

    @Test
    void 전체_멤버_조회() {
    }

    @Test
    void 멤버_한명_조회() {
    }
}