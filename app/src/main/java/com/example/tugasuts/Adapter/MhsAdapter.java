package com.example.tugasuts.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasuts.Model.Mahasiswa;
import com.example.tugasuts.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MhsAdapter  extends RecyclerView.Adapter<MhsAdapter.ViewHolder> {
    ArrayList<Mahasiswa> mahasiswaArrayList;
    Context context;

    public MhsAdapter(ArrayList<Mahasiswa> mahasiswaArrayList) {
        this.mahasiswaArrayList = mahasiswaArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MhsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_mhs, parent, false);
        return new MhsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MhsAdapter.ViewHolder holder, final int position) {
        //holder.imagemhsImage.setImageResource(mahasiswaArrayList.get(position).getMhsImage());
        holder.imagemhsImage.getLayoutParams().width = 100;
        holder.imagemhsImage.getLayoutParams().height = 100;
        if (mahasiswaArrayList.get(position).getMhsImage() !=null){
            Picasso.with(this.context)
                    .load("https://kpsi.fti.ukdw.ac.id/progmob/"+mahasiswaArrayList.get(position).getMhsImage())
                    .into(holder.imagemhsImage);
        }
        holder.txtNama.setText(mahasiswaArrayList.get(position).getNama());
        holder.txtNim.setText(mahasiswaArrayList.get(position).getNim());
        holder.txtEmail.setText(mahasiswaArrayList.get(position).getEmail());
        holder.txtAlamat.setText(mahasiswaArrayList.get(position).getAlamat());

    }

    @Override
    public int getItemCount() {
        return (mahasiswaArrayList != null) ? mahasiswaArrayList.size() : 0 ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagemhsImage;
        TextView txtNama;
        TextView txtNim;
        TextView txtEmail;
        TextView txtAlamat;

        public ViewHolder(View view){
            super(view);
            imagemhsImage = view.findViewById(R.id.browseFoto);
            txtNama = view.findViewById(R.id.namaMhs);
            txtNim = view.findViewById(R.id.nim);
            txtEmail = view.findViewById(R.id.txtEmail);
            txtAlamat = view.findViewById(R.id.txtAlamat);
        }
    }

}
