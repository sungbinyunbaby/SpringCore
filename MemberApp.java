package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        //AppConfig

        //MemberService memberService = new MemberServiceImpl();
        //이것도 마찬가지로 인터페이스 가져오고 구현체를 받고.

        //회원가입 해보자 ctrl art v : 해당 메서드에 대한 반환 타입과 변수 자동 생성.
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        //회원가입이 제대로 되었나 확인해보자 ->
        //조회를 하면 회원가입이 제대로 되었나 확인할 수 있잖아?
        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
