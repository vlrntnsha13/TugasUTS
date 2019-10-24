package com.example.tugasuts.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasuts.Model.Krs;
import com.example.tugasuts.Model.Matkul;
import com.example.tugasuts.R;

import java.util.ArrayList;

public class MatkulAdapter extends RecyclerView.Adapter<MatkulAdapter.ViewHolder>{
    ArrayList<Matkul> matkulArrayList;
    Context context;

    public MatkulAdapter(ArrayList<Matkul> matkulArrayList) {
        this.matkulArrayList = matkulArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MatkulAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_krs, parent, false);
        return new MatkulAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatkulAdapter.ViewHolder holder, final int position) {
        holder.txtKode.setText(matkulArrayList.get(position).getKode());
        holder.txtMatkul.setText(matkulArrayList.get(position).getMatkul());
        holder.txtHari.setText(matkulArrayList.get(position).getHari());
        holder.txtSesi.setText(matkulArrayList.get(position).getSesi());
        holder.txtJmlSks.setText(matkulArrayList.get(position).getJmlsks());
    }

    @Override
    public int getItemCount() {
        return (matkulArrayList != null) ? matkulArrayList.size() : 0 ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtKode;
        TextView txtMatkul;
        TextView txtHari;
        TextView txtSesi;
        TextView txtJmlSks;

        public ViewHolder(View view){
            super(view);
            txtKode = view.findViewById(R.id.txtKode);
            txtMatkul = view.findViewById(R.id.txtMatkul);
            txtHari = view.findViewById(R.id.txtHari);
            txtSesi = view.findViewById(R.id.txtSesi);
            txtJmlSks = view.findViewById(R.id.txtJmlsks);
        }
    }
}
