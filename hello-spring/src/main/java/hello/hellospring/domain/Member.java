package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY) //PK를 DB가 알아서 생성(Auto increasement 같은거)
    private Long id; //고객이 정하는게 아니라 시스템이 구분하기 위해서 필요한 id


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
