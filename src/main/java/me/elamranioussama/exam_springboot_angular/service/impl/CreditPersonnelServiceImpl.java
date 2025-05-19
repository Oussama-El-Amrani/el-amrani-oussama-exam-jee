package me.elamranioussama.exam_springboot_angular.service.impl;

import me.elamranioussama.exam_springboot_angular.entity.CreditPersonnel;
import me.elamranioussama.exam_springboot_angular.repository.CreditPersonnelRepository;
import me.elamranioussama.exam_springboot_angular.service.ICreditPersonnelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CreditPersonnelServiceImpl implements ICreditPersonnelService {

    private final CreditPersonnelRepository creditPersonnelRepository;

    public CreditPersonnelServiceImpl(CreditPersonnelRepository creditPersonnelRepository) {
        this.creditPersonnelRepository = creditPersonnelRepository;
    }

    @Override
    public CreditPersonnel saveCreditPersonnel(CreditPersonnel creditPersonnel) {
        return creditPersonnelRepository.save(creditPersonnel);
    }

    @Override
    public CreditPersonnel getCreditPersonnelById(Long id) {
        return creditPersonnelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit personnel not found with id: " + id));
    }

    @Override
    public List<CreditPersonnel> getAllCreditPersonnel() {
        return creditPersonnelRepository.findAll();
    }

    @Override
    public List<CreditPersonnel> getCreditPersonnelByClientId(Long clientId) {
        return creditPersonnelRepository.findAll().stream()
                .filter(credit -> credit.getClient().getId().equals(clientId))
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditPersonnel> getCreditPersonnelByMotif(String motif) {
        return creditPersonnelRepository.findAll().stream()
                .filter(credit -> credit.getMotif().equals(motif))
                .collect(Collectors.toList());
    }
}
