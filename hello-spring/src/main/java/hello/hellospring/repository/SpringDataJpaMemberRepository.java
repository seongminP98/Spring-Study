package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { //Long은 PK의 타입
//인터페이스만 만들어 놓으면, 그리고 JpaRepository만 해놓으면 스프링 데이터 JPA가 인터페이스에 대한 구현체를 만들어내고 스프링 빈에 등록해놓는다.

    @Override
    Optional<Member> findByName(String name);
    //JPQL로 select m from Member m where m.name = ? 이렇게 짜줌.
    //findByNameAndId 이렇게 And와 Or로 사용가능
}
