package net.majudev.chiawallet.chia;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import net.majudev.chiawallet.R;
import net.majudev.chiawallet.chia.proto.BIP39;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
    private Keychain keychain;
    private long balance;

    public List<TX> getTXs(){
        return this.txs;
    }
    public Keychain getKeychain(){ return this.keychain; }
    public double getBalance(){ return (this.balance / 1000000000000.0); }
    public long getBalanceMojo(){ return this.balance; }

    public void mock(){
        for(int position = 0; position < 25; ++position) txs.add(new Wallet.TX(
                "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015" + position,
                (position % 2 == 0) ? TX.Direction.OUT : TX.Direction.IN,
                "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad",
                new Date(),
                115.185623938471*position,
                1.185623938471*(position/4),
                position % 14
        ));
        //keychain = new Keychain("xch1n7k27dk83kx9teadk5rjqk72kjluhg5lam8xz22tuyv9z3rrqcpqu0pmda");
        balance = 100050000000000L;
    }

    public boolean initialize(Activity activity){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        if(sharedPref.contains("seed")){
            String seed = sharedPref.getString("seed", null);
            if(seed == null) return false;
            this.keychain = new Keychain(seed);
            return true;
        }else return false;
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
        public final String seed;
        public final byte[] bip39_privkey;
        public final String receive_address;
        public Drawable receive_address_qr;

        public Keychain(String seed) {
            this.seed = seed;
            //this.receive_address = pubkey;
            this.bip39_privkey = BIP39.derievePrivkey(seed, "");

            this.receive_address = "placeholder";

            QRGEncoder pubkey_qr_encoder = new QRGEncoder(this.receive_address, null, QRGContents.Type.TEXT, 1000);
            try {
                Bitmap qr_bitmap = pubkey_qr_encoder.getBitmap();
                receive_address_qr = new BitmapDrawable(qr_bitmap);
            } catch (Exception e) {
                receive_address_qr = Resources.getSystem().getDrawable(R.drawable.chia_logo);
            }
        }

        private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
        public static String bytesToHex(byte[] bytes) {
            char[] hexChars = new char[bytes.length * 2];
            for (int j = 0; j < bytes.length; j++) {
                int v = bytes[j] & 0xFF;
                hexChars[j * 2] = HEX_ARRAY[v >>> 4];
                hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
            }
            return new String(hexChars);
        }
    }
}
