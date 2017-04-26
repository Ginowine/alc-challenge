package com.alc.github.challenge.activity;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.alc.github.challenge.R;
import com.alc.github.challenge.util.Share;
import com.squareup.picasso.Picasso;

import android.support.design.widget.CollapsingToolbarLayout;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.alc.github.challenge.model.GitUser;
import me.saket.bettermovementmethod.BetterLinkMovementMethod;
import android.support.customtabs.CustomTabsIntent;

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

        Button shareButton = (Button) findViewById(R.id.share_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareGitUserProfile();
            }
        });

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        GitUser user = getIntent().getParcelableExtra("user");

        if (user != null){
            gitUsername = user.getLogin();
            gitProfileImageUrl = user.getAvatarUrl();
            gitProfileUrl = user.getUrl();

            displayDetails(gitUsername, gitProfileImageUrl, gitProfileUrl);
        }

        collapsingToolbarLayout.setTitle("Welcome" + gitUsername);
    }

    public void displayDetails(String name, String url, String image){

        mUsername.setText(name);
        mProfileUrl.setText(url);
        Picasso.with(context)
                .load(image)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(mUserImage);

        BetterLinkMovementMethod movementMethod = BetterLinkMovementMethod.linkify(Linkify.WEB_URLS, mProfileUrl);
        movementMethod.setOnLinkClickListener(new BetterLinkMovementMethod.OnLinkClickListener(){
            @Override
            public boolean onClick(TextView textView, String url) {
                getCustomTabIntentInstance().launchUrl(DetailActivity.this, Uri.parse(url));
                return true;
            }
        });
    }

    private CustomTabsIntent getCustomTabIntentInstance() {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        return builder.build();
    }


    public void shareGitUserProfile(){
        GitUser user = getIntent().getParcelableExtra("user");

        String message = "Check out this awesome developer @" + user.getLogin() + ", " + user.getUrl();
        Share.shareCustom(message, this);

    }
}
