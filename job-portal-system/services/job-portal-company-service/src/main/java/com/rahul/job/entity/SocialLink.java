package com.rahul.job.entity;

import com.rahul.job.domain.SocialPlatformName;
import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Embeddable
public class SocialLink {
    private SocialPlatformName platformName;
    private String url;
}
