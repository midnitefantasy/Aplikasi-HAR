/*package com.example.har.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.har.Model.RegisterModel;
import com.example.har.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<RegisterModel> listUser;
    public AdapterData(Context ctx, List<RegisterModel> listUser){
        this.ctx = ctx;
        this.listUser = listUser;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, att);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvNama, tvEmail, tvPassword;
        public HolderData(@NonNull View itemView){
            super(itemView);
            tvNama = itemView.findViewById(R.id.nama);
            tvEmail = itemView.findViewById(R.id.email);
            tvPassword = itemView.findViewById(R.id.password);


            }
        }
    }
}*/
