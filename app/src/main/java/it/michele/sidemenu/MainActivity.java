package it.michele.sidemenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import it.michele.sidemenu.fragments.FirstFragment;
import it.michele.sidemenu.fragments.SecondFragment;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this); /*Questo metodo ci permette
        di impostare questa classe (che implementa NavigationView.OnNavigationItemSelectedListener)
         come listener per eseguire il metodo onNavigationItemSelected quando viene selezionato
         un oggetto del menu*/

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState(); /*Questo oggetto sincronizza tutti i cambiamenti
        effettuati all'actionBarDrawerToggle sull'activity (quella che abbiamo passatto come
        parametro al costruttore). Nel nostro caso l'activity è "this" dato che ci troviamo
        direttamente nella classe della Activity (che estende AppCompactActivity)*/

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container_fragment, new FirstFragment())
                .commit();
    }

    /*
    Questo metodo viene eseguito quando clicchiamo su un item del menu
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(item.getItemId() == R.id.home){
            fragmentManager.beginTransaction().replace(R.id.container_fragment, new FirstFragment())
                    .commit();
        } else if(item.getItemId() == R.id.another){
            fragmentManager.beginTransaction().replace(R.id.container_fragment, new SecondFragment())
                    .commit();
        }
        return true;
    }
}