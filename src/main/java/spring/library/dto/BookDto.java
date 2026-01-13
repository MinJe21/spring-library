package spring.library.dto;

import lombok.Builder;
import lombok.Data;
import spring.library.domain.Book;
import spring.library.domain.Member;

public class BookDto {
    @Data
    public static class CreateReqDto {
        private String title;
        private String author;
        private String publisher;
        private Long publicationYear;
        private String classification;
        private String status;
        private Long amount;
    }

    @Data
    @Builder
    public static class DetailResDto {

        private Long id;
        private String title;
        private String author;
        private String publisher;
        private Long publicationYear;
        private String classification;
        private String status;
        private Long amount;

        public static BookDto.DetailResDto from(Book book){
            return builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .publisher(book.getPublisher())
                    .publicationYear(book.getPublicationYear())
                    .classification(book.getClassification())
                    .status(book.getStatus())
                    .amount(book.getAmount())
                    .build();
        }
    }

    @Data
    public static class UpdateReqDto {
        private Long id;
        private String title;
        private String author;
        private String publisher;
        private Long publicationYear;
        private String classification;
        private String status;
        private Long amount;
    }
}
