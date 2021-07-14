package net.majudev.chiawallet.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.majudev.chiawallet.R;
import net.majudev.chiawallet.chia.Wallet;

/**
 * A fragment representing a list of Items.
 */
public class TxlistFragment extends Fragment {
    WalletViewModel model;
    boolean updater_on = true;

    public static TxlistFragment newInstance() {
        // Return new object
        TxlistFragment fragment = new TxlistFragment();
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TxlistFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(WalletViewModel.class);

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Pull changes from Wallet singleton every 5 seconds
                model.setTXList(Wallet.getInstance().getTXs());
                if (updater_on) {
                    handler.postDelayed(this, 5000);
                }
            }
        };
        //handler.post(runnable);
        handler.post(runnable);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_txlist_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new TxlistAdapter(this.model, getViewLifecycleOwner()));
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.updater_on = false;
        this.model = null;
    }
}