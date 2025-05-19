package me.elamranioussama.exam_springboot_angular.controller;

import me.elamranioussama.exam_springboot_angular.dto.CreditImmobilierDTO;
import me.elamranioussama.exam_springboot_angular.entity.CreditImmobilier;
import me.elamranioussama.exam_springboot_angular.mapper.CreditMapper;
import me.elamranioussama.exam_springboot_angular.service.ICreditImmobilierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/credits/immobilier")
public class CreditImmobilierController {

    private final ICreditImmobilierService creditImmobilierService;
    private final CreditMapper creditMapper;

    public CreditImmobilierController(ICreditImmobilierService creditImmobilierService, CreditMapper creditMapper) {
        this.creditImmobilierService = creditImmobilierService;
        this.creditMapper = creditMapper;
    }

    @GetMapping
    public ResponseEntity<List<CreditImmobilierDTO>> getAllCreditImmobilier() {
        List<CreditImmobilierDTO> creditDTOs = creditImmobilierService.getAllCreditImmobilier().stream()
                .map(credit -> (CreditImmobilierDTO) creditMapper.toDto(credit))
                .collect(Collectors.toList());
        return ResponseEntity.ok(creditDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditImmobilierDTO> getCreditImmobilierById(@PathVariable Long id) {
        CreditImmobilier credit = creditImmobilierService.getCreditImmobilierById(id);
        return ResponseEntity.ok((CreditImmobilierDTO) creditMapper.toDto(credit));
    }

    @PostMapping
    public ResponseEntity<CreditImmobilierDTO> createCreditImmobilier(@RequestBody CreditImmobilierDTO creditDTO) {
        // For new credits, ensure ID is not set
        creditDTO.setId(null);

        CreditImmobilier credit = (CreditImmobilier) creditMapper.toEntity(creditDTO);
        CreditImmobilier savedCredit = creditImmobilierService.saveCreditImmobilier(credit);
        return new ResponseEntity<>((CreditImmobilierDTO) creditMapper.toDto(savedCredit), HttpStatus.CREATED);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CreditImmobilierDTO>> getCreditImmobilierByClientId(@PathVariable Long clientId) {
        List<CreditImmobilierDTO> creditDTOs = creditImmobilierService.getCreditImmobilierByClientId(clientId).stream()
                .map(credit -> (CreditImmobilierDTO) creditMapper.toDto(credit))
                .collect(Collectors.toList());
        return ResponseEntity.ok(creditDTOs);
    }

    @GetMapping("/type-bien/{typeBien}")
    public ResponseEntity<List<CreditImmobilierDTO>> getCreditImmobilierByTypeBien(@PathVariable String typeBien) {
        List<CreditImmobilierDTO> creditDTOs = creditImmobilierService.getCreditImmobilierByTypeBien(typeBien).stream()
                .map(credit -> (CreditImmobilierDTO) creditMapper.toDto(credit))
                .collect(Collectors.toList());
        return ResponseEntity.ok(creditDTOs);
    }
}
