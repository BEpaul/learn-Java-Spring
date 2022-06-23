package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 스프링을 띄워서 하는 테스트
// 그러면 MemberServiceTest 같은 순수한 단위테스트는 필요 없지 않느냐??
// --> 속도 면에서 압도적인 차이가 발생하기 때문에 순수 단위테스트가 더 좋은 경우가 대부분임!
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // ctrl + alt + v : 객체 name = 생성
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 왼쪽 IllegalStateException 예외가 터져야 함
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");



    }

}