package com.rahul.job.payload;

import com.rahul.job.domain.ApplicationStatus;
import lombok.Data;

@Data
public class UpdateApplicationStatusReq {
    private ApplicationStatus status;
}
