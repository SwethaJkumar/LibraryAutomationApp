package com.example.library_automation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DeluserViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;

    public DeluserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is del user fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}