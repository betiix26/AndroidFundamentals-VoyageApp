package com.travel.voyage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.travel.voyage.trip.NewTripActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
/**
 * @author Beti
 */
public class HomeActivity extends AppCompatActivity {

    public static FloatingActionButton fab;

    private AppBarConfiguration mAppBarConfiguration;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        TextView navName, navEmail;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), NewTripActivity.class);
            startActivity(i);
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_about, R.id.nav_contact, R.id.nav_share).setOpenableLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navName = headerView.findViewById(R.id.nav_name);
        navEmail = headerView.findViewById(R.id.nav_email);

        navName.setText(DataManager.getLoggedInUser().getName());
        navEmail.setText(DataManager.getLoggedInUser().getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    public void logout(MenuItem item) {
        DataManager.setLoggedInUser(null, HomeActivity.this);

        startActivity(new Intent(HomeActivity.this, LoginActivity.class));

        finish();
    }

    public void openEmailLink(View view) {
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getString(R.string.contact_email)));
        startActivity(intent);
    }

    public void openLinkedInLink(View view) {
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getString(R.string.contact_linkedin)));
        startActivity(intent);
    }

    public void openGithubLink(View view) {
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getString(R.string.contact_github)));
        startActivity(intent);
    }

    public void share(MenuItem item) {
        intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.text_share));
        intent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(intent, null);
        startActivity(shareIntent);
    }
}