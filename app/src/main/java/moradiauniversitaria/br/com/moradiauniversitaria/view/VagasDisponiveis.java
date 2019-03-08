package moradiauniversitaria.br.com.moradiauniversitaria.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import moradiauniversitaria.br.com.moradiauniversitaria.model.Imovel;
import moradiauniversitaria.br.com.moradiauniversitaria.service.ImovelService;
import moradiauniversitaria.br.com.moradiauniversitaria.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VagasDisponiveis extends Fragment {

    public static final String FILTER_ACTION_KEY = "any_key";

    private View view;
    private List<Imovel> imoveis = new ArrayList<>();


    public void VagasDisponiveis () {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmento_vagas_disponiveis, container, false);

        Intent intent = new Intent(view.getContext(),MyService.class);

        getImovel();
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

    public void getImovel () {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ImovelService.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ImovelService service = retrofit.create(ImovelService.class);
        Call<List<Imovel>> callService = service.getImoveis();

        callService.enqueue(new Callback<List<Imovel>>() {
            @Override
            public void onResponse(Call<List<Imovel>> call, Response<List<Imovel>> response) {
                if(response.isSuccessful()) {
                    imoveis = response.body();
                    mostraDadosLista();
                }
            }
            @Override
            public void onFailure(Call<List<Imovel>> call, Throwable t) {
                Log.i("Erro nessa bosta!", t.getMessage());

            }
        });
    }

    private  void mostraDadosLista () {

        ListView vagas = (ListView) view.findViewById(R.id.listaVagas);
        ArrayAdapter<Imovel> adapter = new ArrayAdapter<Imovel>(view.getContext(),
                android.R.layout.simple_list_item_1, imoveis);
        vagas.setAdapter(adapter);
    }
}
