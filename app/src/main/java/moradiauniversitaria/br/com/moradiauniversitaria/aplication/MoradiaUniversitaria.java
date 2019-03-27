package moradiauniversitaria.br.com.moradiauniversitaria.aplication;

import android.app.Application;

import moradiauniversitaria.br.com.moradiauniversitaria.model.Usuario;

public class MoradiaUniversitaria extends Application {

    public static Usuario usuarioLogado;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
