package joinweb.join.service;

import joinweb.join.domain.Member;
import joinweb.join.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원등록() throws Exception {

        // given
        Member member = new Member();
        member.setName("nae");

        //when
        Long result = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findOne(result));
    }

    @Test(expected = IllegalStateException.class )
    public void 중복이름검사() throws Exception {

        //given
        Member member1 = new Member();
        member1.setName("nae");
        Member member2 = new Member();
        member2.setName("nae");

        //when
        memberService.join(member1);
        memberService.join(member2);

        // then
        fail("예외가 발생해야함.");
    }

    }