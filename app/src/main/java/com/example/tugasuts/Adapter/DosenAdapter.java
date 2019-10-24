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
import com.example.tugasuts.Model.Mahasiswa;
import com.example.tugasuts.R;

import java.util.ArrayList;

public class DosenAdapter  extends RecyclerView.Adapter<DosenAdapter.ViewHolder> {
    ArrayList<Dosen> dosenArrayList;
    Context context;

    public DosenAdapter(ArrayList<Dosen> dosenArrayList) {
        this.dosenArrayList = dosenArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DosenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_dosen, parent, false);
        return new DosenAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DosenAdapter.ViewHolder holder, final int position) {
        holder.imagemhsImage.setImageResource(dosenArrayList.get(position).getMhsImage());
        holder.txtNama.setText(dosenArrayList.get(position).getNama());
        holder.txtNim.setText(dosenArrayList.get(position).getNidn());
        holder.txtGelar.setText(dosenArrayList.get(position).getGelar());
        holder.txtEmail.setText(dosenArrayList.get(position).getEmail());
        holder.txtAlamat.setText(dosenArrayList.get(position).getAlamat());

    }

    @Override
    public int getItemCount() {
        return (dosenArrayList != null) ? dosenArrayList.size() : 0 ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagemhsImage;
        TextView txtNama;
        TextView txtNim;
        TextView txtGelar;
        TextView txtEmail;
        TextView txtAlamat;

        public ViewHolder(View view){
            super(view);
            imagemhsImage = view.findViewById(R.id.imagemhsImage);
            txtNama = view.findViewById(R.id.txtNama);
            txtNim = view.findViewById(R.id.txtNidn);
            txtGelar = view.findViewById(R.id.txtGelar);
            txtEmail = view.findViewById(R.id.txtEmail);
            txtAlamat = view.findViewById(R.id.txtAlamat);
        }
    }
}
