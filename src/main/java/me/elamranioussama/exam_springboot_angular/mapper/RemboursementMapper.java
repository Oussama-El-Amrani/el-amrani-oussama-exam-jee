package me.elamranioussama.exam_springboot_angular.mapper;

import me.elamranioussama.exam_springboot_angular.dto.RemboursementDTO;
import me.elamranioussama.exam_springboot_angular.entity.Remboursement;
import me.elamranioussama.exam_springboot_angular.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemboursementMapper implements EntityMapper<RemboursementDTO, Remboursement> {

    @Autowired
    private CreditRepository creditRepository;

    @Override
    public Remboursement toEntity(RemboursementDTO dto) {
        if (dto == null) {
            return null;
        }

        Remboursement remboursement = new Remboursement();
        remboursement.setId(dto.getId());
        remboursement.setDate(dto.getDate());
        remboursement.setMontant(dto.getMontant());
        remboursement.setType(dto.getType());

        if (dto.getCreditId() != null) {
            creditRepository.findById(dto.getCreditId()).ifPresent(remboursement::setCredit);
        }

        return remboursement;
    }

    @Override
    public RemboursementDTO toDto(Remboursement entity) {
        if (entity == null) {
            return null;
        }

        RemboursementDTO dto = new RemboursementDTO();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setMontant(entity.getMontant());
        dto.setType(entity.getType());

        if (entity.getCredit() != null) {
            dto.setCreditId(entity.getCredit().getId());
        }

        return dto;
    }
}
