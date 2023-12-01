package hello.core.member;

public class MemberServiceImpl implements MemberService{ //멤버 서비스 구현체
    //멤버 서비스는 저장소의 save와 findById를 필요로 한다.
    //그래서 인터페이스를 가지고 왔다.
    //그런데 인터페이스만 있으면 NullPonintException이 나온다.
    //구현체가 없기 때문이다. 그래서 구현체인 MemoryMemberRepository를 가져온다.
    private final MemberRepository memberRepository;
    //1. new MemoryMemberRepository()를 AppConfig를 만들면서 지운다.

    public MemberServiceImpl(MemberRepository memberRepository) {
        //2. 생성자를 만든다. =>생성자 주입이라고 부른다.
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
         memberRepository.save(member);
         //join에서 save를 호출하면 다형성으로 인터페이스가 아닌
        // 구현체인 MemoryMemberRepository의 save가 호출이 된다.
    }

    @Override
    public Member findMember(Long memberID) {
        return memberRepository.findById(memberID);
    }
}
