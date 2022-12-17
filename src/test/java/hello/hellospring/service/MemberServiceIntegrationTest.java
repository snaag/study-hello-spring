package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @Test
    @Commit
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring2");

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
        member1.setId(0L);

        Member member2 = new Member();
        member2.setName("spring");
        member1.setId(1L);


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
}