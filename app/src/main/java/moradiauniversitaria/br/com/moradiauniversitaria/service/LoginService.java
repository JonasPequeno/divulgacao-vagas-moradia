package moradiauniversitaria.br.com.moradiauniversitaria.service;

import android.app.AlertDialog;
import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.util.Log;
import android.widget.Toast;

import moradiauniversitaria.br.com.moradiauniversitaria.model.Usuario;
import moradiauniversitaria.br.com.moradiauniversitaria.view.MainActivity;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginService extends IntentService {

    public static final String SERVICE_LOGIN_INTENT = "service.login.intent";
    private static Usuario userResponse;

    public LoginService() {
        super("LoginService");
    }

    @Override
    protected void onHandleIntent(@Nullable final Intent intent) {

        userResponse = new Usuario();

        String email = intent.getStringExtra("email");
        String senha = intent.getStringExtra("senha");

        Usuario usuario = new Usuario();

        usuario.setEmail(email);
        usuario.setSenha(senha);

        //usuario.setEmail("Jonas@email.com");
        //usuario.setSenha("123");
        usuario.setEmail(email);
        usuario.setSenha(senha);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UsuarioService.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsuarioService service = retrofit.create(UsuarioService.class);

        Call<Usuario> callService = service.autentica(usuario);

        try {
            callService.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        userResponse = response.body();
                        Log.d("Retorno do response", userResponse.toString());

                        intent.setAction(SERVICE_LOGIN_INTENT);

                        Log.d("Usuario Login", userResponse.toString());

                        intent.putExtra("usuario", userResponse);
                        LocalBroadcastManager.getInstance(LoginService.this).sendBroadcast(intent);
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
