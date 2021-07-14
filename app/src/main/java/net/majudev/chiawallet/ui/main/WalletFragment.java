package net.majudev.chiawallet.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.WriterException;

import net.majudev.chiawallet.R;
import net.majudev.chiawallet.chia.Wallet;
import net.majudev.chiawallet.databinding.FragmentWalletBinding;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class WalletFragment extends Fragment {
    WalletViewModel model;
    FragmentWalletBinding binding;
    boolean updater_on = true;

    public static WalletFragment newInstance() {
        return new WalletFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updater_on = true;
        model = new ViewModelProvider(this).get(WalletViewModel.class);

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Pull changes from Wallet singleton every 5 seconds
                model.setBalance(Wallet.getInstance().getBalance());
                if(Wallet.getInstance().getKeychain() != null) model.setKeychain(Wallet.getInstance().getKeychain());
                if (updater_on) {
                    handler.postDelayed(this, 5000);
                }
            }
        };
        handler.post(runnable);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentWalletBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ImageView receive_address_qr = binding.imageView;
        final TextView receive_address = binding.receiveAddress;
        final TextView balance = binding.balance;
        model.getKeychain().observe(getViewLifecycleOwner(), new Observer<Wallet.Keychain>() {
            @Override
            public void onChanged(Wallet.Keychain keychain) {
                receive_address_qr.setImageDrawable(keychain.receive_address_qr);
                receive_address.setText(keychain.receive_address);
            }
        });
        model.getBalance().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double b) {
                String balance1 = inflater.getContext().getString(R.string.wallet_balance);
                String balance2 = inflater.getContext().getString(R.string.wallet_balance2);
                String balancet = balance1 + " " + b + " " + balance2;
                balance.setText(balancet);
            }
        });
        receive_address_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) inflater.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(inflater.getContext().getString(R.string.wallet_clip_label), receive_address.getText());
                clipboard.setPrimaryClip(clip);
                Snackbar.make(view, "Address copied to your clipboard!", Snackbar.LENGTH_SHORT).show();
            }
        });

        final Button show_tutorial = binding.showTutorial;
        final Button show_seed = binding.showSeed;
        final Button purge_wallet = binding.clearWallet;
        final Button donate = binding.donate;
        show_tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Work in progess", Snackbar.LENGTH_LONG).show();
            }
        });
        show_seed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Work in progess", Snackbar.LENGTH_LONG).show();
                new AlertDialog.Builder(getContext())
                        .setTitle(getActivity().getString(R.string.wallet_showseed_title))
                        .setMessage(getActivity().getString(R.string.wallet_showseed_message))
                        .setIcon(android.R.drawable.ic_menu_view)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                new AlertDialog.Builder(getContext())
                                        .setTitle(getActivity().getString(R.string.wallet_showseed_showntitle))
                                        .setMessage(Wallet.getInstance().getKeychain().seed)
                                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                //
                                            }}).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
        purge_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle(getActivity().getString(R.string.wallet_purge_title))
                        .setMessage(getActivity().getString(R.string.wallet_purge_message))
                        .setIcon(android.R.drawable.ic_delete)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.clear();
                                editor.commit();
                                getActivity().finish();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Work in progess", Snackbar.LENGTH_LONG).show();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        updater_on = false;
        binding = null;
    }
}