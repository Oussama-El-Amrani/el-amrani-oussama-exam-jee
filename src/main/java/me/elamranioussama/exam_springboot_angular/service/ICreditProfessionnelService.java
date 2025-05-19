package me.elamranioussama.exam_springboot_angular.service;

import me.elamranioussama.exam_springboot_angular.entity.CreditProfessionnel;

import java.util.List;

public interface ICreditProfessionnelService {
    CreditProfessionnel saveCreditProfessionnel(CreditProfessionnel creditProfessionnel);
    CreditProfessionnel getCreditProfessionnelById(Long id);
    List<CreditProfessionnel> getAllCreditProfessionnel();
    List<CreditProfessionnel> getCreditProfessionnelByClientId(Long clientId);
    List<CreditProfessionnel> getCreditProfessionnelByRaisonSociale(String raisonSociale);
}
