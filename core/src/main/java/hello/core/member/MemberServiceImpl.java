package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements  MemberService{

    private final MemberRepository memberRepository;

    @Autowired //자동 의존관계 주입. 생성자에 붙여주면 MemberRepository에 맞는 애를 찾아와서 자동으로 주입해준다.
    //마치 ac.getBean(MemberRepository.class) 처럼 동작
    public MemberServiceImpl(MemberRepository memberRepository) { //DI (의존관계 주입)
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도 (Configuration과 싱글톤)
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
