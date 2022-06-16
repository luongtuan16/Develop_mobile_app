package com.example.navmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.navmenu.fragments.ChangePasswordFragment;
import com.example.navmenu.fragments.FavoriteFragment;
import com.example.navmenu.fragments.HistoryFragment;
import com.example.navmenu.fragments.HomeFragment;
import com.example.navmenu.fragments.ProfileFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_FAVORITE = 1;
    private static final int FRAGMENT_HISTORY = 2;
    private static final int FRAGMENT_PROFILE = 3;
    private static final int FRAGMENT_CHANGE_PASSWORD = 4;

    private int mCurFragment = FRAGMENT_HOME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home){
            if (mCurFragment!=FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                mCurFragment = FRAGMENT_HOME;
            }
        }else if (id == R.id.nav_favorite){
            if (mCurFragment!=FRAGMENT_FAVORITE){
                replaceFragment(new FavoriteFragment());
                mCurFragment = FRAGMENT_FAVORITE;
            }
        }else if (id == R.id.nav_profile){
            if (mCurFragment!=FRAGMENT_PROFILE){
                replaceFragment(new ProfileFragment());
                mCurFragment = FRAGMENT_PROFILE;
            }
        }else if (id == R.id.nav_change_password){
            if (mCurFragment!=FRAGMENT_CHANGE_PASSWORD){
                replaceFragment(new ChangePasswordFragment());
                mCurFragment = FRAGMENT_CHANGE_PASSWORD;
            }
        }else if (id == R.id.nav_history){
            if (mCurFragment!=FRAGMENT_HISTORY){
                replaceFragment(new HistoryFragment());
                mCurFragment = FRAGMENT_HISTORY;
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
            mDrawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}