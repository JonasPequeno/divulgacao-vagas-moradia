package moradiauniversitaria.br.com.moradiauniversitaria.service;

import java.util.List;

import moradiauniversitaria.br.com.moradiauniversitaria.model.Imovel;
import moradiauniversitaria.br.com.moradiauniversitaria.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ImovelService {

    public static final String URL_BASE = "http://10.3.135.130/api/";

    @GET("imovel/")
    Call<List<Imovel>> getImoveis();

    @POST("imovel/")
    Call<Imovel> criaImovel(@Body Imovel imovel);

    @PUT("imovel/{id}")
    Call<Imovel> editaImovel(@Path("id") String id, @Body Imovel imovel);

    @DELETE("imovel/{id}")
    Call<Imovel> deletaImovel(@Path("id") String id);
}
