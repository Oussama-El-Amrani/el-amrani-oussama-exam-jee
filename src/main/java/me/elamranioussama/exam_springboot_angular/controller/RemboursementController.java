package me.elamranioussama.exam_springboot_angular.controller;

import me.elamranioussama.exam_springboot_angular.dto.RemboursementDTO;
import me.elamranioussama.exam_springboot_angular.entity.Remboursement;
import me.elamranioussama.exam_springboot_angular.mapper.RemboursementMapper;
import me.elamranioussama.exam_springboot_angular.service.IRemboursementService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/remboursements")
public class RemboursementController {

    private final IRemboursementService remboursementService;
    private final RemboursementMapper mapper;

    public RemboursementController(IRemboursementService remboursementService, RemboursementMapper mapper) {
        this.remboursementService = remboursementService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<RemboursementDTO>> getAllRemboursements() {
        return ResponseEntity.ok(remboursementService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemboursementDTO> getRemboursementById(@PathVariable Long id) {
        return ResponseEntity.ok(remboursementService.findById(id).orElseThrow(() ->
            new RuntimeException("Remboursement not found with id: " + id)));
    }

    @PostMapping
    public ResponseEntity<RemboursementDTO> createRemboursement(@RequestBody RemboursementDTO remboursementDTO) {
        // For new repayments, ensure ID is not set
        remboursementDTO.setId(null);
        return new ResponseEntity<>(remboursementService.save(remboursementDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRemboursement(@PathVariable Long id) {
        remboursementService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/credit/{creditId}")
    public ResponseEntity<List<RemboursementDTO>> getRemboursementsByCreditId(@PathVariable Long creditId) {
        return ResponseEntity.ok(remboursementService.findByCreditId(creditId));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<RemboursementDTO>> getRemboursementsByDateBetween(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(remboursementService.findByDateBetween(startDate, endDate));
    }

    @GetMapping("/credit/{creditId}/total")
    public ResponseEntity<Double> getTotalPaymentsByCreditId(@PathVariable Long creditId) {
        return ResponseEntity.ok(remboursementService.getTotalPaymentsByCreditId(creditId));
    }

    @GetMapping("/credit/{creditId}/remaining")
    public ResponseEntity<Double> getRemainingAmountToPay(@PathVariable Long creditId) {
        return ResponseEntity.ok(remboursementService.getRemainingAmountToPay(creditId));
    }
}
