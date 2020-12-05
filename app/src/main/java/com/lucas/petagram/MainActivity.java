package com.lucas.petagram;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.lucas.petagram.adapter.PageAdapter;

import java.util.ArrayList;

import com.lucas.petagram.db.BaseDatos;
import com.lucas.petagram.db.ConstructorMascotas;
import com.lucas.petagram.fragment.PerfilFragment;
import com.lucas.petagram.fragment.ReciclerViewFragment;
import com.lucas.petagram.pojo.Mascota;

public class MainActivity extends AppCompatActivity{

    private android.support.v7.widget.Toolbar miActionBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Mascota> arrayListMascotasFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miActionBar = (android.support.v7.widget.Toolbar) findViewById(R.id.miActionBar);


        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        setUpViewPager();

        if(miActionBar!=null)
        {
            setSupportActionBar(miActionBar);
        }

    }

    private ArrayList<android.support.v4.app.Fragment> agregarFragments(){

        ArrayList<android.support.v4.app.Fragment> fragments = new ArrayList<>();

        fragments.add(new ReciclerViewFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_dogs);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_footprint);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    // seleccion de elemento de menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.mContacto:
                Intent intentContacto = new Intent(this,Contacto.class);
                startActivity(intentContacto);
                break;

            case R.id.mAcercaDe:
                Intent intentAcerca = new Intent(this,AcercaDe.class);
                startActivity(intentAcerca);
                break;

            case R.id.mFavorites:
                Intent intentFav = new Intent(this, Favorites.class);
                startActivity(intentFav);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
