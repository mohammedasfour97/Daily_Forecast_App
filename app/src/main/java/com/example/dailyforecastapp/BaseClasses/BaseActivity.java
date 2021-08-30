package com.example.dailyforecastapp.BaseClasses;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;

import com.example.dailyforecastapp.R;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class BaseActivity extends AppCompatActivity {

    private BaseDialog baseDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseDialog = new BaseDialog(this);
    }

    public void showInfoDialogWithTwoButtons(String title, String message, String positive_button_message,
                                             String negative_text_message, Closure positive_button_click,
                                             Closure negative_button_click, boolean cancelable) {

        baseDialog.awesomeInfoWithTwoButtonsDialog(title, message, positive_button_message, negative_text_message,
                positive_button_click, negative_button_click, cancelable).show();
    }


    public void showInfoDialogWithOneButton(String title, String message, String positive_button_message,
                                            Closure positive_button_click, boolean cancelable) {

        baseDialog.awesomeInfoWithOneButtonsDialog(title, message, positive_button_message,
                positive_button_click, cancelable).show();
    }

    public void showProgressDialog(String title, String message, boolean cancelable) {

        baseDialog.awesomeProgressDialog(getResources().getString(R.string.loading), message, cancelable).show();
    }

    public void showDefaultProgressDialog() {

        baseDialog.awesomeProgressDialog(getResources().getString(R.string.loading), getResources().getString(R.string.loading_msg), false)
                .show();
    }

    public void showSuccessDialog(String message, boolean cancelable) {

        baseDialog.awesomeSuccessDialog(message, cancelable).show();
    }

    public void showFailedDialog(String message, boolean cancelable) {

        baseDialog.awesomeErrorDialog(message, cancelable).show();
    }

    public void showFailedDialogWithCustomButton(String message, String buttonText , Closure closure, boolean cancelable) {

        baseDialog.awesomeErrorDialogWithCustomButton(message, buttonText , closure,  cancelable).show();
    }

    public void hideFailedDialog(){

        baseDialog.hideErrorDialog();
    }


    public void hideInfoDialogWithTwoButton() {

        baseDialog.hideInfoDialogWithTwoButton();
    }

    public void hideProgress() {

        baseDialog.hideProgress();
    }

    public void showSnackBar(int message) {

        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getResources().getString(message),
                Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public boolean hasInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseActivity.this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if ((connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
                || (connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState() == NetworkInfo.State.CONNECTED)) {
            return true;
        } else {

            showSnackBar(R.string.check_int_con);
            return false;
        }
    }

    public void hideKeyboard() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }
}
