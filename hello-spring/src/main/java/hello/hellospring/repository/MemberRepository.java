package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name); //Optional은 java8에 있는 기능. 없으면 null. null을 처리하기 위해 사용
    List<Member> findAll();
}
