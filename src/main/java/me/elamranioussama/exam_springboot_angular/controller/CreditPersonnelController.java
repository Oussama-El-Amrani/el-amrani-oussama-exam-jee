package me.elamranioussama.exam_springboot_angular.controller;

import me.elamranioussama.exam_springboot_angular.entity.CreditPersonnel;
import me.elamranioussama.exam_springboot_angular.service.ICreditPersonnelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits/personnel")
public class CreditPersonnelController {

    private final ICreditPersonnelService creditPersonnelService;

    public CreditPersonnelController(ICreditPersonnelService creditPersonnelService) {
        this.creditPersonnelService = creditPersonnelService;
    }

    @GetMapping
    public ResponseEntity<List<CreditPersonnel>> getAllCreditPersonnel() {
        return ResponseEntity.ok(creditPersonnelService.getAllCreditPersonnel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditPersonnel> getCreditPersonnelById(@PathVariable Long id) {
        return ResponseEntity.ok(creditPersonnelService.getCreditPersonnelById(id));
    }

    @PostMapping
    public ResponseEntity<CreditPersonnel> createCreditPersonnel(@RequestBody CreditPersonnel creditPersonnel) {
        return new ResponseEntity<>(creditPersonnelService.saveCreditPersonnel(creditPersonnel), HttpStatus.CREATED);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CreditPersonnel>> getCreditPersonnelByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(creditPersonnelService.getCreditPersonnelByClientId(clientId));
    }

    @GetMapping("/motif/{motif}")
    public ResponseEntity<List<CreditPersonnel>> getCreditPersonnelByMotif(@PathVariable String motif) {
        return ResponseEntity.ok(creditPersonnelService.getCreditPersonnelByMotif(motif));
    }
}
