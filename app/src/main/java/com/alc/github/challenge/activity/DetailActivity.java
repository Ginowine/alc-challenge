package com.alc.github.challenge.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.alc.github.challenge.R;
import android.support.design.widget.CollapsingToolbarLayout;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";
    String gitUsername;
    String gitProfileUrl;
    String gitProfileImageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            gitUsername = extras.getString("name");
            gitProfileImageUrl = extras.getString("image");
            gitProfileUrl = extras.getString("profileUrl");
        }

        collapsingToolbarLayout.setTitle("Welcome" +gitUsername);
    }
}
