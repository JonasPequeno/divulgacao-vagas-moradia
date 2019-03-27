package moradiauniversitaria.br.com.moradiauniversitaria.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;


import moradiauniversitaria.br.com.moradiauniversitaria.model.Imovel;
import moradiauniversitaria.br.com.moradiauniversitaria.R;
import moradiauniversitaria.br.com.moradiauniversitaria.service.ImovelService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;

public class CadastroImovelActivity extends Fragment {
    private View view;

    public static final int IMAGEM_INTERNA = 12;
    //private Imovel imovel ;
    private ImovelService service;

    public void CadastroImovel() {
     //   imovel = new Imovel();
    }


    ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmento_cadastro_imovel, container, false);

        Button btnAdd = (Button) view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pegarImagem();
            }
        });
        Button btnSalvar = (Button) view.findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criaNovaVaga();
            }
        });
        return view;
    }

    ;

    public void pegarImagem() {
        //criando uma intenção, pra alguma aplicação
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //especificando qual tipo será recebido
        intent.setType("image/*");
        //veficicar se alguma aplicação respndeu
        startActivityForResult(intent, IMAGEM_INTERNA);
    }

    ;

    @Override
    public void onActivityResult(int requestCode, int codigoResultado, Intent intent) {
        //Se houver aplicações com resposta
        if (requestCode == IMAGEM_INTERNA) {
            //Se o processamento foi OK
            if (codigoResultado == RESULT_OK) {

                Uri imageUri = intent.getData();
                Bitmap bitmap;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri);
                    ImageView imgProduto = (ImageView) view.findViewById(R.id.imagem);
                    imgProduto.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void criaNovaVaga() {

        EditText descricaoImovel =  view.findViewById(R.id.campoDescricaoImovel);
        EditText descricaoVaga =  view.findViewById(R.id.campoDescricaoVaga);
        EditText valorVaga = view.findViewById(R.id.campoValor);

        Imovel imovel = new Imovel();

        imovel.setSobreImovel(descricaoImovel.getText().toString());
        imovel.setSobreVaga(descricaoVaga.getText().toString());
        imovel.setValorVaga(Float.parseFloat(valorVaga.getText().toString()));

        Log.d("Imovel", imovel.toString());

        salvaImovel(imovel);

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
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());

                        alertDialog.setTitle("SUCESSO!");
                        alertDialog.setIcon(R.mipmap.ic_alert_round);
                        alertDialog.setMessage("Sua divulgação foi criada com sucesso!");
                        AlertDialog alerta = alertDialog.create();
                        alerta.show();

                        Intent intent = new Intent(view.getContext(),VagasDisponiveis.class);
                        startActivity(intent);

                    }
                }

                @Override
                public void onFailure(Call<Imovel> call, Throwable t) {
                    Log.d("Falha", "");
                }
            });
        } catch (Exception ex) {
            Log.d("Exasdas", "dasdasd");
        }
    }

    /*public void criarSlider () {
        ViewPager viewPager = view.findViewById(R.id.fotosSlider);
        SliderAdapter adapter = new SliderAdapter(view.getContext());
        viewPager.setAdapter(adapter);
    }*/


}
