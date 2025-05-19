package me.elamranioussama.exam_springboot_angular.controller;

import me.elamranioussama.exam_springboot_angular.dto.CreditDTO;
import me.elamranioussama.exam_springboot_angular.entity.Credit;
import me.elamranioussama.exam_springboot_angular.mapper.CreditMapper;
import me.elamranioussama.exam_springboot_angular.service.ICreditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/credits")
public class CreditController {

    private final ICreditService creditService;
    private final CreditMapper creditMapper;

    public CreditController(ICreditService creditService, CreditMapper creditMapper) {
        this.creditService = creditService;
        this.creditMapper = creditMapper;
    }

    @GetMapping
    public ResponseEntity<List<CreditDTO>> getAllCredits() {
        List<CreditDTO> creditDTOs = creditService.getAllCredits().stream()
                .map(creditMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(creditDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditDTO> getCreditById(@PathVariable Long id) {
        Credit credit = creditService.getCreditById(id);
        return ResponseEntity.ok(creditMapper.toDto(credit));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CreditDTO>> getCreditsByClientId(@PathVariable Long clientId) {
        List<CreditDTO> creditDTOs = creditService.getCreditsByClientId(clientId).stream()
                .map(creditMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(creditDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<CreditDTO> updateCreditStatus(@PathVariable Long id, @RequestParam String status) {
        Credit updatedCredit = creditService.updateCreditStatus(id, status);
        return ResponseEntity.ok(creditMapper.toDto(updatedCredit));
    }
}
