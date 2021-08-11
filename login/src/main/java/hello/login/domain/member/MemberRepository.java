package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(),member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) { //값이 null 로 반환될 수 있는 상황에서 null 을 직접 반환하기보다 Optional 로 empty 를 반환한다.
/*
        List<Member> all = findAll();
        for (Member m : all) {
            if(m.getLoginId().equals(loginId)) {
                return Optional.of(m);
            }
        }
        return Optional.empty();
*/ //아래와 같음.

        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
        /*
        list를 stream으로 바꾸고 (루프를 돈다고 생각),
        filter를 만족하는 애만 다음 단계로 넘어감. 만족하지 않으면 버려짐.
        .findFirst() : 먼저 나오는애만 가지고 반환.
        자바8의 람다, 스트림.
        */
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
