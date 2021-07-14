package net.majudev.chiawallet.chia;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import net.majudev.chiawallet.R;
import net.majudev.chiawallet.ui.main.TxlistContentManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class Wallet {
    private static Wallet wallet = null;
    private Wallet() {
        this.mock();
    }

    public static Wallet getInstance(){
        if(wallet == null){
            wallet = new Wallet();
        }
        return wallet;
    }

    private List<TX> txs = new ArrayList<TX>();

    public List<TX> getTXs(){
        return this.txs;
    }

    private void mock(){
        for(int position = 0; position < 25; ++position) txs.add(new Wallet.TX(
                "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015" + position,
                (position % 2 == 0) ? TX.Direction.OUT : TX.Direction.IN,
                "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad",
                new Date(),
                115.185623938471*position,
                1.185623938471*(position/4),
                position % 14
        ));
    }

    public static class TX {
        public final String hash;
        public final TX.Direction direction;
        public final String address;
        public final Date date;
        public final double amount;
        public final double fee;
        public final int confirmations;

        public static enum Direction { IN, OUT };

        public TX(String hash,
                  TX.Direction direction,
                  String address,
                  Date date,
                  double amount,
                  double fee,
                  int confirmations) {
            this.hash = hash;
            this.direction = direction;
            this.address = address;
            this.date = date;
            this.amount = amount;
            this.fee = fee;
            this.confirmations = confirmations;
        }
    }

    public static class Keychain {
        public final String receive_address;
        public Drawable receive_address_qr;

        public Keychain(String pubkey){
            this.receive_address = pubkey;

            QRGEncoder pubkey_qr_encoder = new QRGEncoder(this.receive_address, null, QRGContents.Type.TEXT, 1000);
            try {
                Bitmap qr_bitmap = pubkey_qr_encoder.getBitmap();
                receive_address_qr = new BitmapDrawable(qr_bitmap);
            } catch (Exception e) {
                receive_address_qr = Resources.getSystem().getDrawable(R.drawable.chia_logo);
            }
        }
    }
}