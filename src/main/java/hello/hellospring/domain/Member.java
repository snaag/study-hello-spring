package hello.hellospring.domain;
import javax.persistence.*;

// JPA 가 관리하는 Entity 라는 뜻
@Entity
public class Member {
    // GenerationType.IDENTITY = 숫자를 알아서 1씩 늘려줘
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name") // 필드명과 테이블 이름이 같다면, 안해도 됨
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
