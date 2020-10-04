package br.usjt.appanimaldonate.model;

import android.util.Patterns;

public class LoginUsuario {
    private String strEmail;
    private String strPassword;

    public LoginUsuario(String Email, String Password) {
        strEmail = Email;
        strPassword = Password;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getStrEmail()).matches();
    }

    public boolean isPasswordLengthGreaterThan5() {
        return getStrPassword().length() > 6;
    }
}
