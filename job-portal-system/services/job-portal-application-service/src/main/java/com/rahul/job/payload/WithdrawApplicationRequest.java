package com.rahul.job.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawApplicationRequest {

    private String reason;

}
