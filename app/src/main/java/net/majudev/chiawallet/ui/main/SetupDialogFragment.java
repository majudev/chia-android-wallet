package net.majudev.chiawallet.ui.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import net.majudev.chiawallet.R;
import net.majudev.chiawallet.chia.SeedUtil;
import net.majudev.chiawallet.chia.Wallet;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;

public class SetupDialogFragment extends DialogFragment implements DialogInterface.OnClickListener{
    SetupDialogFragment root = this;
    Activity activity;

    public SetupDialogFragment(Activity activity){
        this.activity = activity;
    }

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // Get the layout inflater
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_setup, null);

        ProgressBar progressbar = layout.findViewById(R.id.progressBar);
        LinearLayout step1 = layout.findViewById(R.id.step1);
        RadioButton sel_new = layout.findViewById(R.id.sel_new);
        RadioButton sel_import = layout.findViewById(R.id.sel_import);
        Button step1_continue = layout.findViewById(R.id.button_continue);
        LinearLayout create1 = layout.findViewById(R.id.create1);
        TextView seed = layout.findViewById(R.id.seed);
        Button create1_forward = layout.findViewById(R.id.button_create_intermediate);
        LinearLayout create2 = layout.findViewById(R.id.create2);
        EditText check1 = layout.findViewById(R.id.check1);
        EditText check2 = layout.findViewById(R.id.check2);
        EditText check3 = layout.findViewById(R.id.check3);
        EditText check4 = layout.findViewById(R.id.check4);
        Button create2_finish = layout.findViewById(R.id.button_create);
        ScrollView import1 = layout.findViewById(R.id.import1);
        EditText word1 = layout.findViewById(R.id.word1);
        EditText word2 = layout.findViewById(R.id.word2);
        EditText word3 = layout.findViewById(R.id.word3);
        EditText word4 = layout.findViewById(R.id.word4);
        EditText word5 = layout.findViewById(R.id.word5);
        EditText word6 = layout.findViewById(R.id.word6);
        EditText word7 = layout.findViewById(R.id.word7);
        EditText word8 = layout.findViewById(R.id.word8);
        EditText word9 = layout.findViewById(R.id.word9);
        EditText word10 = layout.findViewById(R.id.word10);
        EditText word11 = layout.findViewById(R.id.word11);
        EditText word12 = layout.findViewById(R.id.word12);
        EditText word13 = layout.findViewById(R.id.word13);
        EditText word14 = layout.findViewById(R.id.word14);
        EditText word15 = layout.findViewById(R.id.word15);
        EditText word16 = layout.findViewById(R.id.word16);
        EditText word17 = layout.findViewById(R.id.word17);
        EditText word18 = layout.findViewById(R.id.word18);
        EditText word19 = layout.findViewById(R.id.word19);
        EditText word20 = layout.findViewById(R.id.word20);
        EditText word21 = layout.findViewById(R.id.word21);
        EditText word22 = layout.findViewById(R.id.word22);
        EditText word23 = layout.findViewById(R.id.word23);
        EditText word24 = layout.findViewById(R.id.word24);
        Button button_import = layout.findViewById(R.id.button_import);

        final SeedUtil seedhelper = new SeedUtil();
        int[] wordn = {0,0,0,0};
        step1_continue.setOnClickListener(v -> {
            if(sel_import.isChecked()){
                // Set up hints
                word1.setHint(activity.getString(R.string.setup_wordno) + "1");
                word2.setHint(activity.getString(R.string.setup_wordno) + "2");
                word3.setHint(activity.getString(R.string.setup_wordno) + "3");
                word4.setHint(activity.getString(R.string.setup_wordno) + "4");
                word5.setHint(activity.getString(R.string.setup_wordno) + "5");
                word6.setHint(activity.getString(R.string.setup_wordno) + "6");
                word7.setHint(activity.getString(R.string.setup_wordno) + "7");
                word8.setHint(activity.getString(R.string.setup_wordno) + "8");
                word9.setHint(activity.getString(R.string.setup_wordno) + "9");
                word10.setHint(activity.getString(R.string.setup_wordno) + "10");
                word11.setHint(activity.getString(R.string.setup_wordno) + "11");
                word12.setHint(activity.getString(R.string.setup_wordno) + "12");
                word13.setHint(activity.getString(R.string.setup_wordno) + "13");
                word14.setHint(activity.getString(R.string.setup_wordno) + "14");
                word15.setHint(activity.getString(R.string.setup_wordno) + "15");
                word16.setHint(activity.getString(R.string.setup_wordno) + "16");
                word17.setHint(activity.getString(R.string.setup_wordno) + "17");
                word18.setHint(activity.getString(R.string.setup_wordno) + "18");
                word19.setHint(activity.getString(R.string.setup_wordno) + "19");
                word20.setHint(activity.getString(R.string.setup_wordno) + "20");
                word21.setHint(activity.getString(R.string.setup_wordno) + "21");
                word22.setHint(activity.getString(R.string.setup_wordno) + "22");
                word23.setHint(activity.getString(R.string.setup_wordno) + "23");
                word24.setHint(activity.getString(R.string.setup_wordno) + "24");

                // Continue to import step
                step1.setVisibility(View.GONE);
                import1.setVisibility(View.VISIBLE);
            }else if(sel_new.isChecked()){
                // Show progress bar
                step1.setVisibility(View.GONE);
                progressbar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        // Generate seed
                        seedhelper.selfpopulate();
                        seed.setText(seedhelper.seed);
                        Random r = new Random();
                        wordn[0] = r.nextInt(10) + 1;
                        for(int i = 1; i < 4; ++i){
                            while(!(wordn[i] > wordn[i-1])) wordn[i] = r.nextInt(21 + i) + 1;
                        }
                        wordn[0] = r.nextInt(24) + 1;
                        wordn[1] = r.nextInt(24) + 1;
                        wordn[2] = r.nextInt(24) + 1;
                        wordn[3] = r.nextInt(24) + 1;
                        Arrays.sort(wordn);
                        check1.setHint(activity.getString(R.string.setup_wordno) + wordn[0]);
                        check2.setHint(activity.getString(R.string.setup_wordno) + wordn[1]);
                        check3.setHint(activity.getString(R.string.setup_wordno) + wordn[2]);
                        check4.setHint(activity.getString(R.string.setup_wordno) + wordn[3]);

                        // Progress to create step (show seed)
                        progressbar.setVisibility(View.GONE);
                        create1.setVisibility(View.VISIBLE);
                    }
                };
                handler.postDelayed(runnable, 100);
            }
        });
        create1_forward.setOnClickListener(v -> {
            create1.setVisibility(View.GONE);
            create2.setVisibility(View.VISIBLE);
        });

        check1.setOnFocusChangeListener((v, hasFocus) -> {
            if(seedhelper.verifyWord(check1.getText().toString(), wordn[0] - 1)){
                check1.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
            }else{
                check1.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
            }
            if(check1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && check2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && check3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && check4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)){
                create2_finish.setEnabled(true);
            }else{
                create2_finish.setEnabled(false);
            }
        });
        check2.setOnFocusChangeListener((v, hasFocus) -> {
            if(seedhelper.verifyWord(check2.getText().toString(), wordn[1] - 1)){
                check2.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
            }else{
                check2.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
            }
            if(check1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && check2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && check3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && check4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)){
                create2_finish.setEnabled(true);
            }else{
                create2_finish.setEnabled(false);
            }
        });
        check3.setOnFocusChangeListener((v, hasFocus) -> {
            if(seedhelper.verifyWord(check3.getText().toString(), wordn[2] - 1)){
                check3.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
            }else{
                check3.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
            }
            if(check1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && check2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && check3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && check4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)){
                create2_finish.setEnabled(true);
            }else{
                create2_finish.setEnabled(false);
            }
        });
        check4.setOnFocusChangeListener((v, hasFocus) -> {
            if(seedhelper.verifyWord(check4.getText().toString(), wordn[3] - 1)){
                check4.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
            }else{
                check4.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
            }
            if(check1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && check2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && check3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && check4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)){
                create2_finish.setEnabled(true);
            }else{
                create2_finish.setEnabled(false);
            }
        });
        create2_finish.setOnClickListener(v -> {
            create2.setVisibility(View.GONE);
            progressbar.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // Push seed to settings via Wallet singleton
                    seedhelper.commit(activity);
                    if(!Wallet.getInstance().initialize(activity)){
                        activity.finish();
                    }

                    // Progress to create step (show seed)
                    root.dismiss();
                }
            };
            handler.post(runnable);
        });

        word1.setOnFocusChangeListener((v, hasFocus) -> {
            if(SeedUtil.wordExists(word1.getText().toString())){
                word1.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
            }else{
                word1.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
            }
            if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
            ){
                button_import.setEnabled(true);
            }else{
                button_import.setEnabled(false);
            }
        });
        word2.setOnFocusChangeListener((v, hasFocus) -> {
            if(SeedUtil.wordExists(word2.getText().toString())){
                word2.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
            }else{
                word2.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
            }
            if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                    && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
            ){
                button_import.setEnabled(true);
            }else{
                button_import.setEnabled(false);
            }
        });
        word3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word3.getText().toString())){
                    word3.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word3.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                button_import.setEnabled(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green));
            }
        });
        word4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word4.getText().toString())){
                    word4.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word4.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word5.getText().toString())){
                    word5.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word5.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word6.getText().toString())){
                    word6.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word6.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word7.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word7.getText().toString())){
                    word7.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word7.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word8.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word8.getText().toString())){
                    word8.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word8.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word9.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word9.getText().toString())){
                    word9.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word9.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word10.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word10.getText().toString())){
                    word10.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word10.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word11.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word11.getText().toString())){
                    word11.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word11.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word12.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word12.getText().toString())){
                    word12.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word12.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word13.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word13.getText().toString())){
                    word13.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word13.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word14.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word14.getText().toString())){
                    word14.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word14.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word15.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word15.getText().toString())){
                    word15.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word15.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word16.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word16.getText().toString())){
                    word16.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word16.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word17.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word17.getText().toString())){
                    word17.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word17.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word18.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word18.getText().toString())){
                    word18.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word18.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word19.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word19.getText().toString())){
                    word19.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word19.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word20.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word20.getText().toString())){
                    word20.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word20.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word21.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word21.getText().toString())){
                    word21.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word21.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word22.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word22.getText().toString())){
                    word22.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word22.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word23.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word23.getText().toString())){
                    word23.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word23.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        word24.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(SeedUtil.wordExists(word24.getText().toString())){
                    word24.setTextColor(ContextCompat.getColor(activity, R.color.ok_green));
                }else{
                    word24.setTextColor(ContextCompat.getColor(activity, R.color.error_red));
                }
                if(word1.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word2.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word3.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word4.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word5.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word6.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word7.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word8.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word9.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word10.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word11.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word12.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word13.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word14.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word15.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word16.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word17.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word18.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word19.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word20.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word21.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word22.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word23.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                        && word24.getCurrentTextColor() == ContextCompat.getColor(activity, R.color.ok_green)
                ){
                    button_import.setEnabled(true);
                }else{
                    button_import.setEnabled(false);
                }
            }
        });
        button_import.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                import1.setVisibility(View.GONE);
                progressbar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        // Push seed to settings via Wallet singleton
                        String seed = word1.getText().toString() + " " +
                                word2.getText().toString() + " " +
                                word3.getText().toString() + " " +
                                word4.getText().toString() + " " +
                                word5.getText().toString() + " " +
                                word6.getText().toString() + " " +
                                word7.getText().toString() + " " +
                                word8.getText().toString() + " " +
                                word9.getText().toString() + " " +
                                word10.getText().toString() + " " +
                                word11.getText().toString() + " " +
                                word12.getText().toString() + " " +
                                word13.getText().toString() + " " +
                                word14.getText().toString() + " " +
                                word15.getText().toString() + " " +
                                word16.getText().toString() + " " +
                                word17.getText().toString() + " " +
                                word18.getText().toString() + " " +
                                word19.getText().toString() + " " +
                                word20.getText().toString() + " " +
                                word21.getText().toString() + " " +
                                word22.getText().toString() + " " +
                                word23.getText().toString() + " " +
                                word24.getText().toString();
                        if(!seedhelper.populate(seed)){
                            Snackbar.make(v, "Critical error", BaseTransientBottomBar.LENGTH_LONG).show();
                            activity.finish();
                        }
                        seedhelper.commit(activity);

                        // Progress to create step (show seed)
                        root.dismiss();
                    }
                };
                handler.post(runnable);
            }
        });

        builder.setView(layout);
        this.setCancelable(false);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
    }
}