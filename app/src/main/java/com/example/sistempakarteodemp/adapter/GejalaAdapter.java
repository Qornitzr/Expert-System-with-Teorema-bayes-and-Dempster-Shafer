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
import com.example.sistempakarteodemp.entities.Penyakit;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class GejalaAdapter extends RecyclerView.Adapter<GejalaAdapter.MyViewHolder> {
    public List<Gejala> gejalaList;
    Context context;
    private OnClickListenerGejala listenerGejala;

    public GejalaAdapter(List<Gejala> gejalaList, Context context) {
        this.gejalaList = gejalaList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvIdGejala, tvNamaGejala, tvPenyakitTerpilih;
        public CardView cardView;
        public DBHelper dbHelper;

        public MyViewHolder(View view) {
            super(view);
            tvIdGejala = view.findViewById(R.id.tv_IdGejala);
            tvNamaGejala = view.findViewById(R.id.tv_NamaGejala);
            tvPenyakitTerpilih = view.findViewById(R.id.tv_penyakitTerpilih);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long potition = (long) view.getTag();
                    listenerGejala.onItemClickGejala(potition);
                }
            });
        }
    }

    public interface OnClickListenerGejala{
        void onItemClickGejala(long id);
    }

    public void setOnItemClickListenerGejala(OnClickListenerGejala listenerGejala){
        this.listenerGejala = listenerGejala;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout_gejala, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Gejala gejala = gejalaList.get(position);

        long id = gejala.getId_gejala();
        holder.itemView.setTag(id);
        holder.tvIdGejala.setText("G"+String.valueOf(gejala.getId_gejala()));
        holder.tvNamaGejala.setText(gejala.getNama_gejala());
        holder.tvPenyakitTerpilih.setText(gejala.getPenyakit_terpilih());
        holder.dbHelper = new DBHelper(context);

    }

    @Override
    public int getItemCount() {
        return gejalaList.size();
    }
}
