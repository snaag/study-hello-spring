package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // data-jpa 라이브러리를 받았음 (build.gradle), 그러면 spring boot 가 알아서 EntityManager 를 만들어줌, 얘가 DB 랑 다 연결해줌
    // JPA 는 entity manager 라는걸로 모든걸 함
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // persist: 영속하다, 저장하다
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // 조회할 type, 식별자 (PK), 요게 select 문임
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // PK 로 조회하는게 아니기 때문에...
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
            .setParameter("name", name)
            .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
            .getResultList();
    }
}
