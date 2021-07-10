package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository(); //DIP위반.
    private MemberRepository memberRepository; //인터페이스만 의존한다. DIP지킴.
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

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
