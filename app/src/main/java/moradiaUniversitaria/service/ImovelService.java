package moradiaUniversitaria.service;

import java.util.List;

import moradiaUniversitaria.model.Imovel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ImovelService {

    public static final String URL_BASE = "http://192.168.2.117:3000/api/";

    @GET("imovel")
    Call<List<Imovel>> getImoveis();
}
