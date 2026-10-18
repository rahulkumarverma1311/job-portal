package com.rahul.job.model.embedable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Builder
@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobLocation {

    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
