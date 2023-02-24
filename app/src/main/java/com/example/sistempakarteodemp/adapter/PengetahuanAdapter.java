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

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class PengetahuanAdapter extends RecyclerView.Adapter<PengetahuanAdapter.MyViewHolder> {
    public List<Pengetahuan> pengetahuanList;
    Context context;
    private OnClickListenerPengetahuan listenerPengetahuan;

    public PengetahuanAdapter(List<Pengetahuan> pengetahuanList, Context context) {
        this.pengetahuanList = pengetahuanList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvIdPengetahuan, tvNamaPenyakit, tvNamaGejala, tvProbabilitasMB, tvProbabilitasMD, tvProbabilitasTotal, tvProbabilitasBayes;
        public DBHelper dbHelper;

        public MyViewHolder(View view) {
            super(view);
            tvIdPengetahuan = view.findViewById(R.id.tv_IdPengetahuan);
            tvNamaPenyakit = view.findViewById(R.id.tv_NamaPenyakitPeng);
            tvNamaGejala = view.findViewById(R.id.tv_NamaGejalaPeng);
            tvProbabilitasMB = view.findViewById(R.id.tv_ProbabilitasMB);
            tvProbabilitasMD = view.findViewById(R.id.tv_ProbabilitasMD);
            tvProbabilitasTotal = view.findViewById(R.id.tv_ProbabilitasTotal);
            tvProbabilitasBayes = view.findViewById(R.id.tv_ProbabilitasBayes);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long potition = (long) view.getTag();
                    listenerPengetahuan.onItemClickPengetahuan(potition);
                }
            });
        }
    }

    public interface OnClickListenerPengetahuan{
        void onItemClickPengetahuan(long id);
    }

    public void setOnItemClickListenerPengetahuan(OnClickListenerPengetahuan listenerPengetahuan){
        this.listenerPengetahuan = listenerPengetahuan;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout_pengetahuan, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Pengetahuan pengetahuan = pengetahuanList.get(position);

        long id = pengetahuan.getId_pengetahuan();
        holder.itemView.setTag(id);
        holder.tvIdPengetahuan.setText(String.valueOf(pengetahuan.getId_pengetahuan()));
        holder.tvNamaPenyakit.setText(pengetahuan.getNama_penyakit());
        holder.tvNamaGejala.setText(pengetahuan.getNama_gejala());
        holder.tvProbabilitasMB.setText(String.valueOf(pengetahuan.getProbabilitas_mb()));
        holder.tvProbabilitasMD.setText(String.valueOf(pengetahuan.getProbabilitas_md()));
        holder.tvProbabilitasTotal.setText(String.valueOf(pengetahuan.getProbabilitas_total()));
        holder.tvProbabilitasBayes.setText(String.valueOf(pengetahuan.getProbabilitas_bayes()));
        holder.dbHelper = new DBHelper(context);

    }

    @Override
    public int getItemCount() {
        return pengetahuanList.size();
    }
}
