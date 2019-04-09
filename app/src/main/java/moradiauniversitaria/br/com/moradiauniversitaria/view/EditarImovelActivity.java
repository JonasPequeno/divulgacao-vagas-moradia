package moradiauniversitaria.br.com.moradiauniversitaria.view;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.lang.UCharacter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


import moradiauniversitaria.br.com.moradiauniversitaria.aplication.MoradiaUniversitaria;
import moradiauniversitaria.br.com.moradiauniversitaria.model.Endereco;
import moradiauniversitaria.br.com.moradiauniversitaria.model.Imovel;
import moradiauniversitaria.br.com.moradiauniversitaria.R;
import moradiauniversitaria.br.com.moradiauniversitaria.service.ImovelService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;

public class EditarImovelActivity extends Fragment {
    private View view;

    public static final int IMAGEM_INTERNA = 12;
    //private Imovel imovel ;
    private ImovelService service;

    private AlertDialog.Builder alertDialog;

    private Imovel imoveleditar;



    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    static String mCurrentPhotoPath;

    public void EditarImovelActivity() {
        //   imovel = new Imovel();
    }


    ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmento_editar_imovel, container, false);

        Button btnAdd = (Button) view.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPermissions();
            }
        });
        Imovel imovel = getActivity().getIntent().getParcelableExtra("imovel");
        setaValores(imovel);

        Button btnSalvar = (Button) view.findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Imovel retornado = criaNovaVaga();

                Log.i("Cria Vaga",retornado.toString());

                retornado.set_id(imovel.get_id());
                retornado.setFoto(mCurrentPhotoPath);

                editarImovel(retornado);
            }
        });
        return view;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void pegarImagem() {
        //criando uma intenção, pra alguma aplicação
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //especificando qual tipo será recebido
        intent.setType("image/*");
        //veficicar se alguma aplicação respndeu
        startActivityForResult(intent, IMAGEM_INTERNA);
    };

    public Imovel criaNovaVaga() {

        EditText descricaoImovel =  view.findViewById(R.id.campoDescricaoImovel);
        EditText descricaoVaga =  view.findViewById(R.id.campoDescricaoVaga);
        EditText valorVaga = view.findViewById(R.id.campoValor);
        EditText contato = view.findViewById(R.id.campoContato);
        EditText cidade = view.findViewById(R.id.campoCidade);
        EditText rua = view.findViewById(R.id.campoRua);
        EditText numero = view.findViewById(R.id.campoNumero);
        EditText estado = view.findViewById(R.id.campoEstado);

        Endereco endereco = new Endereco();

        endereco.setRua(rua.getText().toString());
        endereco.setCidade(cidade.getText().toString());
        endereco.setNumero(numero.getText().toString());
        endereco.setEstado(estado.getText().toString());

        Imovel imovel = new Imovel();

        imovel.setSobreImovel(descricaoImovel.getText().toString());
        imovel.setSobreVaga(descricaoVaga.getText().toString());
        imovel.setValorVaga( Integer.parseInt(valorVaga.getText().toString()));
        imovel.setEndereco(endereco);
        imovel.setEmailUser(MoradiaUniversitaria.usuarioLogado.getEmail());


        Log.i("Cria Vaga", imovel.toString());

        return imovel;



    }

    private void salvaImovel(Imovel imovel) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ImovelService.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ImovelService.class);

        Call<Imovel> callService = service.criaImovel(imovel);

        try {

            callService.enqueue(new Callback<Imovel>() {
                @Override
                public void onResponse(Call<Imovel> call, Response<Imovel> response) {

                    if( response.isSuccessful()) {
                        Imovel imovelRecebido = response.body();
                        Log.d("Imovel Recebido", imovelRecebido.toString());
                        alertDialog = new AlertDialog.Builder(view.getContext());

                        alertDialog.setTitle("SUCESSO!");
                        alertDialog.setIcon(R.mipmap.ic_alert_round);
                        alertDialog.setMessage("Sua divulgação foi criada com sucesso!");
                        AlertDialog alerta = alertDialog.create();
                        alerta.show();

                        //Intent intent = new Intent(view.getContext(),VagasDisponiveis.class);
                        //startActivity(intent);

                    }
                }

                @Override
                public void onFailure(Call<Imovel> call, Throwable t) {
                    alertDialog = new AlertDialog.Builder(view.getContext());

                    alertDialog.setTitle("ERRO!");
                    alertDialog.setIcon(R.mipmap.ic_alert_round);
                    alertDialog.setMessage("Nao foi possivel fazer a divulgaçao, tente novamente mais tarde!");
                    AlertDialog alerta = alertDialog.create();
                    alerta.show();

                }
            });
        } catch (Exception ex) {
            Log.d("Exasdas", "dasdasd");
        }
    }

    public void setaValores(Imovel imoveleditar) {

        EditText descricaoImovel =  view.findViewById(R.id.campoDescricaoImovel);
        EditText descricaoVaga =  view.findViewById(R.id.campoDescricaoVaga);
        EditText valorVaga = view.findViewById(R.id.campoValor);
        EditText contato = view.findViewById(R.id.campoContato);
        EditText cidade = view.findViewById(R.id.campoCidade);
        EditText rua = view.findViewById(R.id.campoRua);
        EditText numero = view.findViewById(R.id.campoNumero);
        EditText estado = view.findViewById(R.id.campoEstado);

        rua.setText(imoveleditar.getEndereco().getRua());
        cidade.setText(imoveleditar.getEndereco().getCidade());
        numero.setText(imoveleditar.getEndereco().getNumero());
        estado.setText(imoveleditar.getEndereco().getEstado());

        descricaoImovel.setText(imoveleditar.getSobreImovel());
            descricaoVaga.setText(imoveleditar.getSobreVaga());
        valorVaga.setText(String.valueOf(imoveleditar.getValorVaga()));
        imoveleditar.setEmailUser(MoradiaUniversitaria.usuarioLogado.getEmail());

        //editarImovel(imoveleditar);
        Log.d("Imovel", imoveleditar.toString());
    }

    private void editarImovel(Imovel imovel) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ImovelService.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ImovelService.class);

        Call<Imovel> callService = service.editaImovel(imovel.get_id(),imovel);

        try {

            callService.enqueue(new Callback<Imovel>() {
                @Override
                public void onResponse(Call<Imovel> call, Response<Imovel> response) {

                    if( response.isSuccessful()) {
                        Imovel imovelRecebido = response.body();

                        Log.d("Imovel Recebido", imovelRecebido.toString());
                        Toast.makeText(getContext().getApplicationContext(), "Imovel Editado Com sucesso!", Toast.LENGTH_LONG).show();
                        //Intent intent = new Intent(view.getContext(),VagasDisponiveis.class);
                        //startActivity(intent);

                    }
                }

                @Override
                public void onFailure(Call<Imovel> call, Throwable t) {
                    alertDialog = new AlertDialog.Builder(view.getContext());

                    alertDialog.setTitle("ERRO!");
                    alertDialog.setIcon(R.mipmap.ic_alert_round);
                    alertDialog.setMessage("Nao foi possivel fazer a divulgaçao, tente novamente mais tarde!");
                    AlertDialog alerta = alertDialog.create();
                    alerta.show();

                }
            });
        } catch (Exception ex) {
            Log.d("Exasdas", "dasdasd");
        }
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
                ImageView imagem = (ImageView)view.findViewById(R.id.imagem);
                Bitmap bm1 = BitmapFactory.decodeStream(this.getActivity().getContentResolver().openInputStream(Uri.parse(mCurrentPhotoPath)));
                imagem.setImageBitmap(bm1);

            }catch(FileNotFoundException fnex){
                Toast.makeText(getContext().getApplicationContext(), "Foto não encontrada!", Toast.LENGTH_LONG).show();
            }
        }
    }


}