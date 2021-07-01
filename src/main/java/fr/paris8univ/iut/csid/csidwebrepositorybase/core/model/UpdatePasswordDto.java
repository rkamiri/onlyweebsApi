package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import lombok.Data;

@Data
public class UpdatePasswordDto {
    private String token;
    private String newPassword;

    public UpdatePasswordDto(String token, String newPassword) {
        this.token = token;
        this.newPassword = newPassword;
    }
}
