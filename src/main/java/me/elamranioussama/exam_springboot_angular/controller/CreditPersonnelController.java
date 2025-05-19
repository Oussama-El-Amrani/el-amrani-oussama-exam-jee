package me.elamranioussama.exam_springboot_angular.controller;

import me.elamranioussama.exam_springboot_angular.dto.CreditPersonnelDTO;
import me.elamranioussama.exam_springboot_angular.entity.CreditPersonnel;
import me.elamranioussama.exam_springboot_angular.mapper.CreditMapper;
import me.elamranioussama.exam_springboot_angular.service.ICreditPersonnelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/credits/personnel")
public class CreditPersonnelController {

    private final ICreditPersonnelService creditPersonnelService;
    private final CreditMapper creditMapper;

    public CreditPersonnelController(ICreditPersonnelService creditPersonnelService, CreditMapper creditMapper) {
        this.creditPersonnelService = creditPersonnelService;
        this.creditMapper = creditMapper;
    }

    @GetMapping
    public ResponseEntity<List<CreditPersonnelDTO>> getAllCreditPersonnel() {
        List<CreditPersonnelDTO> creditDTOs = creditPersonnelService.getAllCreditPersonnel().stream()
                .map(credit -> (CreditPersonnelDTO) creditMapper.toDto(credit))
                .collect(Collectors.toList());
        return ResponseEntity.ok(creditDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditPersonnelDTO> getCreditPersonnelById(@PathVariable Long id) {
        CreditPersonnel credit = creditPersonnelService.getCreditPersonnelById(id);
        return ResponseEntity.ok((CreditPersonnelDTO) creditMapper.toDto(credit));
    }

    @PostMapping
    public ResponseEntity<CreditPersonnelDTO> createCreditPersonnel(@RequestBody CreditPersonnelDTO creditDTO) {
        // For new credits, ensure ID is not set
        creditDTO.setId(null);

        CreditPersonnel credit = (CreditPersonnel) creditMapper.toEntity(creditDTO);
        CreditPersonnel savedCredit = creditPersonnelService.saveCreditPersonnel(credit);
        return new ResponseEntity<>((CreditPersonnelDTO) creditMapper.toDto(savedCredit), HttpStatus.CREATED);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CreditPersonnelDTO>> getCreditPersonnelByClientId(@PathVariable Long clientId) {
        List<CreditPersonnelDTO> creditDTOs = creditPersonnelService.getCreditPersonnelByClientId(clientId).stream()
                .map(credit -> (CreditPersonnelDTO) creditMapper.toDto(credit))
                .collect(Collectors.toList());
        return ResponseEntity.ok(creditDTOs);
    }

    @GetMapping("/motif/{motif}")
    public ResponseEntity<List<CreditPersonnelDTO>> getCreditPersonnelByMotif(@PathVariable String motif) {
        List<CreditPersonnelDTO> creditDTOs = creditPersonnelService.getCreditPersonnelByMotif(motif).stream()
                .map(credit -> (CreditPersonnelDTO) creditMapper.toDto(credit))
                .collect(Collectors.toList());
        return ResponseEntity.ok(creditDTOs);
    }
}
