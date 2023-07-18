package com.week6.retrofitv22;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapater extends RecyclerView.Adapter<PostAdapater.ViewHolder>  {

    List<PostResponse> posts;

    public PostAdapater(List<PostResponse> posts)
    {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostAdapater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapater.ViewHolder holder, int position) {
        PostResponse post = posts.get(position);
        holder.textViewID.setText(String.valueOf(post.getId()));
        holder.textViewTitle.setText(post.getTitle());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewID;
        TextView textViewTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewID = itemView.findViewById(R.id.textViewPostID);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }
    }
}
