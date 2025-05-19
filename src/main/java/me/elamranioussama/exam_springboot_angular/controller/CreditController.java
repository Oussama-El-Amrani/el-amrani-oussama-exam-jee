package me.elamranioussama.exam_springboot_angular.controller;

import me.elamranioussama.exam_springboot_angular.entity.Credit;
import me.elamranioussama.exam_springboot_angular.service.ICreditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
public class CreditController {

    private final ICreditService creditService;

    public CreditController(ICreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public ResponseEntity<List<Credit>> getAllCredits() {
        return ResponseEntity.ok(creditService.getAllCredits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Credit> getCreditById(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.getCreditById(id));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Credit>> getCreditsByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(creditService.getCreditsByClientId(clientId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Credit> updateCreditStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(creditService.updateCreditStatus(id, status));
    }
}
