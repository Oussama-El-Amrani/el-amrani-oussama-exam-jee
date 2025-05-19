package me.elamranioussama.exam_springboot_angular.service;

import me.elamranioussama.exam_springboot_angular.entity.CreditImmobilier;

import java.util.List;

public interface ICreditImmobilierService {
    CreditImmobilier saveCreditImmobilier(CreditImmobilier creditImmobilier);

    CreditImmobilier getCreditImmobilierById(Long id);

    List<CreditImmobilier> getAllCreditImmobilier();

    List<CreditImmobilier> getCreditImmobilierByClientId(Long clientId);

    List<CreditImmobilier> getCreditImmobilierByTypeBien(String typeBien);
}
