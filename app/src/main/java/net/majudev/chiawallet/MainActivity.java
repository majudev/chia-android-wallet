package net.majudev.chiawallet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import net.majudev.chiawallet.chia.Wallet;
import net.majudev.chiawallet.ui.main.SectionsPagerAdapter;
import net.majudev.chiawallet.databinding.ActivityMainBinding;
import net.majudev.chiawallet.ui.main.SetupDialogFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // state
        super.onCreate(savedInstanceState);

        // setting view scope
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // setting up seed, running setup dialog
        if(!Wallet.getInstance().initialize(this)){
            SetupDialogFragment f = new SetupDialogFragment(this);
            f.show(this.getSupportFragmentManager(), "SetupDialog");
        }

        // setting up tabs
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        // set up new transaction button
        FloatingActionButton newtx = binding.newtx;
        newtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Work in progess", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}