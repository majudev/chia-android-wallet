package net.majudev.chiawallet.ui.main;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.majudev.chiawallet.R;
import net.majudev.chiawallet.chia.Wallet;
import net.majudev.chiawallet.ui.main.TxlistContentManager.TxlistItem;
import net.majudev.chiawallet.databinding.FragmentTxlistBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * {@link RecyclerView.Adapter} that can display a {@link TxlistItem}.
 */
public class TxlistAdapter extends RecyclerView.Adapter<TxlistAdapter.ViewHolder> {

    public TxlistAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentTxlistBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.updateView(Wallet.getInstance().getTXs().get(position));
    }

    @Override
    public int getItemCount() {
        return Wallet.getInstance().getTXs().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final LinearLayout shortv;
        public final ImageView s_txstate;
        public final ImageView s_direction;
        public final TextView s_address;
        public final TextView s_amount;

        public final LinearLayout longv;
        public final ImageView l_txstate;
        public final ImageView l_direction;
        public final TextView l_date;
        public final ImageView l_explore;
        public final TextView l_address;
        public final LinearLayout l_outgoing;
        public final TextView l_sent;
        public final TextView l_fee;
        public final TextView l_total;
        public final TextView l_confirmations;

        public ViewHolder(FragmentTxlistBinding binding) {
            super(binding.getRoot());
            shortv = binding.shortv;
            s_txstate = binding.sTxstate;
            s_direction = binding.sDirection;
            s_address = binding.sAddress;
            s_amount = binding.sAmount;

            longv = binding.longv;
            l_txstate = binding.lTxstate;
            l_direction = binding.lDirection;
            l_date = binding.lDate;
            l_explore = binding.lExplore;
            l_address = binding.lAddress;
            l_outgoing = binding.lOutgoing;
            l_sent = binding.lSent;
            l_fee = binding.lFee;
            l_total = binding.lTotal;
            l_confirmations = binding.lConfirmations;

            shortv.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    shortv.setVisibility(View.GONE);
                    longv.setVisibility(View.VISIBLE);
                }
            });

            longv.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    longv.setVisibility(View.GONE);
                    shortv.setVisibility(View.VISIBLE);
                }
            });
        }

        public void updateView(Wallet.TX tx){
            // Update the static information
            String confirmations1 = this.l_confirmations.getContext().getString(R.string.txlist_confirmations);
            String confirmations2 = this.l_confirmations.getContext().getString(R.string.txlist_confirmations2);
            String confirmations = confirmations1 + " " + tx.confirmations + " " + confirmations2;
            this.l_confirmations.setText(confirmations);
            this.l_address.setText(tx.address);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault());
            this.l_date.setText(sdf.format(tx.date));
            this.l_sent.setText("-" + tx.amount);
            this.l_fee.setText("-" + tx.fee);
            this.s_address.setText(this.l_address.getText());
            this.l_explore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String link1 = l_explore.getContext().getString(R.string.tx_explorer_link);
                    String link2 = l_explore.getContext().getString(R.string.tx_explorer_link2);
                    String link = link1 + tx.hash + link2;
                    Uri uri = Uri.parse(link);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    l_explore.getContext().startActivity(intent);
                }
            });
            this.longv.setVisibility(View.GONE);
            this.shortv.setVisibility(View.VISIBLE);

            // Direction stuff
            Drawable direction;
            if(tx.direction == Wallet.TX.Direction.IN){
                this.s_amount.setText("+" + tx.amount);
                this.l_total.setText("+" + (tx.amount + tx.fee));
                this.l_total.setTextColor(ContextCompat.getColor(l_total.getContext(), R.color.green_500));
                this.s_amount.setTextColor(ContextCompat.getColor(s_amount.getContext(), R.color.green_500));
                this.l_outgoing.setVisibility(View.GONE);
                direction = ContextCompat.getDrawable(this.s_direction.getContext(), R.drawable.txstate_arrow_left);
            }else{
                this.s_amount.setText("-" + tx.amount);
                this.l_total.setText("-" + (tx.amount + tx.fee));
                this.l_total.setTextColor(ContextCompat.getColor(l_total.getContext(), R.color.red_500));
                this.s_amount.setTextColor(ContextCompat.getColor(s_amount.getContext(), R.color.red_500));
                this.l_outgoing.setVisibility(View.VISIBLE);
                direction = ContextCompat.getDrawable(this.s_direction.getContext(), R.drawable.txstate_arrow_right);
            }
            this.s_direction.setImageDrawable(direction);
            this.l_direction.setImageDrawable(direction);

            // Confirmation circle
            Drawable txstate;
            if(tx.confirmations >= 12){
                txstate = ContextCompat.getDrawable(this.s_txstate.getContext(), R.drawable.txstate_ok);
            }else{
                txstate = ContextCompat.getDrawable(this.s_txstate.getContext(), R.drawable.txstate_waiting);
            }
            this.s_txstate.setImageDrawable(txstate);
            this.l_txstate.setImageDrawable(txstate);
        }

        /*@Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }*/
    }
}