package spring.library.service;

import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.domain.Loan;
import spring.library.domain.Member;
import spring.library.dto.LoanDto;
import spring.library.repository.BookRepository;
import spring.library.repository.LoanRepository;
import spring.library.repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public void checkout(Long bookId, LoanDto.CheckoutReqDto req) {
        Book b = bookRepository.findById(bookId).orElseThrow();
        Member m =  memberRepository.findById(req.getMemberId()).orElseThrow();

        String feature = m.getFeature();
        if(b.getStatus().equals("대출가능")){
            int count = loanRepository.countByMemberId(req.getMemberId());
            System.out.println(count);
            switch(feature){
                case "학생":
                    if(count >= 10) {
                        throw new IllegalArgumentException("학생은 최대 10권까지만 대출 가능합니다.");
                    }
                    break;
                case "교직원":
                    if(count >= 20) {
                        throw new IllegalArgumentException("교직원은 최대 20권까지만 대출 가능합니다.");
                    }
                    break;
                default:
                    if(count >= 100) {
                        throw new IllegalArgumentException("대출 가능한 최대 한도를 초과했습니다.");
                    }
                    break;
            }
            int duringDate;
            if(feature.equals("학생")) {
                duringDate = 10;
            } else if (feature.equals("교직원")) {
                duringDate = 30;
            } else {
                duringDate = 110813;
            }
            Loan l = Loan.builder()
                    .loanDate(LocalDate.now())
                    .dueDate(LocalDate.now().plusDays(duringDate))
                    .renewalCount(0)
                    .isReturned(false)
                    .book(b)
                    .member(m)
                    .build();
            loanRepository.save(l);
        }
    }

    public List<LoanDto.DetailResDto> detailCheckoutList(Long memberId) {
        Member m =  memberRepository.findById(memberId).orElseThrow();
        return m.getLoans().stream().filter(loan-> !loan.isReturned()).map(LoanDto.DetailResDto::from).toList();
    }

    public List<LoanDto.DetailResDto> detailHistoryList(Long memberId) {
        Member m =  memberRepository.findById(memberId).orElseThrow();
        return m.getLoans().stream().map(LoanDto.DetailResDto::from).toList();
    }

    @Transactional
    public void returnBook(Long bookId, Long memberId) {
        bookRepository.findById(bookId).orElseThrow();
        memberRepository.findById(memberId).orElseThrow();

        Loan l = loanRepository.findByBookIdAndMemberId(bookId, memberId).orElseThrow();
        l.setReturned(true);
        loanRepository.save(l);
    }

    @Transactional
    public void renewalBook(Long bookId, Long memberId) {
        bookRepository.findById(bookId).orElseThrow();
        memberRepository.findById(memberId).orElseThrow();

        Loan l =  loanRepository.findByBookIdAndMemberId(bookId, memberId).orElseThrow();
        if(!l.isReturned() && l.getDueDate().isEqual(LocalDate.now()) && l.getRenewalCount()==0) {
            l.setRenewalCount(1);
            l.setDueDate(l.getDueDate().plusDays(5));
            loanRepository.save(l);
        }
    }
}
