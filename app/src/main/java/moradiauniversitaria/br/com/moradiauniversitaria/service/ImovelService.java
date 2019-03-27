package moradiauniversitaria.br.com.moradiauniversitaria.service;

import java.util.List;

import moradiauniversitaria.br.com.moradiauniversitaria.model.Imovel;
import moradiauniversitaria.br.com.moradiauniversitaria.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ImovelService {

    public static final String URL_BASE = "http://192.168.2.111:3000/api/";

    @GET("imovel/")
    Call<List<Imovel>> getImoveis();

    @POST("imovel/")
    Call<Imovel> criaImovel(@Body Imovel imovel);

    @POST("imovel/")
    Call<Imovel> editaImovel(@Body Imovel imovel);

    @POST("imovel/")
    Call<Imovel> deletaImovel(@Body Imovel imovel);
}
