package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy { //할인정책 인터페이스
    /*
    @return 할인 대상 금액
     */
    int discount(Member member, int price); //멤버와 가격이 들어오면
}
