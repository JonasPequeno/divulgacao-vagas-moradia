package moradiauniversitaria.br.com.moradiauniversitaria.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import moradiauniversitaria.br.com.moradiauniversitaria.R;
import moradiauniversitaria.br.com.moradiauniversitaria.model.Endereco;
import moradiauniversitaria.br.com.moradiauniversitaria.model.Usuario;
import moradiauniversitaria.br.com.moradiauniversitaria.view.CadastroUsuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroService extends IntentService {

    public static final String SERVICE_CADASTRO_USUARIO_INTENT = "service.cadastro.usuario.intent";
    private static Usuario userResponse;

    public CadastroService() {
        super("CadastroService");
    }

    @Override
    protected void onHandleIntent(@Nullable final Intent intent) {

        userResponse = new Usuario();
        Usuario usuario = new Usuario();

        String email = intent.getStringExtra("email");
        String senha = intent.getStringExtra("senha");
        String instituicao = intent.getStringExtra("instituicao");
        String rua = intent.getStringExtra("rua");
        String nome = intent.getStringExtra("nome");
        String cidade = intent.getStringExtra("cidade");
        String estado = intent.getStringExtra("estado");
        String numero = intent.getStringExtra("numero");

        Endereco endereco = new Endereco();

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setInstituicao(instituicao);
        endereco.setEstado(estado);
        endereco.setRua(rua);
        endereco.setCidade(cidade);
        endereco.setNumero(numero);
        usuario.setEndereco(endereco);


        Log.d("Entrou no serviço","");



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UsuarioService.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsuarioService service = retrofit.create(UsuarioService.class);

        Call<Usuario> callService = service.criaUsuario(usuario);

        try {
            callService.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        userResponse = response.body();
                        Log.d("Retorno do response", response.body().toString());

                        intent.setAction(SERVICE_CADASTRO_USUARIO_INTENT);

                        Log.d("Usuario Login", userResponse.toString());

                        intent.putExtra("usuario", userResponse);
                        LocalBroadcastManager.getInstance(CadastroService.this).sendBroadcast(intent);
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Log.d("Falhou", "Deu falha!");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
