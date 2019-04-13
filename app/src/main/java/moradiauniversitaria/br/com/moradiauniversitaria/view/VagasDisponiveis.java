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
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import moradiauniversitaria.br.com.moradiauniversitaria.model.Imovel;
import moradiauniversitaria.br.com.moradiauniversitaria.model.ImovelAdapter;
import moradiauniversitaria.br.com.moradiauniversitaria.service.ImovelService;
import moradiauniversitaria.br.com.moradiauniversitaria.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VagasDisponiveis extends Fragment {

    private View view;
    private List<Imovel> imoveis = new ArrayList<>();

    private RecyclerView listaDeImoveis;
    private ImovelAdapter adapter;



    public void VagasDisponiveis() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmento_vagas_disponiveis, container, false);
        Intent intent = new Intent(view.getContext(), MyService.class);
        listaDeImoveis = (RecyclerView)  view.findViewById(R.id.listaVagas);
        listaDeImoveis.setHasFixedSize(true);
        listaDeImoveis.setLayoutManager(new LinearLayoutManager(view.getContext()));
        getImovel();
        return view;
    }

    ;

    public void getImovel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ImovelService.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ImovelService service = retrofit.create(ImovelService.class);
        Call<List<Imovel>> callService = service.getImoveis();

        callService.enqueue(new Callback<List<Imovel>>() {
            @Override
            public void onResponse(Call<List<Imovel>> call, Response<List<Imovel>> response) {
                if (response.isSuccessful()) {
                    imoveis = response.body();
                    Log.d("puuu", "onResponse: "+imoveis.toString());
                    mostraDadosLista();
                }
            }

            @Override
            public void onFailure(Call<List<Imovel>> call, Throwable t) {
                Log.i("ERRO :", t.getMessage());

            }
        });
    }

    private void mostraDadosLista() {

        adapter = new ImovelAdapter(imoveis, onClickPrato());

        listaDeImoveis.setAdapter(adapter);

        /*
        * ListView vagas = (ListView) view.findViewById(R.id.listaVagas);
        ArrayAdapter<Imovel> adapter = new ArrayAdapter<Imovel>(view.getContext(),
                android.R.layout.simple_list_item_1, imoveis);
        vagas.setAdapter(adapter);
        * */
    }

    private ImovelAdapter.CardOnClickListener onClickPrato(){
        return new ImovelAdapter.CardOnClickListener() {
            @Override
            public void onClickCard(View view, int idx) {
                Imovel item = imoveis.get(idx);//.findViewHolderForItemId(idx);
                Log.i("Entoru ","Clienque");

                getActivity().getIntent().putExtra("imovel", item);
                Fragment fr = new InfoImovel();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();

                fragmentTransaction.replace(R.id.fragment, fr);
                fragmentTransaction.commit();

                //Toast.makeText(VagasDisponiveis.this.getContext(), "Clicou em"+ item.toString(), Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getBaseContext(), PratoPedidoActivity.class);
                //startActivity(intent);
            }
        };
    }
}
