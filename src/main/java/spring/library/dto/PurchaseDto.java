package spring.library.dto;

import lombok.Builder;
import lombok.Data;
import spring.library.domain.Purchase;

import java.time.LocalDate;

public class PurchaseDto {
    @Data
    public static class CreateReqDto {
        private Long memberId;
        private String title;
        private String author;
        private String publisher;
        private int publicationYear;
        private Long publicationRequestCount;
    }

    @Data
    @Builder
    public static class DetailResDto {
        private Long id;
        private String title;
        private String author;
        private String publisher;
        private int publicationYear;
        private Long purchaseRequestCount;

        private LocalDate requestDate;
        private LocalDate dateOfProgress;
        private String processResult;

        public static PurchaseDto.DetailResDto from(Purchase purchase) {
            return builder()
                    .id(purchase.getId())
                    .title(purchase.getTitle())
                    .author(purchase.getAuthor())
                    .publisher(purchase.getPublisher())
                    .publicationYear(purchase.getPublicationYear())
                    .requestDate(purchase.getRequestDate())
                    .dateOfProgress(purchase.getDateOfProgress())
                    .processResult(purchase.getProcessResult())
                    .build();
        }
    }

    @Data
    public static class UpdateResDto {
        private Long memberId;
        private Long purchaseId;
        private String processResult;
    }

}
