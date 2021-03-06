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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jake.fido.Instance.APIFido;
import com.example.jake.fido.Instance.FidoData;
import com.example.jake.fido.Retrofit.ChungChiRetrofit;
import com.example.jake.fido.Retrofit.Data;
import com.example.jake.fido.Retrofit.ObjectRetrofit.Doctor;
import com.example.jake.fido.Retrofit.ObjectRetrofit.Doctors;
import com.example.jake.fido.Utils.OnSearchClickListener;
import com.example.jake.fido.View.Fragment.ChungChiFragment;
import com.example.jake.fido.View.Fragment.DoctorFragment;
import com.example.jake.fido.View.Fragment.MapsFragment;
import com.example.jake.fido.View.Fragment.MessagesFragment;
import com.example.jake.fido.View.Fragment.QuestionFragment;
import com.example.jake.fido.View.Fragment.UpdateFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.miguelcatalan.materialsearchview.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
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
    public static CircleImageView cv_image_user;
    TextView tv_name;
    TextView tv_type_user;
    static OnSearchClickListener onSearchClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        getDataUser();

    }

    private void getDataUser() {
        APIFido.getInstance().getSoService().getChungChi(FidoData.getInstance().getLoginRetrofit().getData().getId().toString()).enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                FidoData.getInstance().setDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {

            }
        });

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
        searchView.setSuggestions(listSuggestions);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if(FidoData.getInstance().getLoginRetrofit().getUsableType().equals("App\\Patient")){
            navigationView.inflateMenu(R.menu.drawer_patients);
        }
        else {
            navigationView.inflateMenu(R.menu.activity_main_drawer);
        }
        View header = navigationView.getHeaderView(0);
        cv_image_user = (CircleImageView)header.findViewById(R.id.profile_image);
        tv_name = (TextView)header.findViewById(R.id.tv_name);
        tv_type_user = (TextView)header.findViewById(R.id.tv_type_user);
        if(FidoData.getInstance().getLoginRetrofit().getData().getAvatar()!=null && !FidoData.getInstance().getLoginRetrofit().getData().getAvatar().equals("")){
            Glide.with(getBaseContext()).load(FidoData.getInstance().getLoginRetrofit().getData().getAvatar()).into(cv_image_user);

        }
        if(FidoData.getInstance().getLoginRetrofit().getData().getName()!=null){
            tv_name.setText(FidoData.getInstance().getLoginRetrofit().getData().getName());
        }
        if(FidoData.getInstance().getLoginRetrofit().getUsableType().equals("App\\Doctor")){
            tv_type_user.setText("Bác sĩ FIDO");
        }

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
                FidoData.getInstance().setSearch("");
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
                break;
            case R.id.chungchi:
                fragmentClass = ChungChiFragment.class;
                break;
            case R.id.nav_exit:
                fragmentClass = DoctorFragment.class;
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                MainActivity.this.startActivity(intent);
                finish();
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!"".equals(query))
            if (onSearchClickListener != null) {
                onSearchClickListener.onSearchSubmit(query, "", "");
            }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!"".equals(newText))
            if (onSearchClickListener != null) {
                onSearchClickListener.onSearchSubmit(newText, "", "");

            }
        return true;
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
