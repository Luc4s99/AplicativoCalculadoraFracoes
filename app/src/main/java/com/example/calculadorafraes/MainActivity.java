package com.example.calculadorafraes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button btnSomar;
    public Button btnSubtrair;
    public EditText n1;
    public EditText d1;
    public EditText n2;
    public EditText d2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n1 = findViewById(R.id.Numerador1);
        n2 = findViewById(R.id.Numerador2);
        d1 = findViewById(R.id.Denominador1);
        d2 = findViewById(R.id.Denominador2);
        btnSomar = findViewById(R.id.btnSomar);
        btnSubtrair = findViewById(R.id.btnSubtrair);

        btnSomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificaCamposVazios()){
                    if(verificaZero()){
                        Bundle params = new Bundle();
                        params.putInt("num1", Integer.parseInt(n1.getText().toString()));
                        params.putInt("num2", Integer.parseInt(n2.getText().toString()));
                        params.putInt("den1", Integer.parseInt(d1.getText().toString()));
                        params.putInt("den2", Integer.parseInt(d2.getText().toString()));
                        params.putString("oper", "+");

                        telaResolucao(params);
                    }else{
                        Toast.makeText(getApplicationContext(),"Não aceitamos divisão por zero!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Preencha todos os campos!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSubtrair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificaCamposVazios()){
                    if(verificaZero()){
                        Bundle params = new Bundle();
                        params.putInt("num1", Integer.parseInt(n1.getText().toString()));
                        params.putInt("num2", Integer.parseInt(n2.getText().toString()));
                        params.putInt("den1", Integer.parseInt(d1.getText().toString()));
                        params.putInt("den2", Integer.parseInt(d2.getText().toString()));
                        params.putString("oper", "-");

                        telaResolucao(params);
                    }else{
                        Toast.makeText(getApplicationContext(),"Não aceitamos divisão por zero!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Preencha todos os campos!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean verificaCamposVazios(){
        return (!n1.getText().toString().equals("") &&
                !n2.getText().toString().equals("") &&
                !d1.getText().toString().equals("") &&
                !d2.getText().toString().equals(""));
    }

    public void telaResolucao(Bundle params){
        Intent intent = new Intent(this, ResolucaoActivity.class);
        intent.putExtras(params);
        startActivity(intent);
    }

    public boolean verificaZero(){

        return (!d1.getText().toString().equals("0") && !d2.getText().toString().equals("0"));
    }
}
