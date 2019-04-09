package moradiauniversitaria.br.com.moradiauniversitaria.aplication;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class CriaBanco extends SQLiteOpenHelper {


    public static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "imoveis";
    public static final String ID = "_id";
    public static final String IMAGEM = "imagem";
    public static final String ID_IMOVEL = "id_imovel";
    public static final int VERSAO = 1;

    public CriaBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + IMAGEM + " text,"
                + ID_IMOVEL + " text"
                +")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
