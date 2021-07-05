package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository 자바 코드로 직접 스프링 빈 등록하기 위해 지움
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null이 올수도 있기 때문에 Optional로 감싼다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //하나라도 찾으면 반환. 없으면 Optional로 처리
    }

    @Override
    public List<Member> findAll() { //실무에서 List 많이 씀.
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
