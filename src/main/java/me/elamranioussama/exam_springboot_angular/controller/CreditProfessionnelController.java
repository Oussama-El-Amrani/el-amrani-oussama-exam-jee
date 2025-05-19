package me.elamranioussama.exam_springboot_angular.controller;

import me.elamranioussama.exam_springboot_angular.dto.CreditProfessionnelDTO;
import me.elamranioussama.exam_springboot_angular.entity.CreditProfessionnel;
import me.elamranioussama.exam_springboot_angular.mapper.CreditMapper;
import me.elamranioussama.exam_springboot_angular.service.ICreditProfessionnelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/credits/professionnel")
public class CreditProfessionnelController {

    private final ICreditProfessionnelService creditProfessionnelService;
    private final CreditMapper creditMapper;

    public CreditProfessionnelController(ICreditProfessionnelService creditProfessionnelService, CreditMapper creditMapper) {
        this.creditProfessionnelService = creditProfessionnelService;
        this.creditMapper = creditMapper;
    }

    @GetMapping
    public ResponseEntity<List<CreditProfessionnelDTO>> getAllCreditProfessionnel() {
        List<CreditProfessionnelDTO> creditDTOs = creditProfessionnelService.getAllCreditProfessionnel().stream()
                .map(credit -> (CreditProfessionnelDTO) creditMapper.toDto(credit))
                .collect(Collectors.toList());
        return ResponseEntity.ok(creditDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditProfessionnelDTO> getCreditProfessionnelById(@PathVariable Long id) {
        CreditProfessionnel credit = creditProfessionnelService.getCreditProfessionnelById(id);
        return ResponseEntity.ok((CreditProfessionnelDTO) creditMapper.toDto(credit));
    }

    @PostMapping
    public ResponseEntity<CreditProfessionnelDTO> createCreditProfessionnel(@RequestBody CreditProfessionnelDTO creditDTO) {
        // For new credits, ensure ID is not set
        creditDTO.setId(null);

        CreditProfessionnel credit = (CreditProfessionnel) creditMapper.toEntity(creditDTO);
        CreditProfessionnel savedCredit = creditProfessionnelService.saveCreditProfessionnel(credit);
        return new ResponseEntity<>((CreditProfessionnelDTO) creditMapper.toDto(savedCredit), HttpStatus.CREATED);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CreditProfessionnelDTO>> getCreditProfessionnelByClientId(@PathVariable Long clientId) {
        List<CreditProfessionnelDTO> creditDTOs = creditProfessionnelService.getCreditProfessionnelByClientId(clientId).stream()
                .map(credit -> (CreditProfessionnelDTO) creditMapper.toDto(credit))
                .collect(Collectors.toList());
        return ResponseEntity.ok(creditDTOs);
    }

    @GetMapping("/raison-sociale/{raisonSociale}")
    public ResponseEntity<List<CreditProfessionnelDTO>> getCreditProfessionnelByRaisonSociale(@PathVariable String raisonSociale) {
        List<CreditProfessionnelDTO> creditDTOs = creditProfessionnelService.getCreditProfessionnelByRaisonSociale(raisonSociale).stream()
                .map(credit -> (CreditProfessionnelDTO) creditMapper.toDto(credit))
                .collect(Collectors.toList());
        return ResponseEntity.ok(creditDTOs);
    }
}
