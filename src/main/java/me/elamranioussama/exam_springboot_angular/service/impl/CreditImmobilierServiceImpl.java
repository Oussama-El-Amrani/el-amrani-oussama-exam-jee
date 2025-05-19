package me.elamranioussama.exam_springboot_angular.service.impl;

import me.elamranioussama.exam_springboot_angular.entity.CreditImmobilier;
import me.elamranioussama.exam_springboot_angular.repository.CreditImmobilierRepository;
import me.elamranioussama.exam_springboot_angular.service.ICreditImmobilierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CreditImmobilierServiceImpl implements ICreditImmobilierService {

    private final CreditImmobilierRepository creditImmobilierRepository;

    public CreditImmobilierServiceImpl(CreditImmobilierRepository creditImmobilierRepository) {
        this.creditImmobilierRepository = creditImmobilierRepository;
    }

    @Override
    public CreditImmobilier saveCreditImmobilier(CreditImmobilier creditImmobilier) {
        return creditImmobilierRepository.save(creditImmobilier);
    }

    @Override
    public CreditImmobilier getCreditImmobilierById(Long id) {
        return creditImmobilierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit immobilier not found with id: " + id));
    }

    @Override
    public List<CreditImmobilier> getAllCreditImmobilier() {
        return creditImmobilierRepository.findAll();
    }

    @Override
    public List<CreditImmobilier> getCreditImmobilierByClientId(Long clientId) {
        return creditImmobilierRepository.findAll().stream()
                .filter(credit -> credit.getClient().getId().equals(clientId))
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditImmobilier> getCreditImmobilierByTypeBien(String typeBien) {
        return creditImmobilierRepository.findAll().stream()
                .filter(credit -> credit.getTypeBien().equals(typeBien))
                .collect(Collectors.toList());
    }
}
