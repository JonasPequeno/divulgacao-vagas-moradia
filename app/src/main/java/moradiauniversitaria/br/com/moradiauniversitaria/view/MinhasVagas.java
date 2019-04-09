package moradiauniversitaria.br.com.moradiauniversitaria.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import moradiauniversitaria.br.com.moradiauniversitaria.R;
import moradiauniversitaria.br.com.moradiauniversitaria.aplication.MoradiaUniversitaria;
import moradiauniversitaria.br.com.moradiauniversitaria.model.Imovel;
import moradiauniversitaria.br.com.moradiauniversitaria.model.ImovelAdapter;
import moradiauniversitaria.br.com.moradiauniversitaria.model.ImovelAdapterEditar;
import moradiauniversitaria.br.com.moradiauniversitaria.service.CadastroService;
import moradiauniversitaria.br.com.moradiauniversitaria.service.ImovelService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MinhasVagas extends Fragment {


    private View view;
    private List<Imovel> imoveis = new ArrayList<>();

    private RecyclerView listaDeImoveis;
    private ImovelAdapterEditar adapter;
    private  AlertDialog.Builder alertDialog;



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
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Imovel>> call, Response<List<Imovel>> response) {
                if (response.isSuccessful()) {
                    imoveis = response.body();
                    imoveis = getListaPorEmail(imoveis);
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

        adapter = new ImovelAdapterEditar(imoveis, onClickPrato());

        listaDeImoveis.setAdapter(adapter);
    }

    private ImovelAdapterEditar.CardOnClickListener onClickPrato(){
        return new ImovelAdapterEditar.CardOnClickListener() {
            @Override
            public void onClickCard(View view, int idx) {
                Imovel item = imoveis.get(idx);//.findViewHolderForItemId(idx);

                getActivity().getIntent().putExtra("imovel", item);
                Fragment fr = new EditarImovelActivity();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();

                fragmentTransaction.replace(R.id.fragment, fr);
                fragmentTransaction.commit();
            }
            @Override
            public void remover(View view, int posicao) {
                Imovel item = imoveis.get(posicao);
                removerVaga(item);
            }
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Imovel> getListaPorEmail (final List<Imovel> imoveis) {
        ArrayList<Imovel> result = new ArrayList<>();
       for(Imovel i : imoveis) {
            if(i.getEmailUser().equals(MoradiaUniversitaria.usuarioLogado.getEmail()))
                result.add(i);
       }
       return result;
    }


    public void removerVaga( Imovel imovel ) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ImovelService.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ImovelService service = retrofit.create(ImovelService.class);
        Call<Imovel> callService = service.deletaImovel(imovel.get_id());

        try {

            callService.enqueue(new Callback<Imovel>() {
                @Override
                public void onResponse(Call<Imovel> call, Response<Imovel> response) {

                    if( response.isSuccessful()) {
                        alertDialog = new AlertDialog.Builder(view.getContext());
                        alertDialog.setTitle("SUCESSO!");
                        alertDialog.setIcon(R.mipmap.ic_alert_round);
                        alertDialog.setMessage("Sua divulgação foi removida com sucesso!");
                        AlertDialog alerta = alertDialog.create();
                        alerta.show();
                        //Intent intent = new Intent(view.getContext(),VagasDisponiveis.class);
                        //startActivity(intent);

                    }
                }

                @Override
                public void onFailure(Call<Imovel> call, Throwable t) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());

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
}
