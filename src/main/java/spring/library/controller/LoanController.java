package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.library.dto.LoanDto;
import spring.library.service.LoanService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class LoanController {
    private final LoanService loanService;

    @PostMapping("/book/{bookId}/checkout")
    public void checkout(@PathVariable Long bookId, @RequestBody LoanDto.CheckoutReqDto req) {
        loanService.checkout(bookId, req);
    }

    @GetMapping("/book/checkout")
    public List<LoanDto.DetailResDto> detailCheckoutList(@RequestParam Long memberId) {
        return loanService.detailCheckoutList(memberId);
    }

    @GetMapping("/book/history")
    public List<LoanDto.DetailResDto> detailHistoryList(@RequestParam Long memberId) {
        return loanService.detailHistoryList(memberId);
    }

    @PutMapping("/book/{bookId}/return")
    public void returnBook(@PathVariable Long bookId, @RequestParam Long memberId) {
        loanService.returnBook(bookId, memberId);
    }

    @PutMapping("/book/{bookId}/renewal")
    public void renewalBook(@PathVariable Long bookId, @RequestParam Long memberId) {
        loanService.renewalBook(bookId, memberId);
    }
}
