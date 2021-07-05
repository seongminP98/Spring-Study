package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; //JPA는 EntityManager로 모든게 동작을 함.
    //앞에서 쓴 dataSource를 내부적으로 들고있어서 DB와 통신하는걸 내부에서 다 통신을 함.
    //JPA를 쓰려면 EntityManager를 주입받아야 한다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Mebmer m", Member.class) //m은 alias as m와 같음. 객체 자체를 select에 넣음.
               .getResultList();
    }
}
