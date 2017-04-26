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

    public List<GitUser> gitUserList;
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
//
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    GitUserAdapter gitUserAdapter = new GitUserAdapter();
//                    final GitUser user = gitUserAdapter.gitUserList.get
//
//                    int position = getAdapterPosition();
//
//                    Context context = v.getContext();
//                    Intent intent = new Intent(context, DetailActivity.class);
//                    Bundle bundle = new Bundle();
//
//
//                    bundle.putInt("position", position);
//                    //intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
//                    intent.putExtras(bundle);
//                    context.startActivity(intent);
//                }
//            });
        }
    }

    @Override
    public GitUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent,false);

        return new GitUserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final GitUserViewHolder holder, int position) {

        final GitUser user = gitUserList.get(position);

        Picasso.with(context)
                .load(user.getAvatarUrl())
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.profileImage);
        holder.username.setText(user.getLogin());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("user", user);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gitUserList.size();
    }
}
