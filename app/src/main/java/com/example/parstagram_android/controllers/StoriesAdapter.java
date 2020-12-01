package com.example.parstagram_android.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.parstagram_android.R;
import com.example.parstagram_android.models.Following;
import com.parse.ParseFile;

import java.util.List;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder> {
    private Following following;
    private Context context;
    private List<Following> followings;

    public StoriesAdapter(Context context, List<Following> followings) {
        this.context = context;
        this.followings = followings;
    }

    @NonNull
    @Override
    public StoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_story, parent, false);
        return new StoriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoriesAdapter.ViewHolder holder, int position) {
        following = followings.get(position);
        holder.bind(following);
    }

    public void clear() {
        followings.clear();
        notifyDataSetChanged();

    }

    // Add a list of items -- change to type used
    public void addAll(List<Following> list) {
        followings.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return followings.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivProfileImage;
        private TextView tvUserName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvUserName = itemView.findViewById(R.id.tvPosts);
        }

        public void bind(Following following) {

            ParseFile pImage = following.getUser().getParseFile("image");
            if (pImage != null)
                Glide.with(context).load(following.getUserFollowed().getParseFile("image").getUrl()).circleCrop().into(ivProfileImage);
            tvUserName.setText(following.getUserFollowed().getUsername());
            ParseFile image = following.getImage();
        }
    }
}

