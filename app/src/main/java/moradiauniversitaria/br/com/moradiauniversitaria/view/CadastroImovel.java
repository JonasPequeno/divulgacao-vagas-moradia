package moradiauniversitaria.br.com.moradiauniversitaria.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import static android.app.Activity.RESULT_OK;

public class CadastroImovel extends Fragment {

    private View view;
    public static final  int IMAGEM_INTERNA = 12;

    public void CadastroImovel () {};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmento_cadastro_imovel, container, false);

        Button btnAdd = (Button)view.findViewById(R.id.btnAdd);
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
    };

    public void pegarImagem () {
        //criando uma intenção, pra alguma aplicação
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //especificando qual tipo será recebido
        intent.setType("image/*");
        //veficicar se alguma aplicação respndeu
        startActivityForResult(intent, IMAGEM_INTERNA);
    };

    @Override
    public void onActivityResult(int requestCode, int codigoResultado, Intent intent) {
        //Se houver aplicações com resposta
        if (requestCode == IMAGEM_INTERNA){
            //Se o processamento foi OK
            if (codigoResultado == RESULT_OK){

                Uri imageUri = intent.getData();
                Bitmap bitmap;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),imageUri);
                    ImageView imgProduto = (ImageView)view.findViewById(R.id.imagem);
                    imgProduto.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void criaNovaVaga () {
        Imovel imovel = new Imovel();


        EditText descricaoImovel = (EditText) view.findViewById(R.id.campoDescricaoImovel);
        EditText descricaoVaga = (EditText) view.findViewById(R.id.campoDescricaoVaga);
        EditText valorVaga = (EditText) view.findViewById(R.id.campoValor);

        Log.d("CHEGOUA QUINESSA BHOSTA", "");

        imovel.setSobreImovel(String.valueOf(descricaoImovel.getText()));
        imovel.setSobreVaga(String.valueOf(descricaoVaga.getText()));
        imovel.setValorVaga(Float.parseFloat(String.valueOf(valorVaga.getText())));

        Log.d("Cadastro de Imovel", imovel.toString());
    }

    /*public void criarSlider () {
        ViewPager viewPager = view.findViewById(R.id.fotosSlider);
        SliderAdapter adapter = new SliderAdapter(view.getContext());
        viewPager.setAdapter(adapter);
    }*/

}
