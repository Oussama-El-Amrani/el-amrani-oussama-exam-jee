package me.elamranioussama.exam_springboot_angular.service.impl;

import me.elamranioussama.exam_springboot_angular.entity.Credit;
import me.elamranioussama.exam_springboot_angular.repository.CreditRepository;
import me.elamranioussama.exam_springboot_angular.service.ICreditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CreditServiceImpl implements ICreditService {

    private final CreditRepository creditRepository;

    public CreditServiceImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    public Credit getCreditById(Long id) {
        return creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + id));
    }

    @Override
    public List<Credit> getAllCredits() {
        return creditRepository.findAll();
    }

    @Override
    public List<Credit> getCreditsByClientId(Long clientId) {
        return creditRepository.findAll().stream()
                .filter(credit -> credit.getClient().getId().equals(clientId))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCredit(Long id) {
        // Check if credit exists
        getCreditById(id);
        creditRepository.deleteById(id);
    }

    @Override
    public Credit updateCreditStatus(Long id, String status) {
        Credit credit = getCreditById(id);
        credit.setStatut(status);

        // If status is "Accepté", set acceptance date
        if ("Accepté".equals(status)) {
            credit.setDateAcception(LocalDate.now());
        }

        return creditRepository.save(credit);
    }
}