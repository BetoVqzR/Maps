package com.example.maps.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.maps.R;
import com.example.maps.fragments.MapFragment;
import com.example.maps.fragments.WelcomeFragment;

public class MainActivity extends AppCompatActivity {

    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            currentFragment = new WelcomeFragment();
            ChangeFragment(currentFragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_welcome:
                currentFragment= new WelcomeFragment();
                break;
            case R.id.menu_map:
                currentFragment = new MapFragment();
                break;
        }
        ChangeFragment(currentFragment);
        return super.onOptionsItemSelected(item);
    }
    private void ChangeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, fragment).commit();
    }
}
