package idusw.springboot.boardjhm.service;

import idusw.springboot.boardjhm.domain.Member;
import idusw.springboot.boardjhm.entity.MemberEntity;
import idusw.springboot.boardjhm.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public int create(Member m) {
        // DTO -> Entity : Repository에서 처리하기 위해
        MemberEntity entity = MemberEntity.builder()
            .seq(m.getSeq())
            .email(m.getEmail())
            .name(m.getName())
            .pw(m.getPw())
            .build();
        if(memberRepository.save(entity) != null) // 저장 성공
            return 1;
        else
            return 0;
    }

    @Override
    public Member read(Member m) {
        MemberEntity e = memberRepository.getById(m.getSeq());
        Member member = Member.builder()
            .seq(e.getSeq())
            .email(e.getEmail())
            .name(e.getName())
            .pw(e.getPw())
            .build();
        return member;
    }

    @Override
    public List<Member> readList() {
        // 1. 매개변수를 처리하고(처리를 위한 객체 선언 및 초기화), 리파지터리 객체에게 전달
        // 2. 리파지터리 객체의 해당 메소드가 처리한 결과를 반환 유형으로 변환
        // repository : entity 객체를 대상으로 함, service : dto or domain 객체를 대상으로 함

        List<Member> result = new ArrayList<>(); // Member 객체를 원소로 갖는 리스트형 객체를 생성, 배정
        List<MemberEntity> entities = memberRepository.findAll(); // select * from a_member;
        for(MemberEntity e : entities) {
            // Function Language 특징을 활용한 처리, 1.8 Lambda 식 ->, Lombok library 활용
            Member m = Member.builder()
                .seq(e.getSeq())
                .email(e.getEmail())
                .name(e.getName())
                .build(); // DTO (Data Transfer Object) : Controller - Service or Controller - View
            result.add(m);
        }
        return result;
    }

    @Override
    public int update(Member m) {
        return 0;
    }

    @Override
    public int delete(Member m) {
        return 0;
    }

    @Override
    public Member login(Member m) { // 인터페이스에 선언한 메소드 중 구현하지 않는 메소드를 구현해야 함.
        Member result = null;
        MemberEntity entity = memberRepository.getByEmailPw(m.getEmail(), m.getPw());
        if(entity != null) {
            result = new Member();
            result.setSeq(entity.getSeq());
            result.setEmail(entity.getEmail());
            result.setName(entity.getName());
            result.setPw(entity.getPw());
        }
        return null;
    }
}
