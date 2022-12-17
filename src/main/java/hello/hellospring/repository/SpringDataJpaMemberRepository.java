package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// interface 가 interface 를 받을 때는 extends
// SpringJPA 가 어 이런게있네~ 하고 알아서 구현체 만들어줌
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // select m from Member m where m.name = ? 로 바뀜
    @Override
    Optional<Member> findByName(String name);
}
