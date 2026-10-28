package com.rahul.job.dto;

import com.rahul.job.domain.LanguageProficiency;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LanguageResponse {
    private Long id;
    private String languageName;
    private LanguageProficiency languageProficiency;
    private Integer displayOrder;
}
