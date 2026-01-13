package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.library.domain.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    int countByMemberId(Long memberId);
}

