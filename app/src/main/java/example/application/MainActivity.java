package example.application;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import example.data.DatabaseHelper;

//public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
public class MainActivity extends AppCompatActivity{


    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);
        //navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.bot_nav_home);
        }

        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.bot_nav_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.bot_nav_pantry:
                    replaceFragment(new PantryFragment());
                    break;
                case R.id.bot_nav_tips:
                    replaceFragment(new TipsFragment());
                    break;
                case R.id.bot_nav_profile:
                    replaceFragment(new ProfileFragment());
                    break;
            }
            return true;
        });

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.side_nav_recipe:
                    replaceFragment(new RecipeFragment());
                    break;

                case R.id.side_nav_settings:
                    replaceFragment(new SettingsFragment());
                    break;

                case R.id.side_nav_help:
                    replaceFragment(new HelpFragment());
                    break;

                case R.id.side_nav_about:
                    replaceFragment(new AboutFragment());
                    break;

                case R.id.side_nav_logout:
                    Toast.makeText(MainActivity.this, "Logout!", Toast.LENGTH_SHORT).show();
                    break;
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

    }


    @Override
    public void onBackPressed()
    {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
