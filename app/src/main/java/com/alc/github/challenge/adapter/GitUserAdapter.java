package com.alc.github.challenge.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alc.github.challenge.R;
import com.alc.github.challenge.activity.DetailActivity;
import com.alc.github.challenge.model.GitUser;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Gino Osahon on 20/04/2017.
 */
public class GitUserAdapter extends RecyclerView.Adapter<GitUserAdapter.GitUserViewHolder> {

    private List<GitUser> gitUserList;
    private int rowLayout;
    private Context context;

    public String image_url;
    public String gitUsername;
    public String profileUrl;

    public GitUserAdapter(List<GitUser> gitUsers, int rowLayout, Context context) {
        this.gitUserList = gitUsers;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    public GitUserAdapter(){

    }

    //A view holder inner class where we get reference to the views in the layout using their ID

    public static class GitUserViewHolder extends RecyclerView.ViewHolder{

        ImageView profileImage;
        TextView username;

        public GitUserViewHolder(View view){
            super(view);

            profileImage = (ImageView) view.findViewById(R.id.profile_image);
            username = (TextView) view.findViewById(R.id.username);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    GitUserAdapter git = new GitUserAdapter();
                    String mImageUrl = git.image_url;
                    String mUsername = git.gitUsername;
                    String mProfileUrl = git.profileUrl;

                    int position = getAdapterPosition();

                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("image", mImageUrl);
                    bundle.putString("name", mUsername);
                    bundle.putString("profileUrl", mProfileUrl);
                    bundle.putInt("position", position);
                    //intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public GitUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent,false);

        return new GitUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GitUserViewHolder holder, int position) {
        image_url = gitUserList.get(position).getAvatarUrl();

        Picasso.with(context)
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.profileImage);
        holder.username.setText(gitUserList.get(position).getLogin());

        gitUsername = gitUserList.get(position).getLogin();
        profileUrl = gitUserList.get(position).getUrl();
    }

    @Override
    public int getItemCount() {
        return gitUserList.size();
    }
}
