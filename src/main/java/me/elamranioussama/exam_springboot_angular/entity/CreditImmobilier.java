package me.elamranioussama.exam_springboot_angular.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CreditImmobilier extends Credit {
    private String typeBien; // Appartement, Maison, Local Commercial
    // Getters and setters
}
