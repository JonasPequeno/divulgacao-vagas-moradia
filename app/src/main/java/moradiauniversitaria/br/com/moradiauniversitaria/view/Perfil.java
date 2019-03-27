package moradiauniversitaria.br.com.moradiauniversitaria.view;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import moradiauniversitaria.br.com.moradiauniversitaria.R;
import moradiauniversitaria.br.com.moradiauniversitaria.aplication.MoradiaUniversitaria;
import moradiauniversitaria.br.com.moradiauniversitaria.model.Endereco;
import moradiauniversitaria.br.com.moradiauniversitaria.model.Usuario;
import moradiauniversitaria.br.com.moradiauniversitaria.service.UsuarioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static moradiauniversitaria.br.com.moradiauniversitaria.aplication.MoradiaUniversitaria.usuarioLogado;

public class Perfil extends Fragment {

    private View view;
    private EditText campoEmail;
    private EditText campoNome;
    private EditText campoInstituicao;
    private EditText campoCidade;
    private EditText campoRua;
    private EditText campoNumero;
    private EditText campoEstado;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmento_perfil, container, false);
        preecheDados(view);
        salvaEdicao(view);
        return view;
    }

    ;

    @Override
    public void onResume() {

        super.onResume();
    }

    public void preecheDados(View view) {
        Usuario usuario = usuarioLogado;
        Log.d("Perfil", usuario.toString());
        campoNome = view.findViewById(R.id.textNome);
        campoNome.setText(usuario.getNome());
        campoEmail = view.findViewById(R.id.textEmail);
        campoEmail.setText(usuario.getEmail());
        campoInstituicao = view.findViewById(R.id.textInstituicao);
        campoInstituicao.setText(usuario.getInstituicao());
        campoNumero = view.findViewById(R.id.campoNumero);
        campoNumero.setText(usuario.getEndereco().getNumero());
        campoCidade = view.findViewById(R.id.campoCidade);
        campoCidade.setText(usuario.getEndereco().getCidade());
        campoRua = view.findViewById(R.id.campoRua);
        campoRua.setText(usuario.getEndereco().getRua());
        campoEstado = view.findViewById(R.id.campoEstado);
        campoEstado.setText(usuario.getEndereco().getEstado());
    }

    public Usuario getDadosView() {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioLogado.getId());
        Endereco endereco = new Endereco();
        campoNome = view.findViewById(R.id.textNome);
        usuario.setNome(campoNome.getText().toString());
        campoEmail = view.findViewById(R.id.textEmail);
        usuario.setEmail(campoEmail.getText().toString());
        campoInstituicao = view.findViewById(R.id.textInstituicao);
        usuario.setInstituicao(campoInstituicao.getText().toString());
        campoNumero = view.findViewById(R.id.campoNumero);
        endereco.setNumero(campoNumero.getText().toString());
        campoCidade = view.findViewById(R.id.campoCidade);
        endereco.setCidade(campoCidade.getText().toString());
        campoRua = view.findViewById(R.id.campoRua);
        endereco.setRua(campoRua.getText().toString());
        campoEstado = view.findViewById(R.id.campoEstado);
        endereco.setEstado(campoEstado.getText().toString());
        usuario.setEndereco(endereco);
        return usuario;
    }

    public void salvaEdicao(final View view) {

        Button btn = view.findViewById(R.id.btnSalvar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = getDadosView();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(UsuarioService.URL_BASE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                UsuarioService service = retrofit.create(UsuarioService.class);

                Call<Usuario> callService = service.editaUsuario(usuario.getId(), usuario);

                callService.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if (response.isSuccessful()) {
                            Log.d("Usuario editado", response.body().toString());
                            usuarioLogado = response.body();
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());

                            alertDialog.setTitle("Modificacoes feitas");
                            alertDialog.setIcon(R.mipmap.ic_alert_round);
                            alertDialog.setMessage("Suas modificaçoes foram salvas com sucesso!");
                            alertDialog.setCancelable(true);
                            AlertDialog alerta = alertDialog.create();
                            alerta.show();
                            preecheDados(view);
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Log.d("Usuario editado", t.getMessage());
                    }
                });
            }
        });

    }
}
