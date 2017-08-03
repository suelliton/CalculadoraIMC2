package com.example.suelliton.calculadoraimc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.suelliton.calculadoraimc.MainActivity.requestAltura;
import static com.example.suelliton.calculadoraimc.MainActivity.requestPeso;

public class CalculaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcula);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        TextView ttipo = (TextView) findViewById(R.id.TtipoPassado);
        double valorRecebido = 0;
        if(bundle.getInt("type") == requestPeso){
            valorRecebido =  bundle.getDouble("valorPassado");
            ttipo.setText("Peso");
        }else if(bundle.getInt("type") == requestAltura){
            valorRecebido =  bundle.getDouble("valorPassado");
            ttipo.setText("Altura");
        }
        final EditText eeditaValor =  (EditText) findViewById(R.id.EeditaValor);
        eeditaValor.setText(""+valorRecebido);


        Button balterar = (Button) findViewById(R.id.Balterar);
        balterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle =  new Bundle();
                bundle.putDouble("valorModificado",Double.parseDouble(eeditaValor.getText().toString()));
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        Button bcancelar = (Button) findViewById(R.id.Bcancelar);
        bcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });


    }
}
