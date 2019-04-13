package moradiauniversitaria.br.com.moradiauniversitaria.service;

import java.util.List;

import moradiauniversitaria.br.com.moradiauniversitaria.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioService {

    public static final String URL_BASE = "http://192.168.2.114:3000/api/";


    @POST("usuario/autentica/")
    Call<Usuario> autentica(@Body Usuario usuario);

    @POST("usuario/")
    Call<Usuario> criaUsuario(@Body Usuario usuario);

    @PUT("usuario/{id}")
    Call<Usuario> editaUsuario(@Path("id") String id,@Body Usuario usuario);

}
