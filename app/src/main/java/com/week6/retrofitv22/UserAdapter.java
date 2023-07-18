package com.week6.retrofitv22;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<UserResponse> users;
    private OnItemClickListener listener;
    public UserAdapter(List<UserResponse> users)
    {
        this.users = users;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {

        UserResponse user = users.get(position);
        holder.textViewName.setText(user.getName());
        holder.imageView.setImageResource(R.drawable.icon);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(UserResponse user);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        UserResponse user = users.get(position);
                        listener.onItemClick(user);
                    }
                }
            });
        }
    }
}
