package moradiauniversitaria.br.com.moradiauniversitaria.view;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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

import static android.app.Activity.RESULT_OK;
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
    private ImageView fotoPerfil;

    private FloatingActionButton btnFotoPerfil;

    private String mCurrentPhotoPath;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmento_perfil, container, false);
        preecheDados(view);

        Button btn = view.findViewById(R.id.btnSalvar);
        FloatingActionButton btnFotoPerfil = view.findViewById(R.id.btnFotoPerfil);

        btnFotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPermissions();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvaEdicao(view);
            }
        });

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

        fotoPerfil = view.findViewById(R.id.imagemPerfil);
        fotoPerfil.getLayoutParams().width = 400;
        fotoPerfil.getLayoutParams().height = 400;

        Picasso.get()
                .load(usuarioLogado.getFoto())
                .placeholder(R.mipmap.ic_launcher) // optional
                .error(R.mipmap.ic_launcher)
                .resize(256, 256).centerCrop()
                .into(fotoPerfil);

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
        usuario.setFoto(mCurrentPhotoPath);
        usuario.setEndereco(endereco);
        return usuario;
    }

    public void salvaEdicao(final View view) {

        Usuario usuario = getDadosView();
        usuario.setId(usuarioLogado.getId());
        Log.d("Usuario antes", usuario.toString());

        Retrofit retrofit = new Retrofit.Builder()
                       .baseUrl(UsuarioService.URL_BASE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                UsuarioService service = retrofit.create(UsuarioService.class);

                Call<Usuario> callService = service.editaUsuario(usuarioLogado.getId(), usuario);

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

                            Picasso.get()
                                    .load(usuarioLogado.getFoto())
                                    .placeholder(R.mipmap.ic_launcher) // optional
                                    .error(R.mipmap.ic_launcher)
                                    .resize(256, 256).centerCrop()
                                    .into(fotoPerfil);

                            preecheDados(view);
                        }
                        else {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());


                            alertDialog.setTitle("Erro ao salvar");
                            alertDialog.setIcon(R.mipmap.ic_alert_round);
                            alertDialog.setMessage("Suas modificaçoes foram salvas com sucesso!");
                            alertDialog.setCancelable(true);
                            AlertDialog alerta = alertDialog.create();
                            alerta.show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Log.d("Usuario editado", t.getMessage());
                    }
                });
            }
    private void getPermissions() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        else
            dispatchTakePictureIntent();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakePictureIntent();
                } else {
                    Toast.makeText(getContext(), "Não vai funcionar!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            File photoFile = null;
            try {
                File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                photoFile = File.createTempFile("PHOTOAPP", ".jpg", storageDir);
                mCurrentPhotoPath = "file:" + photoFile.getAbsolutePath();
            }
            catch(IOException ex){
                Toast.makeText(getContext().getApplicationContext(), "Erro ao tirar a foto", Toast.LENGTH_SHORT).show();
            }

            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                ImageView imagem = (ImageView)view.findViewById(R.id.imagemPerfil);

                imagem.getLayoutParams().width = 400;
                imagem.getLayoutParams().height = 400;

                usuarioLogado.setFoto(mCurrentPhotoPath);

                Bitmap bm1 = BitmapFactory
                        .decodeStream(this.getActivity().getContentResolver().openInputStream(Uri.parse(mCurrentPhotoPath)));
                imagem.setImageBitmap(bm1);

                preecheDados(view);

            }catch(FileNotFoundException fnex){
                Toast.makeText(getContext().getApplicationContext(), "Foto não encontrada!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
