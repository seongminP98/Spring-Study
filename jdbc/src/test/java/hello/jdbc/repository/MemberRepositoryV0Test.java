package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        //save
        Member member = new Member("memberV100", 10000);
        repository.save(member);

        //findById
        Member findMember = repository.findById(member.getMemberId());
        log.info("findMember={}", findMember);
        // Member 에서 lombok 의 @Data 사용.
        log.info("member != findMember {}", member == findMember); //false
        log.info("member equals findMember {}", member.equals(findMember)); //true
        assertThat(findMember).isEqualTo(member);

        //update : money: 10000 -> 20000
        repository.update(member.getMemberId(), 20000);
        Member updateMember = repository.findById(member.getMemberId());
        assertThat(updateMember.getMoney()).isEqualTo(20000);

        //delete
        repository.delete(member.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);

        /*
        테스트 중간에 오류가 발생해 삭제 로직을 수행할 수 없다면 테스트를 반복해서 실행할 수 없다.
        (pk 가 중복되기 때문) 좋은 테스트 코드가 아니다.
        트랜잭션을 활용하면 이 문제를 깔끔하게 해결할 수 있다.
         */
    }
}