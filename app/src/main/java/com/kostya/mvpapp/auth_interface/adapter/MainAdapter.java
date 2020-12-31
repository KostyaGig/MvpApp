package com.kostya.mvpapp.auth_interface.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kostya.mvpapp.R;
import com.kostya.mvpapp.model.User;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<User> users = new ArrayList<>();

    public MainAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User currentUser = users.get(position);

        holder.bind(currentUser);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView userEmail,userPassword;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userEmail = itemView.findViewById(R.id.user_email);
            userPassword = itemView.findViewById(R.id.user_password);
        }

        public void bind(User currentUser) {

            userEmail.setText(currentUser.getEmail());
            userPassword.setText(currentUser.getPassword());
        }
    }

    public void updateListUsers(List<User> updatedList){
        users.clear();
        users.addAll(updatedList);
        notifyDataSetChanged();
    }
}
