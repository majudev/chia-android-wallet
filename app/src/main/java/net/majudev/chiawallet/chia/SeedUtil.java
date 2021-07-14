package net.majudev.chiawallet.chia;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import net.majudev.chiawallet.chia.proto.BIP39;

public class SeedUtil {
    public String seed;
    public String[] seedarray;
    public boolean populate(String seed){
        this.seed = seed;
        this.seedarray = seed.split(" ");
        for(String lseed : this.seedarray){
            if(!wordExists(lseed)) return false;
        }
        return BIP39.verifySeed(this.seed);
    }

    public boolean selfpopulate(){
        String lseed = BIP39.generateSeed();
        if(lseed == null) return false;
        return this.populate(lseed);
    }

    public void commit(Activity activity){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("seed", this.seed);
        editor.commit();
    }

    public boolean verifyWord(String word, int position){
        return seedarray[position].equalsIgnoreCase(word);
    }

    public static boolean wordExists(String word){
        return BIP39.wordlist.contains(word);
    }
}
