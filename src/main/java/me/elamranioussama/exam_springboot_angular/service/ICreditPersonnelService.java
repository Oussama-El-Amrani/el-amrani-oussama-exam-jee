package me.elamranioussama.exam_springboot_angular.service;

import me.elamranioussama.exam_springboot_angular.entity.CreditPersonnel;

import java.util.List;

public interface ICreditPersonnelService {
    CreditPersonnel saveCreditPersonnel(CreditPersonnel creditPersonnel);

    CreditPersonnel getCreditPersonnelById(Long id);

    List<CreditPersonnel> getAllCreditPersonnel();

    List<CreditPersonnel> getCreditPersonnelByClientId(Long clientId);

    List<CreditPersonnel> getCreditPersonnelByMotif(String motif);
}
