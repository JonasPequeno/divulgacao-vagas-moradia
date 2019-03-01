package moradiauniversitaria.br.com.moradiauniversitaria;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import moradiaUniversitaria.service.LoginService;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }



   public void chamaCadastro(View v) {
        Intent intent = new Intent(getApplicationContext(), CadastroUsuario.class);
       startActivity(intent);
   }

    public void chamaHome(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
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
}
