package me.elamranioussama.exam_springboot_angular.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditProfessionnel extends Credit {
    private String motif;
    private String raisonSociale;
}
