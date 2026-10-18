package com.rahul.job.dto;

import com.rahul.job.domain.SocialPlatformName;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SocialLinkResponse {
    private SocialPlatformName platformName;
    private String url;

}
