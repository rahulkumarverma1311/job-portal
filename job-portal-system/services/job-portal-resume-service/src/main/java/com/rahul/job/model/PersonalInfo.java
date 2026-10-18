package com.rahul.job.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalInfo {

    private String firstName;
    private String lastName;
    private String headline;

    private String email;
    private String phoneNumber;
    private String city;
    private String country;

    private String linkedinUrl;
    private String portfolioUrl;
    private String githubUrl;
    private String websiteUrl;



}
