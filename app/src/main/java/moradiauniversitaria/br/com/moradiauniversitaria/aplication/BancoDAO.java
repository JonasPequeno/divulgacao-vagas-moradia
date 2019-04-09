package moradiauniversitaria.br.com.moradiauniversitaria.aplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoDAO {

    private SQLiteDatabase db;
    private Banco2 banco;

    public BancoDAO(Context context) {

        Banco2 auxBanco = new Banco2(context);
        db = auxBanco.getWritableDatabase();
    }

    public String insereDado(byte[] imagem, String imovel) {

        ContentValues valores ;
        long resultado;

        valores = new ContentValues();

        valores.put("imagem", imagem);
        valores.put("imovel", imovel);

        resultado = db.insert("imoveis", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }


}
