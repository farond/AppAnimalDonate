package br.usjt.appanimaldonate.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Usuario.class}, version = 1, exportSchema = false)
public abstract class PetFriendlyRoomDatabase extends RoomDatabase {

    public abstract UsuarioDao usuarioDao();

    private static volatile PetFriendlyRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //gera uma unica instancia de banco
    static PetFriendlyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PetFriendlyRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PetFriendlyRoomDatabase.class, "petfriendly_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
