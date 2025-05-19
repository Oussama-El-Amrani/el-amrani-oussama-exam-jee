package me.elamranioussama.exam_springboot_angular.controller;

import me.elamranioussama.exam_springboot_angular.entity.CreditImmobilier;
import me.elamranioussama.exam_springboot_angular.service.ICreditImmobilierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits/immobilier")
public class CreditImmobilierController {

    private final ICreditImmobilierService creditImmobilierService;

    public CreditImmobilierController(ICreditImmobilierService creditImmobilierService) {
        this.creditImmobilierService = creditImmobilierService;
    }

    @GetMapping
    public ResponseEntity<List<CreditImmobilier>> getAllCreditImmobilier() {
        return ResponseEntity.ok(creditImmobilierService.getAllCreditImmobilier());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditImmobilier> getCreditImmobilierById(@PathVariable Long id) {
        return ResponseEntity.ok(creditImmobilierService.getCreditImmobilierById(id));
    }

    @PostMapping
    public ResponseEntity<CreditImmobilier> createCreditImmobilier(@RequestBody CreditImmobilier creditImmobilier) {
        return new ResponseEntity<>(creditImmobilierService.saveCreditImmobilier(creditImmobilier), HttpStatus.CREATED);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CreditImmobilier>> getCreditImmobilierByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(creditImmobilierService.getCreditImmobilierByClientId(clientId));
    }

    @GetMapping("/type-bien/{typeBien}")
    public ResponseEntity<List<CreditImmobilier>> getCreditImmobilierByTypeBien(@PathVariable String typeBien) {
        return ResponseEntity.ok(creditImmobilierService.getCreditImmobilierByTypeBien(typeBien));
    }
}
