package com.example.jogodavelha;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button[] arrayButton = new Button[10];
    private String vez = "X";
    private int jogadas = 0;
    private String[] matriz = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaButtons();
        onClickButtons();
    }

    private void inicializaButtons(){
        arrayButton[1] = (Button) findViewById(R.id.btn1);
        arrayButton[2] = (Button) findViewById(R.id.btn2);
        arrayButton[3] = (Button) findViewById(R.id.btn3);
        arrayButton[4] = (Button) findViewById(R.id.btn4);
        arrayButton[5] = (Button) findViewById(R.id.btn5);
        arrayButton[6] = (Button) findViewById(R.id.btn6);
        arrayButton[7] = (Button) findViewById(R.id.btn7);
        arrayButton[8] = (Button) findViewById(R.id.btn8);
        arrayButton[9] = (Button) findViewById(R.id.btn9);
    }

    private void onClickButtons(){
        for(int x = 1; x<10; x++){
            final int finalX = x;
            arrayButton[finalX].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jogada(finalX);
                }
            });
            matriz[x]="";
        }
    }

    private void jogada(int x){
        if (matriz[x]==""){
            matriz[x]= vez;
            jogadas++;
            if (vez =="X"){
                vez = "O";
            }else{
                vez = "X";
            }
        }
        exibirButtons();
        verifica();
    }

    private void exibirButtons(){
        for (int x = 1; x<10;x++){
            arrayButton[x].setText(matriz[x]);
        }
    }

    private void verifica(){
        if(matriz[1].equals(matriz[2]) && matriz[1].equals(matriz[3]) && matriz[1].toString() != ""){
            ganhador(matriz[1]);
            return;
        }

        if(matriz[4].equals(matriz[5]) && matriz[4].equals(matriz[6]) && matriz[4].toString() != ""){
            ganhador(matriz[4]);
            return;
        }

        if(matriz[7].equals(matriz[8]) && matriz[7].equals(matriz[9]) && matriz[7].toString() != ""){
            ganhador(matriz[7]);
            return;
        }

        if(matriz[1].equals(matriz[4]) && matriz[1].equals(matriz[7]) && matriz[1].toString() != ""){
            ganhador(matriz[1]);
            return;
        }

        if(matriz[2].equals(matriz[5]) && matriz[2].equals(matriz[8]) && matriz[2].toString() != ""){
            ganhador(matriz[2]);
            return;
        }

        if(matriz[3].equals(matriz[6]) && matriz[3].equals(matriz[9]) && matriz[3].toString() != ""){
            ganhador(matriz[3]);
            return;
        }

        if(matriz[1].equals(matriz[5]) && matriz[1].equals(matriz[9]) && matriz[1].toString() != ""){
            ganhador(matriz[1]);
            return;
        }

        if(matriz[3].equals(matriz[5]) && matriz[3].equals(matriz[7]) && matriz[3].toString() != ""){
            ganhador(matriz[3]);
            return;
        }

        if (jogadas == 9){
            ganhador("");
            return;
        }
    }

    private void ganhador(String ganhador){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Resultado");
        if (ganhador.equals("")){
            builder.setMessage("Empate");
        }else{
            if (ganhador.equals("X")){
                builder.setMessage("\"X\" é o vencedor");
            }else{
                builder.setMessage("\"O\" é o vencedor");
            }
        }
        builder.setPositiveButton("Novo jogo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                jogadas = 0;
                for (int x = 1;x<10;x++){
                    matriz[x]="";
                }
                exibirButtons();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}