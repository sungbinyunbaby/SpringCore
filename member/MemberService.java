package hello.core.member;

public interface MemberService {//멤버 서비스 인터페이스, 가입, 조회
    void join (Member member); //가입

    Member findMember(Long memberID); //조회
}
