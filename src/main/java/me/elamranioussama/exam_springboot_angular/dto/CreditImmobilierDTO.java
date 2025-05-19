package me.elamranioussama.exam_springboot_angular.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditImmobilierDTO extends CreditDTO {
    private String typeBien;
}
