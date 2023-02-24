package com.example.sistempakarteodemp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.entities.Account;
import com.example.sistempakarteodemp.entities.Gejala;
import com.example.sistempakarteodemp.entities.Pengetahuan;
import com.example.sistempakarteodemp.entities.Penyakit;
import com.example.sistempakarteodemp.entities.Riwayat;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.MyViewHolder> {
    public List<Riwayat> riwayatList;
    Context context;
    private OnClickListenerRiwayat listenerRiwayat;

    public RiwayatAdapter(List<Riwayat> riwayatList, Context context) {
        this.riwayatList = riwayatList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvIdRiwayat, tvTanggal, tvRiwayatNamaPenyakit, tvRiwayatNilai, tvnamaGej;
        public DBHelper dbHelper;

        public MyViewHolder(View view) {
            super(view);
            tvIdRiwayat = view.findViewById(R.id.tv_IdRiwayat);
            tvTanggal = view.findViewById(R.id.tv_tanggal);
            tvRiwayatNamaPenyakit = view.findViewById(R.id.tv_namaPenyakit);
            tvRiwayatNilai = view.findViewById(R.id.tv_nilai);
            tvnamaGej = view.findViewById(R.id.tv_NamaGejala);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long potition = (long) view.getTag();
                    listenerRiwayat.onItemClickRiwayat(potition);
                }
            });
        }
    }

    public interface OnClickListenerRiwayat{
        void onItemClickRiwayat(long id);
    }

    public void setOnItemClickListenerRiwayat(OnClickListenerRiwayat listenerRiwayat){
        this.listenerRiwayat = listenerRiwayat;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout_riwayat, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Riwayat riwayat = riwayatList.get(position);

        long id = riwayat.getId_riwayat();
        holder.itemView.setTag(id);
        holder.tvIdRiwayat.setText(String.valueOf(riwayat.getId_riwayat()));
        holder.tvTanggal.setText(riwayat.getTanggal());
        holder.tvRiwayatNamaPenyakit.setText(riwayat.getRiwayat_nama_penyakit());
        holder.tvRiwayatNilai.setText(riwayat.getRiwayat_nilai());
        holder.tvnamaGej.setText(riwayat.getRiwayat_nama_gejala());
        holder.dbHelper = new DBHelper(context);

    }

    @Override
    public int getItemCount() {
        return riwayatList.size();
    }
}
