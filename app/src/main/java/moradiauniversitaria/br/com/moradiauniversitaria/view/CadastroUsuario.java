package moradiauniversitaria.br.com.moradiauniversitaria.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import moradiauniversitaria.br.com.moradiauniversitaria.R;
import moradiauniversitaria.br.com.moradiauniversitaria.aplication.MoradiaUniversitaria;
import moradiauniversitaria.br.com.moradiauniversitaria.model.Endereco;
import moradiauniversitaria.br.com.moradiauniversitaria.model.Usuario;
import moradiauniversitaria.br.com.moradiauniversitaria.service.CadastroService;
import moradiauniversitaria.br.com.moradiauniversitaria.service.LoginService;

public class CadastroUsuario extends AppCompatActivity {

    private CadastroUsuario.LocalBroadcastLogin localBroadcastLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cadastra();

        IntentFilter intentFilter = new IntentFilter(CadastroService.SERVICE_CADASTRO_USUARIO_INTENT);

        localBroadcastLogin = new CadastroUsuario.LocalBroadcastLogin();

        LocalBroadcastManager.getInstance(this).registerReceiver(localBroadcastLogin, intentFilter);


    }


    public void cadastra() {


        Button btnCadastrar = (Button) CadastroUsuario.this.findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CadastroService.class);

                EditText nome = findViewById(R.id.campoNome);
                intent.putExtra("nome", nome.getText().toString());

                EditText email = findViewById(R.id.campoEmail);
                intent.putExtra("email", email.getText().toString());

                EditText senha = findViewById(R.id.campoSenha);
                intent.putExtra("senha", senha.getText().toString());

                EditText instituicao = findViewById(R.id.campoInstituicao);
                intent.putExtra("instituicao", instituicao.getText().toString());

                EditText estado = findViewById(R.id.campoEstado);
                intent.putExtra("estado", estado.getText().toString());

                EditText rua = findViewById(R.id.campoRua);
                intent.putExtra("rua", rua.getText().toString());

                EditText cidade = findViewById(R.id.campoCidade);
                intent.putExtra("cidade", cidade.getText().toString());

                EditText numero = findViewById(R.id.campoNumero);
                intent.putExtra("numero", numero.getText().toString());

                startService(intent);
            }
        });
    }

    private class LocalBroadcastLogin extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            Usuario user = intent.getParcelableExtra("usuario");

            if(user.getEmail() != null ) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CadastroUsuario.this);

                alertDialog.setTitle("Cadastro Realizado com Sucesso!");

                alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                    }
                });
                alertDialog.setCancelable(true);
                AlertDialog alerta = alertDialog.create();
                alerta.show();
            }

        }
    }
}

