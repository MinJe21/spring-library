package spring.library.dto;

import lombok.Builder;
import lombok.Data;
import spring.library.domain.Loan;
import spring.library.domain.Member;

import java.time.LocalDate;

public class LoanDto {
    @Data
    public static class CheckoutReqDto {
        private Long MemberId;
    }

    @Data
    @Builder
    public static class DetailResDto {

        private Long bookId;
        private String title;
        private String author;
        private LocalDate loanDate;
        private LocalDate dueDate;
        private int renewalCount;
        private boolean isReturned;

        public static LoanDto.DetailResDto from(Loan loan){
            return builder()
                    .bookId(loan.getBook().getId())
                    .title(loan.getBook().getTitle())
                    .author(loan.getBook().getAuthor())
                    .loanDate(loan.getLoanDate())
                    .dueDate(loan.getDueDate())
                    .renewalCount(loan.getRenewalCount())
                    .isReturned(loan.isReturned())
                    .build();
        }
    }
}
