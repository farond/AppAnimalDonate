package br.usjt.appanimaldonate.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UsuarioDao {


    //Update e registro de user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Usuario usuario);

    @Query("DELETE FROM usuario")
    void deleteAll();

    @Query("SELECT * from usuario LIMIT 1")
    LiveData<Usuario> getUsuario();

}
