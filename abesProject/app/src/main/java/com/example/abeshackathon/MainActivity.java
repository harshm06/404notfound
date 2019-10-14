package com.example.abeshackathon;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.abeshackathon.Fragments.Dashboard;
import com.example.abeshackathon.Fragments.Medical;
import com.example.abeshackathon.Fragments.Profile;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    View behindView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        behindView = findViewById(R.id.Behindview);
        setSupportActionBar(toolbar);
        setTitle("Dashboard");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(();

        Fragment fragment = null;
        Class fragmentClass = null;
        fragmentClass = Dashboard.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        replaceFragment(fragment);
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {

            @Override
            public void onBackStackChanged() {
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.flContent);
                if (f != null) {
                    updateTitleAndDrawer(f);
                }

            }
        });
    }

    private void updateTitleAndDrawer(Fragment f) {
        String fragClassName = f.getClass().getName();
        if (fragClassName.equals(Dashboard.class.getName())) {
            behindView.setVisibility(View.VISIBLE);
            navigationView.getMenu().getItem(0).setChecked(true);
            setTitle("Dashboard");
        }
    }


    private void replaceFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.flContent, fragment, fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass = null;
        Menu menu = navigationView.getMenu();
        switch (item.getItemId()) {

            case R.id.nav_dashboard:
                behindView.setVisibility(View.VISIBLE);
                fragmentClass = Dashboard.class;
                break;

            case R.id.medical:
                behindView.setVisibility(View.GONE);
                fragmentClass= Medical.class;
                break;

            case R.id.Profile:
                behindView.setVisibility(View.GONE);
                fragmentClass= Profile.class;
                break;
            case R.id.Logout:
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
        }
        return true;
    }

}
