package me.elamranioussama.exam_springboot_angular.service.impl;

import me.elamranioussama.exam_springboot_angular.dto.RemboursementDTO;
import me.elamranioussama.exam_springboot_angular.entity.Credit;
import me.elamranioussama.exam_springboot_angular.entity.Remboursement;
import me.elamranioussama.exam_springboot_angular.mapper.RemboursementMapper;
import me.elamranioussama.exam_springboot_angular.repository.CreditRepository;
import me.elamranioussama.exam_springboot_angular.repository.RemboursementRepository;
import me.elamranioussama.exam_springboot_angular.service.CreditService;
import me.elamranioussama.exam_springboot_angular.service.RemboursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RemboursementServiceImpl implements RemboursementService {

    @Autowired
    private RemboursementRepository remboursementRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private CreditService creditService;

    @Autowired
    private RemboursementMapper remboursementMapper;

    @Override
    @Transactional(readOnly = true)
    public List<RemboursementDTO> findAll() {
        return remboursementRepository.findAll().stream()
                .map(remboursementMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RemboursementDTO> findById(Long id) {
        return remboursementRepository.findById(id)
                .map(remboursementMapper::toDto);
    }

    @Override
    public RemboursementDTO save(RemboursementDTO remboursementDTO) {
        Remboursement remboursement = remboursementMapper.toEntity(remboursementDTO);
        remboursement = remboursementRepository.save(remboursement);
        return remboursementMapper.toDto(remboursement);
    }

    @Override
    public void delete(Long id) {
        remboursementRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RemboursementDTO> findByCreditId(Long creditId) {
        return remboursementRepository.findAll().stream()
                .filter(remboursement -> remboursement.getCredit() != null &&
                        remboursement.getCredit().getId().equals(creditId))
                .map(remboursementMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RemboursementDTO> findByDateBetween(LocalDate startDate, LocalDate endDate) {
        return remboursementRepository.findAll().stream()
                .filter(remboursement -> !remboursement.getDate().isBefore(startDate) &&
                        !remboursement.getDate().isAfter(endDate))
                .map(remboursementMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public double getTotalPaymentsByCreditId(Long creditId) {
        return remboursementRepository.findAll().stream()
                .filter(remboursement -> remboursement.getCredit() != null &&
                        remboursement.getCredit().getId().equals(creditId))
                .mapToDouble(Remboursement::getMontant)
                .sum();
    }

    @Override
    public double getRemainingAmountToPay(Long creditId) {
        Optional<Credit> creditOpt = creditRepository.findById(creditId);
        if (creditOpt.isPresent()) {
            Credit credit = creditOpt.get();
            double totalAmount = credit.getMontant() + this.calculateTotalInterest(credit);
            double totalPaid = this.getTotalPaymentsByCreditId(creditId);
            return totalAmount - totalPaid;
        }
        return 0;
    }

    private double calculateTotalInterest(Credit credit) {
        double principal = credit.getMontant();
        double rate = credit.getTauxInteret() / 100;
        int durationYears = credit.getDureeRemboursement() / 12;
        return principal * rate * durationYears;
    }
}
