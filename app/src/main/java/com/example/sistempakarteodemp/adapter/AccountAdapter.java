package com.example.sistempakarteodemp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.entities.Account;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.MyViewHolder> {
    public List<Account> accountList;
    Context context;
    private OnClickListenerAccount listenerAccount;

    public AccountAdapter(List<Account> accountList, Context context) {
        this.accountList = accountList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvId, tvNama, tvUsername;
        public CardView cardView;
        public DBHelper dbHelper;

        public MyViewHolder(View view) {
            super(view);
            tvId = view.findViewById(R.id.tv_IdAdmin);
            tvNama = view.findViewById(R.id.tv_NamaAdmin);
            tvUsername = view.findViewById(R.id.tv_UsernameAdmin);
            cardView = view.findViewById(R.id.cardView_dataAdminList);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long potition = (long) view.getTag();
                    listenerAccount.onItemClickAccount(potition);
                }
            });
        }
    }

    public interface OnClickListenerAccount{
        void onItemClickAccount(long id);
    }

    public void setOnItemClickListenerAccount(OnClickListenerAccount listenerAccount){
        this.listenerAccount = listenerAccount;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout_admin, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Account account = accountList.get(position);

        long id = account.getId();
        holder.itemView.setTag(id);
        holder.tvNama.setText(account.getNama_admin());
        holder.tvId.setText(String.valueOf(account.getId()));
        holder.tvUsername.setText(account.getUsername());
        holder.dbHelper = new DBHelper(context);

    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }
}
