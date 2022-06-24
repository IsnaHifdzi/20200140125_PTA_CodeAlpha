package com.example.codeaplha;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<User> userList;
    Context context;

    public Adapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {

        holder.text_usernama.setText(userList.get(position).getUsergame());
        holder.text_idgame.setText(userList.get(position).getIdgame());
        holder.text_jumlah.setText(userList.get(position).getJumlah());
        holder.text_metode.setText(userList.get(position).getMetode());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView text_usernama, text_idgame, text_jumlah, text_metode;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_usernama = itemView.findViewById(R.id.Nama);
            text_idgame = itemView.findViewById(R.id.game);
            text_jumlah = itemView.findViewById(R.id.jumlahdiamon);
            text_metode = itemView.findViewById(R.id.metodebeli);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, Payment.class);
            intent.putExtra("id", userList.get(getAdapterPosition()).getId());
            intent.putExtra("usergame", userList.get(getAdapterPosition()).getUsergame());
            intent.putExtra("idgame", userList.get(getAdapterPosition()).getIdgame());
            intent.putExtra("jumlah", userList.get(getAdapterPosition()).getJumlah());
            intent.putExtra("metode", userList.get(getAdapterPosition()).getMetode());
            context.startActivity(intent);


        }
    }
}
