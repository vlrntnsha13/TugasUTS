package com.example.tugasuts.Adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasuts.Model.Dosen;
import com.example.tugasuts.R;
import com.squareup.picasso.Picasso;

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
        context = parent.getContext();
        return new DosenAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DosenAdapter.ViewHolder holder, final int position) {
        //holder.foto.setImageResource(dosenArrayList.get(position).getFoto());
        //URL url = new URL("http://image10.bizrate-images.com/resize?sq=60&uild=2216744464");
        //Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        //imageView.setImage(bmp);
        holder.foto.getLayoutParams().width = 100;
        holder.foto.getLayoutParams().height = 100;
        if (dosenArrayList.get(position).getFoto() !=null){
            Picasso.with(this.context)
                    .load(dosenArrayList.get(position).getFoto())
                    .into(holder.foto);
        }
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

    public class ViewHolder extends RecyclerView.ViewHolder
        implements View.OnCreateContextMenuListener{
        ImageView foto;
        TextView txtNama;
        TextView txtNim;
        TextView txtGelar;
        TextView txtEmail;
        TextView txtAlamat;

        public ViewHolder(View view){
            super(view);
            foto = view.findViewById(R.id.foto);
            txtNama = view.findViewById(R.id.txtNamaMatkul);
            txtNim = view.findViewById(R.id.txtNidn);
            txtGelar = view.findViewById(R.id.txtGelar);
            txtEmail = view.findViewById(R.id.txtEmail);
            txtAlamat = view.findViewById(R.id.txtAlamat);

            view.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Pilih Aksi");
            contextMenu.add(this.getAdapterPosition(), view.getId(), 0, "Ubah Data Dosen");
            contextMenu.add(this.getAdapterPosition(), view.getId(), 0, "Hapus Data Dosen");
        }
    }
}
