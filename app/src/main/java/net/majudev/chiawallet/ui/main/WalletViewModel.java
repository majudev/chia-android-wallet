package net.majudev.chiawallet.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.majudev.chiawallet.chia.Wallet;

import java.util.List;

public class WalletViewModel extends ViewModel {

    private MutableLiveData<List<Wallet.TX>> txlist = new MutableLiveData<List<Wallet.TX>>();
    private MutableLiveData<Double> balance = new MutableLiveData<Double>();
    private MutableLiveData<Wallet.Keychain> keychain = new MutableLiveData<Wallet.Keychain>();

    public void setBalance(double balance){
        this.balance.setValue(balance);
    }

    public void setKeychain(Wallet.Keychain keychain){
        this.keychain.setValue(keychain);
    }

    public void setTXList(List<Wallet.TX> txs){
        this.txlist.setValue(txs);
    }

    public LiveData<Double> getBalance(){
        return this.balance;
    }

    public LiveData<Wallet.Keychain> getKeychain(){
        return this.keychain;
    }

    public LiveData<List<Wallet.TX>> getTXList(){
        return this.txlist;
    }
    /*private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello world from section: " + input;
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }*/
}