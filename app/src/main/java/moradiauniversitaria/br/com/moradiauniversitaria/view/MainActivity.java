package moradiauniversitaria.br.com.moradiauniversitaria.view;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import moradiauniversitaria.br.com.moradiauniversitaria.R;
import moradiauniversitaria.br.com.moradiauniversitaria.aplication.MoradiaUniversitaria;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String FILTER_ACTION_KEY = "any_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.fragment, new VagasDisponiveis())
        .addToBackStack(null)
        .commit();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class)); //O efeito ao ser pressionado do botão (no caso abre a activity)
        //finishAffinity(); //Método para matar a activity e não deixa-lá indexada na pilhagem
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cadastro_imovel) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, new CadastroImovelActivity())
                    .addToBackStack(null)
                    .commit();
        } else
        if (id == R.id.nav_minhasVagas) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, new MinhasVagas())
                    .addToBackStack(null)
                    .commit();
        } else
            if (id == R.id.nav_perfil) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, new Perfil())
                    .addToBackStack(null)
                    .commit();

        } else
            if (id == R.id.nav_sobre) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment, new Sobre())
                        .addToBackStack(null)
                        .commit();

        } else
            if (id == R.id.sair) {
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);

            }else

            if( id == R.id.nav_lista_vagas) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, new VagasDisponiveis())
                    .addToBackStack(null)
                    .commit();

        };

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
