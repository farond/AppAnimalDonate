package br.usjt.appanimaldonate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout constraintLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        Log.d("CICLO_DE_VIDA","MainActivity --> onCreate");
    }

    public void novoCadastro(View view){

        Intent intent = new Intent(this, CadastroUsuarioActivity.class);

        startActivity(intent);
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
    }
}