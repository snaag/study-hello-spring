package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 매 테스트가 끝나면 실행되면 callback method
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }


    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        System.out.println("result == member:" + (result == member));

        // Assertions _ org.junit.jupiter.api
        // 둘이 똑같은지 확인 가능
        // Assertions.assertEquals(기대, 실제값);
//        Assertions.assertEquals(result, member);

        // org.assertj
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

//        Optional<Member> result = repository.findByName("spring1");
        Member result1 = repository.findByName("spring1").get();
        org.assertj.core.api.Assertions.assertThat(result1).isEqualTo(member1);

        Member result2 = repository.findByName("spring2").get();
        org.assertj.core.api.Assertions.assertThat(result2).isEqualTo(member2);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member member3 = new Member();
        member3.setName("spring3");
        repository.save(member3);

        Member member4 = new Member();
        member4.setName("spring4");
        repository.save(member4);

        List<Member> result = repository.findAll();
        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(4);
    }

    /**
     * getRandomBoolean 를 사용해서 테스트
     */
    @Test
    public void 옵셔널1() {
        for (int i=0; i<10; i++) {
            Optional<Boolean> optional = getRandomBoolean();
            Boolean value = optional.orElse(false);

            System.out.println("optional.isPresent() = " + optional.isPresent()); // Boolean, true or false

            // Boolean,
            // true (진짜 true 로 나온 경우)
            // or false (null 이었는데 orElse(false) 로 false 가 들어간 경우)
            System.out.println("value = " + value);
        }
    }

    /**
     * getRandomInteger 를 사용해서 테스트
     */
    @Test
    public void 옵셔널2() {
        for (int i=0; i<10; i++) {
            Optional<Integer> optional = getRandomInteger();
            Integer value = optional.orElse(-1);

            System.out.println("optional.isPresent() = " + optional.isPresent()); // Boolean, true or false
            System.out.println("value = " + value); // Integer, -1 or 다른 숫자들
        }
    }

    /**
     * 10보다 적은 숫자를 랜덤하게 고른 후,
     * 5보다 적으면 true 를,
     * 5와 같거나 크면 Optional.empty() == null 을 반환
     * @return Optional.of(true) or Optional.empty()
     */
    public Optional<Boolean> getRandomBoolean() {
        double dValue = Math.random();
        int iValue = (int)(dValue * 10);
        if(iValue < 5) return Optional.of(true);
        return Optional.empty();
    }

    /**
     * 10보다 적은 숫자를 랜덤하게 고른 후,
     * 5보다 적으면 숫자를,
     * 5와 같거나 크면 Optional.empty() == null 을 반환
     * @return Optional.of(Integer) or Optional.empty()
     */
    public Optional<Integer> getRandomInteger() {
        double dValue = Math.random();
        int iValue = (int)(dValue * 10);
        if(iValue < 5) return Optional.of(iValue);
        return Optional.empty();
    }

    @Test
    public void 옵셔널_null() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring2");

        Member member3 = new Member();
        member3.setName("spring3");

        Member member4 = new Member();
        member4.setName("spring4");

        repository.save(member1);
        repository.save(member2);
        repository.save(member3);
        repository.save(member4);

        // when
        repository.clearStore();

        // then
        Optional<Member> optional = repository.findById(1L);
        System.out.println("optional = " + optional);
    }

}
