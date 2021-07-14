package net.majudev.chiawallet.chia;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

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
        return true;
    }

    public void selfpopulate(){
        this.populate("media lab aware bright spawn month garage neutral genuine cancel phone shiver promote muffin nature actress random mad cover verify frown west grief enact");
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
