package sharedpreferences.cursoandroid.com.sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText textoNome;
    private Button   botaoSalvar;
    private TextView textoExibicaot;

    private static final String ARQUIVO_PREFERENCIA = "Arquivo preferencia";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNome = (EditText) findViewById(R.id.textoNomeId);
        textoExibicaot = (TextView) findViewById(R.id.textoExibicaoId);
        botaoSalvar = (Button) findViewById(R.id.botaoSalvarId);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0); //0 ARQUIVO PRIVADO
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if( textoNome.getText().toString().equals("") ){
                    Toast.makeText(MainActivity.this, "Por favor preencher o nome", Toast.LENGTH_SHORT).show();

                }else{
                    editor.putString("nome", textoNome.getText().toString());
                    editor.commit();
                    textoExibicaot.setText("Ola, "+textoNome.getText().toString());
                }
            }
        });

        //recupera os dados salvos
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0); //0 ARQUIVO PRIVADO
        if( sharedPreferences.contains("nome")){
            String nomeUsuario = sharedPreferences.getString("nome", "usuário não definido");
            textoExibicaot.setText("Olá, " + nomeUsuario);
        }else{
            textoExibicaot.setText("Olá, usuário não definido");
        }
    }
}
