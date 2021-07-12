package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //final을 가진애를 가지고 생성자를 만들어줌
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository(); //DIP위반.
    private final MemberRepository memberRepository; //인터페이스만 의존한다. DIP지킴.
    private final DiscountPolicy discountPolicy;

/*필드 주입
   @Autowired private MemberRepository memberRepository;
    @Autowired private DiscountPolicy discountPolicy;
*/

/*수정자 주입

    @Autowired(required = false) //required = false를 하면 선택적. 있어도 되고 없어도 됨.
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
*/

    @Autowired   //생성자가 1개라서 Autowired생략 가능.
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

/*일반 메서드 주입
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //OrderService 입장에서는 할인에 대한건 모른다. discountPolicy가 알아서 해라.
        //단일책임원칙을 잘 지킨 것.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도 (Configuration과 싱글톤)
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
