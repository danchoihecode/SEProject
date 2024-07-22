package com.chattingweb.backend.services.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class ApproveReport {
    @NotBlank(message = "Ban reason is required")
    @Size(max = 200,message = "Length of ban reason must be below 200")
    private String banReason;

    @NotBlank(message = "duration is required")
    private Integer duration;

    public Integer getDuration() {
        return duration;
    }
    public String getBanReason() {
        return banReason;
    }

}