package com.example.appcopa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appcopa.TBancoDados.ConfiguracaoModel;


public class SelectLanguage extends AppCompatActivity {
    private ConfiguracaoModel banco_dados= new ConfiguracaoModel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_language);
        Button btnPortugues=findViewById(R.id.portugues);
        Intent intent=new Intent(SelectLanguage.this,GrupoSelecao.class);
        btnPortugues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codLingua="pt";
                processLingua(codLingua);
                startActivity(intent);
                finish();
            }
        });
    }

    public void processLingua(String codLingua)
    {
        banco_dados.SalvarLingua(codLingua);
        Toast.makeText(this,"Lingua salva no banco!",Toast.LENGTH_SHORT).show();
    }
}