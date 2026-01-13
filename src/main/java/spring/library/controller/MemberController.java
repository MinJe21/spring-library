package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.library.dto.MemberDto;
import spring.library.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping()
    public Long create(@RequestBody MemberDto.CreateReqDto req) {
        return memberService.create(req);
    }

    @GetMapping()
    public List<MemberDto.DetailResDto> getAll(){
        return memberService.getAll();
    }

    @PutMapping("/{memberId}")
    public void update(@PathVariable Long memberId, MemberDto.UpdateReqDto req) {
        memberService.update(memberId, req);
    }

    @DeleteMapping("/{memberId}")
    public void delete(@PathVariable Long memberId) {
        memberService.delete(memberId);
    }

}
