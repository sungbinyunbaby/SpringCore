package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{ //할인정책 구현
    private int discountFixAmount = 1000; //할인 1000원

    @Override
    public int discount(Member member, int price) { //멤버와 가격이 들어오고
        if (member.getGrade() == Grade.VIP) { //vIP이면
            return discountFixAmount; //1000원 줌.
        } else {
            return 0; //아니면 할인 없음.
        }
    }
}
