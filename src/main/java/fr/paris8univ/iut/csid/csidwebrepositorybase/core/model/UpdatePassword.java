package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

public class UpdatePassword {
    private String token;
    private String newPassword;

    public UpdatePassword(String token, String newPassword) {
        this.token = token;
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
