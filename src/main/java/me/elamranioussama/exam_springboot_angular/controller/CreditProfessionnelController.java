package me.elamranioussama.exam_springboot_angular.controller;

import me.elamranioussama.exam_springboot_angular.entity.CreditProfessionnel;
import me.elamranioussama.exam_springboot_angular.service.ICreditProfessionnelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits/professionnel")
public class CreditProfessionnelController {

    private final ICreditProfessionnelService creditProfessionnelService;

    public CreditProfessionnelController(ICreditProfessionnelService creditProfessionnelService) {
        this.creditProfessionnelService = creditProfessionnelService;
    }

    @GetMapping
    public ResponseEntity<List<CreditProfessionnel>> getAllCreditProfessionnel() {
        return ResponseEntity.ok(creditProfessionnelService.getAllCreditProfessionnel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditProfessionnel> getCreditProfessionnelById(@PathVariable Long id) {
        return ResponseEntity.ok(creditProfessionnelService.getCreditProfessionnelById(id));
    }

    @PostMapping
    public ResponseEntity<CreditProfessionnel> createCreditProfessionnel(@RequestBody CreditProfessionnel creditProfessionnel) {
        return new ResponseEntity<>(creditProfessionnelService.saveCreditProfessionnel(creditProfessionnel), HttpStatus.CREATED);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CreditProfessionnel>> getCreditProfessionnelByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(creditProfessionnelService.getCreditProfessionnelByClientId(clientId));
    }

    @GetMapping("/raison-sociale/{raisonSociale}")
    public ResponseEntity<List<CreditProfessionnel>> getCreditProfessionnelByRaisonSociale(@PathVariable String raisonSociale) {
        return ResponseEntity.ok(creditProfessionnelService.getCreditProfessionnelByRaisonSociale(raisonSociale));
    }
}
