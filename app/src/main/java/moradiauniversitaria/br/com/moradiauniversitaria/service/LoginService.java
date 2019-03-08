package moradiauniversitaria.br.com.moradiauniversitaria.service;

import android.app.IntentService;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;

import moradiauniversitaria.br.com.moradiauniversitaria.model.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginService extends IntentService {

    public static final String SERVICE_LOGIN_INTENT = "service.login.intent";
    public Usuario userResponse;

    public LoginService() {
        super("LoginService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        String email = intent.getStringExtra("email");
        String senha = intent.getStringExtra("senha");

        Usuario usuario = new Usuario();


        usuario.setEmail("Jonas@email.com");
        usuario.setSenha("123");

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(UsuarioService.URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            UsuarioService service = retrofit.create(UsuarioService.class);

                Call<Usuario> callService = service.autentica(usuario);

            callService.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if(response.isSuccessful()){
                            userResponse = new Usuario();
                            userResponse = response.body();
                           Log.i("--usuario na responta--", response.body().toString());
                        }else  {
                            Log.i("Sem sucesso", "sem");
                        }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Log.i("Erro nessa bosta", t.getMessage());
                }
            });

        intent.setAction(SERVICE_LOGIN_INTENT);
        intent.putExtra("usuario", userResponse);

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }


}
