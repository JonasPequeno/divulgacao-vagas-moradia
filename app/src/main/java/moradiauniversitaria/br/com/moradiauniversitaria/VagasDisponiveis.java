package moradiauniversitaria.br.com.moradiauniversitaria;

import android.opengl.EGLExt;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.moradiauniversitaria.model.Imovel;

import static android.R.layout.simple_list_item_1;

public class VagasDisponiveis extends Fragment {

    private View view;
    ArrayList<String> listaImoveis = new ArrayList<String>();

    public void VagasDisponiveis () {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmento_vagas_disponiveis, container, false);
        mostraDadosLista();

        return view;
    };

    private ArrayList<String> preencherDados (ArrayList<String> dados) {
        dados.add("Quarto Disponivel");
        dados.add("Procura-se Meninas para Dividir aluguel");
        dados.add("Casa com Dois quarto");
        dados.add("Republica");
        dados.add("Procura-se Colega de quarto");
        return dados;
    }

    private  void mostraDadosLista () {

        ListView vagas = (ListView) view.findViewById(R.id.listaVagas);
        preencherDados(this.listaImoveis);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, this.listaImoveis);
        vagas.setAdapter(adapter);

        vagas.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                getFragmentManager()
                        .beginTransaction().replace(R.id.fragment,  new InfoImovel())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
