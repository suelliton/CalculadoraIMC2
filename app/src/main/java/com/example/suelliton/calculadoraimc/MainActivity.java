package com.example.suelliton.calculadoraimc;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    public final static int requestPeso = 1;
    public final static int requestAltura = 2;
    Context c = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tpeso = (TextView) findViewById(R.id.Tpeso);
        final TextView taltura = (TextView) findViewById(R.id.Taltura);

        Button balteraPeso = (Button) findViewById(R.id.BalteraPeso);
        balteraPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putDouble("valorPassado",Double.parseDouble(tpeso.getText().toString()));
                Intent intent = new Intent(c,CalculaActivity.class);
                int type = requestPeso;
                bundle.putInt("type",type);
                intent.putExtras(bundle);
                startActivityForResult(intent,requestPeso);
            }
        });

        Button balteraAltura = (Button) findViewById(R.id.BalteraAltura);
        balteraAltura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putDouble("valorPassado",Double.parseDouble(taltura.getText().toString()));
                int type = requestAltura;
                bundle.putInt("type",type);
                Intent intent = new Intent(c,CalculaActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,requestAltura);
            }
        });

        Button bcalcular = (Button) findViewById(R.id.Bcalcular);
        bcalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularImc();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == requestPeso){
            if(resultCode == RESULT_OK){
                TextView tpeso = (TextView) findViewById(R.id.Tpeso);
                Bundle bundle = data.getExtras();
                tpeso.setText(""+bundle.getDouble("valorModificado"));
            }else if(resultCode == RESULT_CANCELED){
                Toast.makeText(c, "Operação cancelada", Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode == requestAltura){
            if(resultCode == RESULT_OK){
                TextView taltura = (TextView) findViewById(R.id.Taltura);
                Bundle bundle = data.getExtras();
                taltura.setText(""+bundle.getDouble("valorModificado"));
            }else if(resultCode == RESULT_CANCELED){
                Toast.makeText(c, "Operação cancelada", Toast.LENGTH_SHORT).show();
            }
        }
    }
    protected void calcularImc(){
        TextView tpeso = (TextView) findViewById(R.id.Tpeso);
        TextView taltura = (TextView) findViewById(R.id.Taltura);
        Double IMC =  Double.parseDouble(tpeso.getText().toString())/Math.pow(Double.parseDouble(taltura.getText().toString()),2);
        TextView timc = (TextView) findViewById(R.id.Timc);
        timc.setText(""+IMC);

        String message = "";

        if(IMC <18.5){
            message = "Abaixo do peso";
        }else if(IMC > 18.5 && IMC < 25 ){
            message = "Peso ideal";
        }else if(IMC > 25 && IMC < 30 ){
            message = "Acima do peso";
        }else if(IMC > 30 && IMC < 35 ){
            message = "Obesidade grau I";
        }else if(IMC > 35 && IMC < 40){
            message = "Obesidade grau II";
        }else{
            message = "Obesidade morbida";
        }

        TextView tresultado = (TextView) findViewById(R.id.Tresultado);
        tresultado.setText(message);
    }
}
