package com.chattingweb.backend.services.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DeletePost {
    @NotBlank(message = "post ID is required")
    private Integer ID;

    @NotBlank(message = "Delete reason is required")
    @Size(max = 200,message = "Length of ban reason must be below 200")
    private String deleteReason;

    public Integer getID() {
        return ID;
    }
    public String getdeleteReason() {
        return deleteReason;
    }
}
