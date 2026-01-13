package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long idNumber;
    private String feature;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy="member",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Loan> loans;
}
