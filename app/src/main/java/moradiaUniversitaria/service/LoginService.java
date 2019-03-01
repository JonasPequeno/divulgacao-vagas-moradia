package moradiaUniversitaria.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import moradiaUniversitaria.model.Usuario;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginService extends IntentService {

    public static final String FILTER_ACTION_LOGIN_INTENT_SERVICE = "filter.action.login.intent.service";

    public LoginService() {
        super("LoginService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        final String url = "http:///192.168.2.117:3000/api/usuario/autentica";

        final String email = intent.getStringExtra("email");
        final String senha = intent.getStringExtra("senha");


        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = new FormBody.Builder()
                .add("email", email)
                .add("senha", senha)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try {

            Response response = client.newCall(request).execute();
            Usuario cliente = new Gson().fromJson(response.body().string(), Usuario.class);
            Log.d("cleinte recuperado", cliente.toString());

            intent.setAction(FILTER_ACTION_LOGIN_INTENT_SERVICE);

            intent.putExtra("usuarioLogado", String.valueOf(cliente));

            LocalBroadcastManager.getInstance(LoginService.this).sendBroadcast(intent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
