package com.moselix.portal.request;


import lombok.Data;

@Data
public class TrainerRequestDTO {
    private String name;
    private String email;
    private String phone;
    private String expertise;
}
