package com.example.tugasuts.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasuts.Model.Dosen;
import com.example.tugasuts.Model.Krs;
import com.example.tugasuts.R;

import java.util.ArrayList;

public class KrsAdapter  extends RecyclerView.Adapter<KrsAdapter.ViewHolder>{
    ArrayList<Krs> krsArrayList;
    Context context;

    public KrsAdapter(ArrayList<Krs> krsArrayList) {
        this.krsArrayList = krsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public KrsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_krs, parent, false);
        return new KrsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KrsAdapter.ViewHolder holder, final int position) {
        holder.txtKode.setText(krsArrayList.get(position).getKode());
        holder.txtMatkul.setText(krsArrayList.get(position).getMatkul());
        holder.txtHari.setText(krsArrayList.get(position).getHari());
        holder.txtSesi.setText(krsArrayList.get(position).getSesi());
        holder.txtJmlSks.setText(krsArrayList.get(position).getJmlsks());
        holder.txtDosen.setText(krsArrayList.get(position).getDosen());
        holder.txtJmlmhs.setText(krsArrayList.get(position).getJmlmhs());
    }

    @Override
    public int getItemCount() {
        return (krsArrayList != null) ? krsArrayList.size() : 0 ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtKode;
        TextView txtMatkul;
        TextView txtHari;
        TextView txtSesi;
        TextView txtJmlSks;
        TextView txtDosen;
        TextView txtJmlmhs;

        public ViewHolder(View view){
            super(view);
            txtKode = view.findViewById(R.id.txtKode);
            txtMatkul = view.findViewById(R.id.txtMatkul);
            txtHari = view.findViewById(R.id.txtHari);
            txtSesi = view.findViewById(R.id.txtSesi);
            txtJmlSks = view.findViewById(R.id.txtJmlsks);
            txtDosen = view.findViewById(R.id.txtDosen);
            txtJmlmhs = view.findViewById(R.id.txtJmlMhs);
        }
    }
}
