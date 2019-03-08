package moradiauniversitaria.br.com.moradiauniversitaria.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import moradiauniversitaria.br.com.moradiauniversitaria.model.Usuario;
import moradiauniversitaria.br.com.moradiauniversitaria.service.LoginService;
import moradiauniversitaria.br.com.moradiauniversitaria.R;

public class Login extends AppCompatActivity {

    private LocalBroadcastLogin localBroadcastLogin;
    private TextInputLayout campoEmail;
    private TextInputLayout campoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        IntentFilter intentFilter = new IntentFilter(LoginService.SERVICE_LOGIN_INTENT);

        campoEmail = findViewById(R.id.campoEmail);
        campoSenha = findViewById(R.id.campoSenha);


        localBroadcastLogin = new LocalBroadcastLogin();

        LocalBroadcastManager.getInstance(this).registerReceiver(localBroadcastLogin,intentFilter);

        //startService(new Intent(this,LoginService.class));
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(localBroadcastLogin);
        super.onDestroy();
    }

    public void chamaCadastro(View v) {
        Intent intent = new Intent(getApplicationContext(), CadastroUsuario.class);
       startActivity(intent);
   }

    public void chamaHome(View v) {
        Intent intent = new Intent(getApplicationContext(), LoginService.class);
        Button btnAcesar = (Button) this.findViewById(R.id.btnAcessar);
        btnAcesar.setText(R.string.aguardar);
        btnAcesar.setEnabled(false);



        intent.putExtra("email", campoEmail.getEditText().getText().toString());
        intent.putExtra("senha", campoSenha.getEditText().getText().toString());

        startService(intent);

    }


    private class LocalBroadcastLogin extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            Usuario user = intent.getParcelableExtra("usuario");

            if(user != null) {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
            }

//            Log.i("usuario na responta", intent.getParcelableExtra("usuario").toString());
        }
    }

}






























/*
    * final Button acessar = (Button) findViewById(R.id.btnAcessar);

        acessar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                acessar.setText(R.string.aguardar);
                acessar.setEnabled(false);

                Intent intent = new Intent(Login.this, LoginService.class);

                EditText email = (EditText)findViewById(R.id.campoEmail);
                EditText senha = (EditText)findViewById(R.id.campoSenha);

                intent.putExtra("email", email.getText().toString());
                intent.putExtra("senha", senha.getText().toString());

                startService(intent);

            }
        })
    * */


