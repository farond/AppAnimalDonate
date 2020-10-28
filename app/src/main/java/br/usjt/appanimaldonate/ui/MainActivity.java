package br.usjt.appanimaldonate.ui;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.usjt.appanimaldonate.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavegationView();
        replaceFragment(R.id.frameLayout,HomeFragment.newInstance("",""),HomeFragment.HOME_FRAGMENT_TAG,"home");
    }

    private void setupNavegationView() {
        navigationView = findViewById(R.id.bottomNav);
        navigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.home:
                    replaceFragment(R.id.frameLayout,
                            HomeFragment.newInstance("", ""),
                            HomeFragment.HOME_FRAGMENT_TAG,
                            "home");
                    break;
                case R.id.novoAnuncio:
                    replaceFragment(R.id.frameLayout,
                            NovoAnuncioFragment.newInstance("", null),
                            NovoAnuncioFragment.NOVOANUNCIO_FRAGMENT_TAG,
                            "novoAnuncio");
                    break;
//                case R.id.perfil:
//                    replaceFragment(R.id.frameLayout,
//                            PerfilFragment.newInstance("", ""),
//                            PerfilFragment.PERFIL_FRAGMENT_TAG,
//                            "perfil");
//                    break;
                case R.id.anuncios:
                    replaceFragment(R.id.frameLayout,
                            AnunciosFragment.newInstance("", ""),
                            AnunciosFragment.ANUNCIOS_FRAGMENT_TAG,
                            "anuncios");
                    break;
                case R.id.configuracao:
                    replaceFragment(R.id.frameLayout,
                            ConfiguracaoFragment.newInstance(false, ""),
                            ConfiguracaoFragment.CONFIGURACAO_FRAGMENT_TAG,
                            "configuracao");
                    break;
                default:
                    //nada
            }
            return false;
        });
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sair:
                finish();
                return (true);
        }
        return (super.onOptionsItemSelected(item));
        }




   /* @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        protected void onStart(){
            super.onStart();
            Log.d("CICLO_DE_VIDA","MainActivity --> onStart");
        }

        @Override
       protected void onResume(){
           super.onResume();
            Log.d("CICLO_DE_VIDA","MainActivity --> onResume");
        }

       @Override
        protected void onPause(){
            super.onPause();
           Log.d("CICLO_DE_VIDA","MainActivity --> onPause");
        }

        @Override
        protected void onStop(){
            super.onStop();
           Log.d("CICLO_DE_VIDA","MainActivity --> onStop");
       }

       @Override
        protected void onDestroy(){
            super.onDestroy();
            Log.d("CICLO_DE_VIDA","MainActivity --> onDestroy");
       }*/
}
