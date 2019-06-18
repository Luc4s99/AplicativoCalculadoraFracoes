package com.example.calculadorafraes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class DemonstracaoActivity extends AppCompatActivity {

    private Button btnVolta;
    private TextView operacao;
    private RatingBar rb1;
    private RatingBar rb2;
    private RatingBar rb3;
    public String oper;
    public int n1;
    public int n2;
    public int d1;
    public int d2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demonstracao);

        btnVolta = findViewById(R.id.btnVolta);
        operacao = findViewById(R.id.operDem);
        rb1 = findViewById(R.id.rep1);
        rb2 = findViewById(R.id.rep2);
        rb3 = findViewById(R.id.rep3);


        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        if(params != null){
            n1 = params.getInt("num1");
            n2 = params.getInt("num2");
            d1 = params.getInt("den1");
            d2 = params.getInt("den2");
            oper = params.getString("oper");

            //Setando a operação a ser realizada
            operacao.setText(oper);

            //Caso os denominadores sejam iguais
            if(d1 == d2){

                rb1.setNumStars(d1);
                rb1.setRating((float)n1);

                rb2.setNumStars(d2);
                rb2.setRating((float)n2);

                if(oper.equals("+")){
                    rb3.setNumStars(d1);
                    rb3.setRating((float)(n1+n2));
                }else{
                    rb3.setNumStars(d1);
                    rb3.setRating((float)(n1-n2));
                }
            //Caso os denominadores sejam diferentes
            }else{
                rb1.setNumStars(d1);
                rb1.setRating((float)n1);

                rb2.setNumStars(d2);
                rb2.setRating((float)n2);

                if(oper.equals("+")){
                    int auxDen = mmc(d1, d2);
                    int auxNum = (((auxDen / d1) * n1) + ((auxDen / d2) * n2));

                    rb3.setNumStars(auxDen);
                    rb3.setRating(auxNum);
                }else{
                    int auxDen = mmc(d1, d2);
                    int auxNum = (((auxDen / d1) * n1) - ((auxDen / d2) * n2));

                    rb3.setNumStars(auxDen);
                    rb3.setRating(auxNum);
                }
            }
        }

        btnVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaResol();
            }
        });
    }

    private void telaResol(){
        Intent i = new Intent(this, ResolucaoActivity.class);
        startActivity(i);
    }

    private static int mdc(int a, int b){
        while(b != 0){
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    //Algoritmo para o cálculo do MMC
    private static int mmc(int a, int b){
        return a * (b / mdc(a, b));
    }
}
