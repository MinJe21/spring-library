package spring.library.dto;

import lombok.Builder;
import lombok.Data;
import spring.library.domain.Member;

public class MemberDto {
    @Data
    public static class CreateReqDto {
        private String name;
        private Long idNumber;
        private String feature;
        private String email;
        private String phoneNumber;
    }

    @Data
    @Builder
    public static class DetailResDto {

        private Long id;
        private String name;
        private Long idNumber;
        private String feature;
        private String email;
        private String phoneNumber;

        public static MemberDto.DetailResDto from(Member member){
            return builder()
                    .id(member.getId())
                    .name(member.getName())
                    .idNumber(member.getIdNumber())
                    .feature(member.getFeature())
                    .email(member.getEmail())
                    .phoneNumber(member.getPhoneNumber())
                    .build();
        }
    }

    @Data
    public static class UpdateReqDto {
        private Long id;
        private String name;
        private Long idNumber;
        private String feature;
        private String email;
        private String phoneNumber;
    }
}
