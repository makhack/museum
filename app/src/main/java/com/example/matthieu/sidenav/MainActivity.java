package com.example.matthieu.sidenav;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, PhotosFragment.OnFragmentInteractionListener,GeoFragment.OnFragmentInteractionListener {

    ViewFlipper vf;
    private ArrayList<Theme> themeList;

    private int countPressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TEST DB
        // On créé une instance de BaseDAO qui va gérer la DB et la créé si il faut etc
        BaseDAO sqlInstance = new BaseDAO(getApplicationContext());

        // On ouvre la connexion à la bdd
        SQLiteDatabase db = sqlInstance.open();

        // on créé une instance d'ItemDAO si on veut gérer des items (add/delete/edit/select/selectAll...)
        // (FavoritesDAO pour les favorites, ThemeDAO pour les themes)
        ItemDAO idao = new ItemDAO(getApplicationContext(), db);
        ThemeDAO tdao = new ThemeDAO(getApplicationContext(), db);

        Theme t = new Theme("Tableau", 1, R.drawable.tableau1, "Tout les tableau rien que pour vous");

        // on créé un item
        Item i = new Item(R.drawable.tableau1, "description item", "name item", 114.2, 47.5, 1);
        Item it = new Item(R.drawable.tableau2, "description item", "name item", 114.2, 47.5, 1);
        Item ite = new Item(R.drawable.tableau3, "description item", "name item", 114.2, 47.5, 1);
        Item item = new Item(R.drawable.tableau4, "description item", "name item", 114.2, 47.5, 1);

        // on l'ajoute à la BDD via l'ItemDAO
        idao.add(i);

        // on sélectionne l'item avec l'id = 1 (celui qu'on vient d'ajouter)
        // via la methode select de l'ItemDAO
        Item iFromDb = idao.select(1);

        Toast.makeText(getApplicationContext(),iFromDb.getDescription() + iFromDb.getName() + iFromDb.get_latitude() + iFromDb.get_longitude(),Toast.LENGTH_LONG).show();
        // FIN TEST DB

        countPressed = 0;
        Class fragmentClass;
        fragmentClass = PhotosFragment.class;
        Fragment fragment = null;


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(countPressed == 0){
                Toast.makeText(getApplicationContext(),"Retour pour quitter",Toast.LENGTH_LONG).show();
                countPressed = 1;
                new CountDownTimer(4000, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        countPressed = 0;
                    }
                }.start();
            }else if(countPressed == 1){
                super.onBackPressed();
            }
        }
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
        Fragment fragment = null;

        Class fragmentClass;


        fragmentClass = null;


        if (id == R.id.nav_camera) {
            return false;
        } else if (id == R.id.nav_gallery) {
            fragmentClass = PhotosFragment.class;
        } else if (id == R.id.nav_slideshow) {
            Uri imageUri = Uri.parse("http://museumofindustry.novascotia.ca/sites/default/files/inline/images/le-plan.jpg");

            DownloadManager.Request request = new DownloadManager.Request(imageUri);
            request.setDescription("Some descrition");
            request.setTitle("Some title");
            // in order for this if to run, you must use the android 3.2 to compile your app
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            }
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "name-of-the-file.ext");

            // get download service and enqueue file
            DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            manager.enqueue(request);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return false;
        } else if (id == R.id.nav_manage) {
            fragmentClass = GeoFragment.class;

        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
