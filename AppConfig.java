package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    //우리는 이것을 담당 구현체에 직접 했었죠(배우가 직접 담당 배우를 섭외하는것과 같은것)
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
        //생성자를 통해 파라미터로 들어가게 된다.
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        //Rate로 바꾸어 보자. 밑처럼 AppConfig에서 이렇게 간단히 하나만 바꾸면 된다.
        //OCP를 지킨다!!!
        return new RateDiscountPolicy();
    }
}
