package me.elamranioussama.exam_springboot_angular.service.impl;

import me.elamranioussama.exam_springboot_angular.entity.CreditProfessionnel;
import me.elamranioussama.exam_springboot_angular.repository.CreditProfessionnelRepository;
import me.elamranioussama.exam_springboot_angular.service.ICreditProfessionnelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CreditProfessionnelServiceImpl implements ICreditProfessionnelService {

    private final CreditProfessionnelRepository creditProfessionnelRepository;

    public CreditProfessionnelServiceImpl(CreditProfessionnelRepository creditProfessionnelRepository) {
        this.creditProfessionnelRepository = creditProfessionnelRepository;
    }

    @Override
    public CreditProfessionnel saveCreditProfessionnel(CreditProfessionnel creditProfessionnel) {
        return creditProfessionnelRepository.save(creditProfessionnel);
    }

    @Override
    public CreditProfessionnel getCreditProfessionnelById(Long id) {
        return creditProfessionnelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit professionnel not found with id: " + id));
    }

    @Override
    public List<CreditProfessionnel> getAllCreditProfessionnel() {
        return creditProfessionnelRepository.findAll();
    }

    @Override
    public List<CreditProfessionnel> getCreditProfessionnelByClientId(Long clientId) {
        return creditProfessionnelRepository.findAll().stream()
                .filter(credit -> credit.getClient().getId().equals(clientId))
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditProfessionnel> getCreditProfessionnelByRaisonSociale(String raisonSociale) {
        return creditProfessionnelRepository.findAll().stream()
                .filter(credit -> credit.getRaisonSociale().equals(raisonSociale))
                .collect(Collectors.toList());
    }
}
