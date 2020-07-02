package com.lazyloding.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;
import com.lazyloding.R;
import com.lazyloding.databinding.ActivityHomeBinding;
import com.lazyloding.ui.fragments.allusers.AllUserFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class HomeActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    private AppBarConfiguration mAppBarConfiguration;
    ActivityHomeBinding dataBinding;
    private HomeViewModel mHomeViewModel;
    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    private FragmentManager fragmentManager;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        mHomeViewModel = ViewModelProviders.of(this, mViewModelFactory).get(HomeViewModel.class);
        replaceFragment(new AllUserFragment());
//        dataBinding.appBar1.toolbar.setTitle("Home");
    }

    public void replaceFragment(Fragment fragment) {
        // Insert the fragment by replacing any existing fragment
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_up,
                R.anim.slide_down).replace(R.id.flContent, fragment).commit();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentAndroidInjector;
    }

    public void setURL(final String url){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
            }
        }, 5000);
    }
}
