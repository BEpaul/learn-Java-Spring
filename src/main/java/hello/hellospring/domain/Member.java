package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Member 클래스는 JPA가 관리하는 엔티티가 됨
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db에서 id값을 자동으로 부여해주는 것을 IDENTITY라고 함
    private Long id;
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
