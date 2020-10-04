package br.usjt.appanimaldonate.model;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> Email = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();

    private MutableLiveData<LoginUsuario> usuarioMutableLiveData;

    public MutableLiveData<LoginUsuario> getUsuario() {

        if (usuarioMutableLiveData == null) {
            usuarioMutableLiveData = new MutableLiveData<>();
        }
        return usuarioMutableLiveData;

    }

    public void onClick(View view) {

        LoginUsuario loginUser = new LoginUsuario(Email.getValue(), Password.getValue());

        usuarioMutableLiveData.setValue(loginUser);

    }


}
