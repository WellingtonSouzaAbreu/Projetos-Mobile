package com.example.jokenpo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView jogador1;
    ImageView jogador2;
    ImageView versus;

    ImageButton btPedra;
    ImageButton btPapel;
    ImageButton btTesoura;
    TextView lbMsg;

    TextView txJogador1;
    TextView txJogador2;

    Animation some;
    Animation aparece;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jogador1 = findViewById(R.id.jogador1);
        jogador2 = findViewById(R.id.jogador2);
        versus = findViewById(R.id.versus);
        btPedra = findViewById(R.id.pedra);
        btPapel = findViewById(R.id.papel);
        btTesoura = findViewById(R.id.tesoura);
        lbMsg = findViewById(R.id.lbMsg);
        txJogador1 = findViewById(R.id.txJogador1);
        txJogador2 = findViewById(R.id.txJogador2);

        some = new AlphaAnimation(1,0);
        aparece = new AlphaAnimation(0,1);

        some.setDuration(1000);
        aparece.setDuration(100);

        some.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                jogador2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jogador2.setVisibility(View.INVISIBLE);
                jogador2.startAnimation(aparece);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        aparece.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                sorteiaJogada();
                jogador2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jogador2.setVisibility(View.VISIBLE);
                verificaJogada();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public int jogada1 = -1;
    public int jogada2 = -1;

    public void sorteiaJogada(){
        Random r = new Random();
        int valor = r.nextInt(3);

        switch(valor){
            case(0): jogador2.setImageResource(R.drawable.pedra);
                jogada2 = 0;
                break;
            case(1): jogador2.setImageResource(R.drawable.papel);
                jogada2 = 1;
                break;
            case(2): jogador2.setImageResource(R.drawable.tesoura);
                jogada2 = 2;
                break;
        }
    }

    int j1 = 0;
    int j2 = 0;

    public void verificaJogada(){
        if ((jogada1 == 0 && jogada2 == 1) || (jogada1 == 1 && jogada2 == 2) || (jogada1 == 2 && jogada2 == 0)){
           lbMsg.setText("Que pena, você perdeu!");
            j2 = j2 + 1;
            txJogador2.setText("Zézinho: " + j2);
        }
        if (jogada1 == 1 && (jogada2 == 0) || (jogada1 == 2) && (jogada2 == 1) || (jogada1 == 0) && (jogada2 == 2)){
            lbMsg.setText("Parabéns, você venceu!");
            j1 = j1 + 1;
            txJogador1.setText("Você: " + j1);
        }
        if (jogada1 == jogada2){
            lbMsg.setText("Empatoou!");
        }
        jogada1 = -2;
    }

    public void zerarPlacar(View view){
    j1 = 0;
    j2 = 0;
    txJogador1.setText("Você: " + j1);
    txJogador2.setText("Zézinho: " + j2);
    }

    public void reeniciarJogo(){
        jogada1 = -1;
        jogada2 = -1;

        jogador1.setImageResource(R.drawable.interrogacao);
        jogador2.setImageResource(R.drawable.interrogacao);
        lbMsg.setText("Faça Sua jogada...");
    }

    /* new Timer().schedule(new TimerTask() {
        @Override
        public void run () {

        }

    }, 2000); */

    public void pressBotao(View view){

        if (jogada1 == -2){
           reeniciarJogo();
           return;
        }

        if (jogada1 == -1) {

            jogador1.setScaleX(-1);
            switch (view.getId()) {
                case (R.id.pedra):
                    jogador1.setImageResource(R.drawable.pedra);
                    jogada1 = 0;
                    break;
                case (R.id.papel):
                    jogador1.setImageResource(R.drawable.papel);
                    jogada1 = 1;
                    break;
                case (R.id.tesoura):
                    jogador1.setImageResource(R.drawable.tesoura);
                    jogada1 = 2;
                    break;
            }
            jogador2.startAnimation(some);
        }




    }
}
