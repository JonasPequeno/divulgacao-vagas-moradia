package moradiauniversitaria.br.com.moradiauniversitaria.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import moradiauniversitaria.br.com.moradiauniversitaria.R;
import moradiauniversitaria.br.com.moradiauniversitaria.model.Imovel;

public class InfoImovel extends Fragment {

    private View view;
    private TextView sobreImovel;
    private TextView sobreVaga;
    private TextView valorVaga;
    private TextView estado;
    private TextView cidade;
    private TextView contato;
    private TextView rua;
    private TextView numero;
    private ImageView imagem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmento_info_imovel, container, false);

        //ViewPager viewPager = view.findViewById(R.id.fotosSlider);
        //SliderAdapter adapter = new SliderAdapter(view.getContext());
        //viewPager.setAdapter(adapter);
        preecheValores();
        return view;
    };

    private void preecheValores() {
        Imovel imovel = getActivity().getIntent().getParcelableExtra("imovel");
        System.out.println(imovel.toString());
        Log.i("Imovel recebio", imovel.toString());

        sobreImovel = (TextView) view.findViewById(R.id.labelSobreImovel);
        imagem = (ImageView) view.findViewById(R.id.imageDetalhe);
        sobreVaga = (TextView) view.findViewById(R.id.labelSobreVaga);
        valorVaga = (TextView) view.findViewById(R.id.labelValorVaga);
        estado = (TextView) view.findViewById(R.id.labelEstato);
        cidade = (TextView) view.findViewById(R.id.labelCidade);
        numero = (TextView) view.findViewById(R.id.labelNumero);
        contato = (TextView) view.findViewById(R.id.labelContato);
        rua = (TextView) view.findViewById(R.id.labelRua);

        sobreImovel.setText(imovel.getSobreImovel());
        sobreVaga.setText(imovel.getSobreVaga());

        imagem.getLayoutParams().width = 600;
        imagem.getLayoutParams().height = 600;

        Picasso.get()
                .load(imovel.getFoto())
                .placeholder(R.mipmap.ic_launcher) // optional
                .error(R.mipmap.ic_launcher) //if error
                .into(imagem);

        estado.setText(imovel.getEndereco().getEstado());
        cidade.setText(imovel.getEndereco().getCidade());
        numero.setText(imovel.getEndereco().getNumero());
        contato.setText(imovel.getContato());
        rua.setText(imovel.getEndereco().getRua());
        valorVaga.setText("R$ " + String.valueOf(imovel.getValorVaga()));
    }

}
