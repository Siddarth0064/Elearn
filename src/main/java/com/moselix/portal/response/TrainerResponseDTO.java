package com.moselix.portal.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrainerResponseDTO {
    private Long trainerId;
    private String name;
    private String email;
    private String phone;
    private String expertise;
}
