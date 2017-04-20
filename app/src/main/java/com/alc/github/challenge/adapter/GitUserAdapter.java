package com.alc.github.challenge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alc.github.challenge.R;
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

    public GitUserAdapter(List<GitUser> gitUsers, int rowLayout, Context context) {
        this.gitUserList = gitUsers;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    //A view holder inner class where we get reference to the views in the layout using their ID

    public static class GitUserViewHolder extends RecyclerView.ViewHolder{

        LinearLayout gitUserLayout;
        ImageView profileImage;
        TextView username;

        public GitUserViewHolder(View view){
            super(view);
            gitUserLayout = (LinearLayout) view.findViewById(R.id.user_layout);
            profileImage = (ImageView) view.findViewById(R.id.profile_image);
            username = (TextView) view.findViewById(R.id.username);
        }
    }

    @Override
    public GitUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent,false);

        return new GitUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GitUserViewHolder holder, int position) {
        String image_url = gitUserList.get(position).getAvatarUrl();

        Picasso.with(context)
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.profileImage);
        holder.username.setText(gitUserList.get(position).getLogin());

    }

    @Override
    public int getItemCount() {
        return gitUserList.size();
    }
}
