package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service 자바 코드로 직접 스프링 빈 등록하기 위해 지움
@Transactional //JPA는 모든 데이터 변경이 다 트랜잭션 안에서 실행되어야 함.
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired 자바 코드로 직접 스프링 빈 등록하기 위해 지움
    public MemberService(MemberRepository memberRepository){ //테스트코드에서 DI함.
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member){
        //같은 이름이 있는 중복 회원x
//        Optional<Member> result = memberRepository.findByName(member.getName());
//
//        //Optional로 감싸기 때문에 ifPresnet를 쓸 수 있음. Optional을 안쓰면 if null이 아니면.
//        result.ifPresent(m -> { //result가 값이 있으면 (m에는 member가 들어옴)
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });




            validateDuplicateMember(member); //중복 회원 검증
            memberRepository.save(member);
            return member.getId();


    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
