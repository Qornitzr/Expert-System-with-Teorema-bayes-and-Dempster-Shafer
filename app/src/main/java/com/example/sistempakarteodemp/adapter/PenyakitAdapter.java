package com.example.sistempakarteodemp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.entities.Account;
import com.example.sistempakarteodemp.entities.Penyakit;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class PenyakitAdapter extends RecyclerView.Adapter<PenyakitAdapter.MyViewHolder> {
    public List<Penyakit> penyakitList;
    Context context;
    private OnClickListenerPenyakit listenerPenyakit;

    public PenyakitAdapter(List<Penyakit> penyakitList, Context context) {
        this.penyakitList = penyakitList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvIdPenyakit, tvNamaPenyakit, tvDeskripsi, tvSaran;
        public CardView cardView;
        public DBHelper dbHelper;

        public MyViewHolder(View view) {
            super(view);
            tvIdPenyakit = view.findViewById(R.id.tv_IdPenyakit);
            tvNamaPenyakit = view.findViewById(R.id.tv_NamaPenyakit);
            tvDeskripsi = view.findViewById(R.id.tv_DeskripsiPenyakit);
            tvSaran = view.findViewById(R.id.tv_SaranPenyakit);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long potition = (long) view.getTag();
                    listenerPenyakit.onItemClickPenyakit(potition);
                }
            });
        }
    }

    public interface OnClickListenerPenyakit{
        void onItemClickPenyakit(long id);
    }

    public void setOnItemClickListenerPenyakit(OnClickListenerPenyakit listenerPenyakit){
        this.listenerPenyakit = listenerPenyakit;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout_penyakit, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Penyakit penyakit = penyakitList.get(position);

        long id = penyakit.getId_penyakit();
        holder.itemView.setTag(id);
        holder.tvIdPenyakit.setText("P"+String.valueOf(penyakit.getId_penyakit()));
        holder.tvNamaPenyakit.setText(penyakit.getNama_penyakit());
        holder.tvDeskripsi.setText(penyakit.getDeskripsi());
        holder.tvSaran.setText(penyakit.getSaran());
        holder.dbHelper = new DBHelper(context);

    }

    @Override
    public int getItemCount() {
        return penyakitList.size();
    }
}
