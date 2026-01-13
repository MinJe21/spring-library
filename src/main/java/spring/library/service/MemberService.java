package spring.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Member;
import spring.library.dto.MemberDto;
import spring.library.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long create(MemberDto.CreateReqDto req){
        Member m = Member.builder()
                .name(req.getName())
                .idNumber(req.getIdNumber())
                .feature(req.getFeature())
                .email(req.getEmail())
                .phoneNumber(req.getPhoneNumber())
                .build();

        return memberRepository.save(m).getId();
    }

    public List<MemberDto.DetailResDto> getAll(){
        List<Member> members = memberRepository.findAll();
        return members.stream().map(MemberDto.DetailResDto::from).toList();
    }

    @Transactional
    public void update(Long id, MemberDto.UpdateReqDto req){
        Member m = memberRepository.findById(id).orElseThrow();
        m.setName(req.getName());
        m.setIdNumber(req.getIdNumber());
        m.setFeature(req.getFeature());
        m.setEmail(req.getEmail());
        m.setPhoneNumber(req.getPhoneNumber());
        memberRepository.save(m);
    }

    public void delete(Long id){
        Member m = memberRepository.findById(id).orElseThrow();
        memberRepository.delete(m);
    }
}
