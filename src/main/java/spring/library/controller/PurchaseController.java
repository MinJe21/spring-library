package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.library.dto.PurchaseDto;
import spring.library.service.PurchaseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book/purchase-request")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping()
    public void create(@RequestBody PurchaseDto.CreateReqDto req) {
        purchaseService.createPurchase(req);
    }

    @GetMapping()
    public List<PurchaseDto.DetailResDto> detail(@RequestParam Long memberId) {
        return purchaseService.detail(memberId);
    }

    @PutMapping()
    public void update(@RequestBody PurchaseDto.UpdateResDto req) {
        purchaseService.update(req);
    }

}
