package me.elamranioussama.exam_springboot_angular.service;

import me.elamranioussama.exam_springboot_angular.dto.CreditDTO;
import me.elamranioussama.exam_springboot_angular.dto.CreditImmobilierDTO;
import me.elamranioussama.exam_springboot_angular.dto.CreditPersonnelDTO;
import me.elamranioussama.exam_springboot_angular.dto.CreditProfessionnelDTO;

import java.util.List;
import java.util.Optional;

public interface CreditService {

    List<CreditDTO> findAll();

    Optional<CreditDTO> findById(Long id);

    CreditPersonnelDTO savePersonnel(CreditPersonnelDTO creditDTO);

    CreditImmobilierDTO saveImmobilier(CreditImmobilierDTO creditDTO);

    CreditProfessionnelDTO saveProfessionnel(CreditProfessionnelDTO creditDTO);

    void delete(Long id);

    // Additional business methods
    List<CreditDTO> findByClientId(Long clientId);

    List<CreditDTO> findByStatus(String status);

    void updateCreditStatus(Long creditId, String newStatus);

    double calculateTotalInterest(Long creditId);

    double calculateMonthlyPayment(Long creditId);
}
