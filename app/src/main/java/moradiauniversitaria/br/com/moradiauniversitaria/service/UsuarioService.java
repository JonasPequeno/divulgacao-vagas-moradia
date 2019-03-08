package moradiauniversitaria.br.com.moradiauniversitaria.service;

import java.util.List;

import moradiauniversitaria.br.com.moradiauniversitaria.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioService {

    public static final String URL_BASE = "http://192.168.0.121:3000/api/usuario/";


    @POST("autentica/")
    Call<Usuario> autentica(@Body Usuario usuario);

}
