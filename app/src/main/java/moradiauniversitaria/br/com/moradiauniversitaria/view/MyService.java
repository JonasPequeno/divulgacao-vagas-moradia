package moradiauniversitaria.br.com.moradiauniversitaria.view;

import android.app.IntentService;
import android.content.Intent;
import android.os.Parcelable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;

import java.util.List;

import moradiauniversitaria.br.com.moradiauniversitaria.model.Imovel;
import moradiauniversitaria.br.com.moradiauniversitaria.service.ImovelService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyService extends IntentService {

    private   List<Imovel> imoveis = null;
    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(final Intent intent) {

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
                }
            }
            @Override
            public void onFailure(Call<List<Imovel>> call, Throwable t) {
                Log.i("Erro nessa bosta!", t.getMessage());

            }
        });

        intent.setAction(MainActivity.FILTER_ACTION_KEY);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent.putExtra("broadcastMessage", (Parcelable) imoveis));
    }

}
