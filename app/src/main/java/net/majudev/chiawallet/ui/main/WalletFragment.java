package net.majudev.chiawallet.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public static WalletFragment newInstance() {
        return new WalletFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(WalletViewModel.class);
        model.setBalance(100.103);
        model.setKeychain(new Wallet.Keychain("xch1n7k27dk83kx9teadk5rjqk72kjluhg5lam8xz22tuyv9z3rrqcpqu0pmda"));
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
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}