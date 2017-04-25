package com.alc.github.challenge.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.alc.github.challenge.R;
import com.squareup.picasso.Picasso;

import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.alc.github.challenge.model.GitUser;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";
    String gitUsername;
    String gitProfileUrl;
    String gitProfileImageUrl;
    Context context;

    TextView mUsername;
    TextView mProfileUrl;
    ImageView mUserImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mUsername = (TextView) findViewById(R.id.username);
        mProfileUrl = (TextView) findViewById(R.id.profile_url);
        mUserImage = (ImageView) findViewById(R.id.image);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        GitUser user = getIntent().getParcelableExtra("user");

        if (user != null){
            gitUsername = user.getLogin();
            gitProfileImageUrl = user.getAvatarUrl();
            gitProfileUrl = user.getUrl();

            displayDetails(gitUsername, gitProfileImageUrl, gitProfileUrl);
        }

        collapsingToolbarLayout.setTitle("Welcome" +gitUsername);
    }

    public void displayDetails(String name, String url, String image){

        mUsername.setText(name);
        mProfileUrl.setText(url);
        Picasso.with(context)
                .load(image)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(mUserImage);
    }
}
