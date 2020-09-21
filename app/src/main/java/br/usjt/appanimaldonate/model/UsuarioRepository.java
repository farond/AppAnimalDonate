package br.usjt.appanimaldonate.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

public class UsuarioRepository {

    private UsuarioDao usuarioDao;
    private LiveData<Usuario> usuario;

    UsuarioRepository(Application application) {
       PetFriendlyRoomDatabase db = PetFriendlyRoomDatabase.getDatabase(application);
        usuarioDao = db.usuarioDao();
        usuario = usuarioDao.getUsuario();
    }


    LiveData<Usuario> getUsuario() {
        return usuario;
    }

    void insert(Usuario usuario) {
        PetFriendlyRoomDatabase.databaseWriteExecutor.execute(() -> {
            usuarioDao.insert(usuario);
        });
    }
}
