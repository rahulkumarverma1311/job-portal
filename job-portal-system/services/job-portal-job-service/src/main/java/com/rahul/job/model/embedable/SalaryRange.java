package com.rahul.job.model.embedable;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalaryRange {

    private BigDecimal minSalary;
    private BigDecimal maxSalary;


}
