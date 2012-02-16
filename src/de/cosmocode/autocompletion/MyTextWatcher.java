package de.cosmocode.autocompletion;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;

/**
 * @author Dennis Bläsing <blaesing@cosmocode.de>
 */
public class MyTextWatcher implements TextWatcher {
    private final AutoCompleteTextView mTextView;
    private final MyAutoCompleteAdapter mAdapter;

    private CharSequence mLastSearchString;
    private static final int AUTOCOMPLETION_DELAY = 600; // in milliseconds
    private final CountDownTimer mTimer;

    public MyTextWatcher(AutoCompleteTextView autoCompleteTextView, MyAutoCompleteAdapter autoCompleteAdapter) {
        mTextView = autoCompleteTextView;
        mAdapter = autoCompleteAdapter;

        mTimer = getAutoCompletionTimer();
    }

    private CountDownTimer getAutoCompletionTimer() {
        return new CountDownTimer(AUTOCOMPLETION_DELAY, AUTOCOMPLETION_DELAY) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                if(mLastSearchString.length() > 0) {
                   // Do the API call here
                   String[] evilDummyResults = { "SOPA", "ACTA", "IPRED", "PIPA", "TRIPS" };
                   
                    for(String result : evilDummyResults) {
                        mAdapter.add(result);
                    }
                }
            }
        };
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // empty implementation because we dont need to do anything here
    }

    @Override
    public void onTextChanged(CharSequence currentSearchString, int start, int before, int count) {
        if(currentSearchString.length() < mTextView.getThreshold()) {
            cancelCountDownTimer();
            if(!mAdapter.isEmpty()) mAdapter.clear();
            return;
        }

        mLastSearchString = currentSearchString;
        resetCountDownTimer();
    }

    @Override
    public void afterTextChanged(Editable editable) {
        // empty implementation because we dont need to do anything here
    }

    /**
    * Resets the AutoCompletion Timer
    */
   private void resetCountDownTimer() {
       cancelCountDownTimer();
       mTimer.start();
   }

   /**
    * Cancels the AutoCompletions Timer
    */
   private void cancelCountDownTimer() {
       mTimer.cancel();
   }
}
