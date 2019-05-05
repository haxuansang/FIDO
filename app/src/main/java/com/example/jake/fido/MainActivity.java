package com.example.jake.fido;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Filter;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.jake.fido.Instance.APIFido;
import com.example.jake.fido.Instance.FidoData;
import com.example.jake.fido.Retrofit.ObjectRetrofit.Doctor;
import com.example.jake.fido.Retrofit.ObjectRetrofit.Doctors;
import com.example.jake.fido.Utils.OnSearchClickListener;
import com.example.jake.fido.View.Fragment.DoctorFragment;
import com.example.jake.fido.View.Fragment.MapsFragment;
import com.example.jake.fido.View.Fragment.MessagesFragment;
import com.example.jake.fido.View.Fragment.QuestionFragment;
import com.example.jake.fido.View.Fragment.UpdateFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.miguelcatalan.materialsearchview.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MaterialSearchView.OnQueryTextListener {
    FragmentManager fragmentManager;
    DrawerLayout drawer;
    MaterialSearchView searchView;
    SearchAdapter searchAdapter;
    String[] listSuggestions = new String[]{};
    static OnSearchClickListener onSearchClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();

    }

    public static void setOnSearchClickListener(OnSearchClickListener onSearchClickListeners){
        onSearchClickListener = onSearchClickListeners;
    }
    private void setupView() {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, DoctorFragment.newInstance()).commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setCursorDrawable(R.drawable.color_cursor_white);
        searchView.setVoiceSearch(true);
        searchView.setEllipsize(true);



       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        searchView.setSuggestions(listSuggestions);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_doctors);
        navigationView.setNavigationItemSelectedListener(this);
        searchView.setOnQueryTextListener(this);
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                FidoData.getInstance().setTypeShow(2);
            }

            @Override
            public void onSearchViewClosed() {
                FidoData.getInstance().setTypeShow(1);
                onSearchClickListener.onCloseSearch();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        Class fragmentClass = null;
        int id = item.getItemId();


        switch (id) {
            // Handle the camera action
            case R.id.nav_doctors:
                fragmentClass = DoctorFragment.class;
                break;
            case R.id.nav_maps:
                fragmentClass = MapsFragment.class;
                break;
            case R.id.nav_messages:
                fragmentClass = MessagesFragment.class;
                break;
            case R.id.nav_questions:
                fragmentClass = QuestionFragment.class;
                break;
            case R.id.nav_update:
                fragmentClass = UpdateFragment.class;



     /*   } else if (id == R.id.na) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
*/
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        /*// Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());*/

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if(!"".equals(query))
           getSuggestions(query);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(!"".equals(newText))
            getSuggestions(newText);
        return true;
    }

    private void getSuggestions(String newText) {
        final RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", newText)
                .build();

        APIFido.getInstance().getSoService().searchDoctors(requestBody).enqueue(new Callback<Doctors>() {
            @Override
            public void onResponse(Call<Doctors> call, Response<Doctors> response) {
                if(onSearchClickListener!=null) {
                    onSearchClickListener.onSearchSubmit(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<Doctors> call, Throwable t) {

            }
        });

    }


    private void updateSuggestionsAdapter(List<Doctor> data) {
        List<String> listDoctor = new ArrayList<>();
        for (Doctor doctor : data
        ) {
            listDoctor.add(doctor.getName());
        }
        listSuggestions = listDoctor.toArray(new String[listDoctor.size()]);
        searchView.setSuggestions(listSuggestions);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);

                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
