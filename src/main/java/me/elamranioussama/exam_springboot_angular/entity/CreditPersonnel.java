package me.elamranioussama.exam_springboot_angular.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class CreditPersonnel extends Credit {
    private String motif;
}
