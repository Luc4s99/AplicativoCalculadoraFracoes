package com.example.calculadorafraes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResolucaoActivity extends AppCompatActivity {

    private Button btnVoltar;
    private TextView demTexto;
    private Button btnDem;
    private boolean DEMONSTRAR = true;
    public String oper;
    public int n1;
    public int n2;
    public int d1;
    public int d2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolucao);

        btnVoltar = findViewById(R.id.btnVolta);
        demTexto = findViewById(R.id.resolucao);
        btnDem = findViewById(R.id.demonstrar);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        if(params != null){

            n1 = params.getInt("num1");
            n2 = params.getInt("num2");
            d1 = params.getInt("den1");
            d2 = params.getInt("den2");
            oper = params.getString("oper");

            //Caso os denominadores sejam iguais
            if(d1 == d2){
                int num;
                String result;

                if(oper.equals("+")){
                    result = "Denominadores iguais:\n\n("+n1+"+"+n2+")/"+d1+" => "+(n1+n2)+"/"+d1+" => "+(float)((n1+n2)/d1)+"";
                    num = n1 + n2;
                }else{
                    result = "Denominadores iguais:\n\n("+n1+"-"+n2+")/"+d1+" => "+(n1-n2)+"/"+d1+" => "+(float)((n1-n2)/d1)+"";
                    num = n1 - n2;
                }

                demTexto.setText(result);

                if(num < 0){
                    DEMONSTRAR = false;
                    Toast.makeText(this, "Não será possível representar uma fração negativa", Toast.LENGTH_LONG).show();
                }else if(num > d1 || d1 > 10){
                        DEMONSTRAR = false;
                        Toast.makeText(this, "Números muito grandes! Representação não poderá ser exibida", Toast.LENGTH_LONG).show();
                }
            }else{
                int mmc = mmc(d1, d2);
                int num;
                String result;

                if(oper.equals("+")){
                    result = "Denominadores diferentes:\n\n MMC entre "+d1+" e "+d2+" = "+mmc+"\n\n"+(mmc/d1)*n1+"/"+mmc+" + "+(mmc/d2)*n2+"/"+mmc+" => ("+(((mmc/d1)*n1)+" + "+((mmc/d2)*n2))+")/"+mmc+" =>\n\n "+(((mmc/d1)*n1) + ((mmc/d2)*n2))+"/"+mmc+" => "+(float)(((mmc/d1)*n1) + ((mmc/d2)*n2))/mmc+"";
                    num = ((mmc/d1)*n1) + ((mmc/d2)*n2);

                }else{
                    result = "Denominadores diferentes:\n\n MMC entre "+d1+" e "+d2+" = "+mmc+"\n\n"+(mmc/d1)*n1+"/"+mmc+" - "+(mmc/d2)*n2+"/"+mmc+" => ("+(((mmc/d1)*n1)+" - "+((mmc/d2)*n2))+")/"+mmc+" =>\n\n "+(((mmc/d1)*n1) - ((mmc/d2)*n2))+"/"+mmc+" => "+(float)(((mmc/d1)*n1) - ((mmc/d2)*n2))/mmc+"";
                    num = ((mmc/d1)*n1) - ((mmc/d2)*n2);
                }

                demTexto.setText(result);

                if(num < 0){
                    DEMONSTRAR = false;
                    Toast.makeText(this, "Não será possível representar uma fração negativa", Toast.LENGTH_LONG).show();
                }else if(num > mmc || num > 10 || mmc > 10){
                    DEMONSTRAR = false;
                    Toast.makeText(this, "Números muito grandes! Representação não poderá ser exibida", Toast.LENGTH_LONG).show();
                }
            }
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaPrincipal();
            }
        });

        btnDem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle params = new Bundle();
                params.putInt("num1", n1);
                params.putInt("num2", n2);
                params.putInt("den1", d1);
                params.putInt("den2", d2);
                params.putString("oper", oper);
                telaDem(params);
            }
        });
    }

    public void telaPrincipal(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void telaDem(Bundle params){
        if(DEMONSTRAR){
            Intent i = new Intent(this, DemonstracaoActivity.class);
            i.putExtras(params);
            startActivity(i);
        }else{
            Toast.makeText(this, "Não será possível representar a fração", Toast.LENGTH_LONG).show();
        }
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
