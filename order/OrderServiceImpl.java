package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    //DCP위반: 추상화만 의존해야 하는데, 인터페이스의 구현체까지 의존하고 있다.
    private MemberRepository memberRepository; //= new MemoryMemberRepository() 이게 AppConfig로 인해 사라짐.
//    private DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //OCP위반: Fix -> Rate로 바꾸는 순간 OrderServiceImpl까지 바꿔야 한다.
    private DiscountPolicy discountPolicy;
    //인테페이스만 의존한다. DIP를 지킨다.
    // 그렇지만,,요렇게 바꾸면 된다,,,? NPE가 나오지 않을까?
    //AppConfig 등등등등등장!


    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        //AppConfig로 의존하는 구현체를 지우고 생성자를 만들어서 생성자의 파라미터로 들어오게 만들어 준다.
        //이를 생성자 주입이라고 한다.
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
        //이 생성자를 통해서 어던 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정된다.
        //이로인해 DIP를 만족할 수 있게 되었고, 또한 OCP도 만족할 수 있게 되었다.
        //OrderServiceImpl는 이제부터 의존관계에 대한 고민은 외부(AppConfig)에게 맡기고 실행에만 집중하면 된다.
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //discountPrice로 존나 잘만든 코드가 된 것이다.
        //discountPrice 너 혼자 알아서 해! OrderService 나는 모르겠어 그냥 너가 알아서 해줭
        //discountPrice 너가 알아서 할인하고, 나한테는 결과만 줘용~
        //단일 책임 원칙을 잘 지킨것.
        //OrderService에 할인과 관련된 변경이 들어와도 OrderService를 고치는 문제가 생기지 않는다.
        //할인관련 discountPrice만 고치면 된다.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
