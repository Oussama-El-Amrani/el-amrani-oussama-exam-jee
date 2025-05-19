package me.elamranioussama.exam_springboot_angular.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditPersonnelDTO extends CreditDTO {
    private String motif;
}
