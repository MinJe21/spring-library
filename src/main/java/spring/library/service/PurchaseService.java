package spring.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.*;
import spring.library.dto.PurchaseDto;
import spring.library.repository.BookRepository;
import spring.library.repository.MemberRepository;
import spring.library.repository.PurchaseRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public void createPurchase(PurchaseDto.CreateReqDto req) {
        Member member = memberRepository.findById(req.getMemberId()).orElseThrow();

        if (bookRepository.existsByTitle(req.getTitle())) {
            throw new IllegalArgumentException("already exists");
        }

        Purchase purchase = Purchase.builder()
                .member(member)
                .title(req.getTitle())
                .author(req.getAuthor())
                .publisher(req.getPublisher())
                .publicationYear(req.getPublicationYear())
                .requestDate(LocalDate.now())
                .processResult("신청")
                .purchaseRequestCount(1L)
                .build();

        purchaseRepository.save(purchase);
    }

    public List<PurchaseDto.DetailResDto> detail(Long memberId) {
        Member m = memberRepository.findById(memberId).orElseThrow();
        List<Purchase> purchases;

        if(m.getFeature().equals("관리자")){
            purchases = purchaseRepository.findAll();
        } else {
            purchases = purchaseRepository.findByMemberId(memberId);
        }

        return purchases.stream().map(PurchaseDto.DetailResDto::from).toList();
    }

    @Transactional
    public void update(PurchaseDto.UpdateResDto req) {
        Member m = memberRepository.findById(req.getMemberId()).orElseThrow();
        if (!m.getFeature().equals("관리자")) {
            throw new IllegalArgumentException("관리자만 처리가 가능합니다.");
        }

        Purchase p = purchaseRepository.findById(req.getPurchaseId()).orElseThrow();

        p.setProcessResult(req.getProcessResult());
        p.setDateOfProgress(LocalDate.now());

    }
}
