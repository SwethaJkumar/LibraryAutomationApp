package com.example.library_automation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdduserViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;

    public AdduserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is add user fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}