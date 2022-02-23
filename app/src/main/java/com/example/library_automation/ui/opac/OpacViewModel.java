package com.example.library_automation.ui.opac;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OpacViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OpacViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is opac fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}