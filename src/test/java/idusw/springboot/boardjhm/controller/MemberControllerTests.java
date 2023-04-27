package idusw.springboot.boardjhm.controller;

import idusw.springboot.boardjhm.domain.Member;
import idusw.springboot.boardjhm.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MemberControllerTests { // Unit Test : JUnit - Test Framework
    @Autowired
    MemberService memberService;
    /*
    @Autowired
    MemberRepository memberRepository;
    */

    @Test
    void contextLoads(){
        List<Member> result = memberService.readList();
        for(Member m : result)
            System.out.println(m.getSeq() + ":" + m.getEmail() + ":" + m.getName());
    }
}
