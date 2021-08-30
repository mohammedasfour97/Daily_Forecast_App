package com.example.dailyforecastapp.BaseClasses;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeInfoDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.example.dailyforecastapp.R;

public class BaseDialog {

    private Context context;

    private AwesomeInfoDialog awesomeInfoDialog;
    private AwesomeProgressDialog awesomeProgressDialog;
    private AwesomeSuccessDialog awesomeSuccessDialog;
    private AwesomeErrorDialog awesomeErrorDialog;

    public BaseDialog(Context context) {
        this.context = context;
    }

    public AwesomeInfoDialog awesomeInfoWithTwoButtonsDialog(String title, String message, String positive_button_message,
                                                      String negative_text_message, Closure positive_button_click,
                                                      Closure negative_button_click, boolean cancelable){


        awesomeInfoDialog =  new AwesomeInfoDialog(context)
                .setTitle(getSpanningString(title, R.style.Bold_Primary_Color_TextView_style))
                .setMessage(getSpanningString(message, R.style.Regular_Default_TextView_style))
                .setColoredCircle(R.color.purple_500)
                .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
                .setCancelable(cancelable)
                .setPositiveButtonText(positive_button_message)
                .setPositiveButtonbackgroundColor(R.color.purple_500)
                .setPositiveButtonTextColor(R.color.white)
                .setNegativeButtonText(negative_text_message)
                .setNegativeButtonbackgroundColor(R.color.purple_500)
                .setNegativeButtonTextColor(R.color.white)
                .setPositiveButtonClick(positive_button_click)
                .setNegativeButtonClick(negative_button_click);

        return awesomeInfoDialog;
    }


    public AwesomeInfoDialog awesomeInfoWithOneButtonsDialog(String title, String message, String positive_button_message,
                                                      Closure positive_button_click, boolean cancelable){

        awesomeInfoDialog =  new AwesomeInfoDialog(context)
                .setTitle(getSpanningString(title, R.style.Bold_Default_TextView_style))
                .setMessage(getSpanningString(message, R.style.Regular_Default_TextView_style))
                .setColoredCircle(R.color.purple_500)
                .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
                .setCancelable(cancelable)
                .setPositiveButtonText(positive_button_message)
                .setPositiveButtonbackgroundColor(R.color.purple_500)
                .setPositiveButtonTextColor(R.color.white)
                .setPositiveButtonClick(positive_button_click);

        return awesomeInfoDialog;
    }


    public AwesomeProgressDialog awesomeProgressDialog(String title, String message, boolean cancelable){

        awesomeProgressDialog =  new AwesomeProgressDialog(context)
                .setTitle(getSpanningString(title, R.style.Bold_Default_TextView_style))
                .setMessage(getSpanningString(message, R.style.Regular_Default_TextView_style))
                .setColoredCircle(R.color.purple_500)
                .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
                .setCancelable(cancelable);

        return awesomeProgressDialog;
    }


    public AwesomeSuccessDialog awesomeSuccessDialog(String message, boolean cancelable){

         awesomeSuccessDialog =  new AwesomeSuccessDialog(context);
                awesomeSuccessDialog.setTitle(context.getResources().getString(R.string.succ))
                .setMessage(getSpanningString(message, R.style.Regular_Default_TextView_style))
                .setColoredCircle(R.color.dialogSuccessBackgroundColor)
                .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
                .setPositiveButtonText(context.getResources().getString(R.string.dialog_ok_button))
                .setPositiveButtonbackgroundColor(R.color.dialogSuccessBackgroundColor)
                .setPositiveButtonTextColor(R.color.white)
                .setCancelable(cancelable).setPositiveButtonClick(new Closure() {
                    @Override
                    public void exec() {

                        awesomeSuccessDialog.hide();

                    }
                });

        return awesomeSuccessDialog;
    }


    public AwesomeErrorDialog awesomeErrorDialog(String message,boolean cancelable) {

        awesomeErrorDialog =  new AwesomeErrorDialog(context);
                awesomeErrorDialog.setTitle(context.getResources().getString(R.string.error))
                .setMessage(getSpanningString(message, R.style.Regular_Default_TextView_style))
                .setColoredCircle(R.color.dialogErrorBackgroundColor)
                .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                .setCancelable(cancelable)
                .setButtonBackgroundColor(R.color.dialogErrorBackgroundColor)
                .setButtonText(context.getResources().getString(R.string.dialog_ok_button))
                .setButtonTextColor(R.color.white)
                .setErrorButtonClick(new Closure() {
                    @Override
                    public void exec() {

                        awesomeErrorDialog.hide();
                    }
                });

                return awesomeErrorDialog;
    }


    public AwesomeErrorDialog awesomeErrorDialogWithCustomButton(String message, String buttonText, Closure closure , boolean cancelable) {

        awesomeErrorDialog =  new AwesomeErrorDialog(context);
        awesomeErrorDialog.setTitle(context.getResources().getString(R.string.error))
                .setMessage(getSpanningString(message, R.style.Regular_Default_TextView_style))
                .setColoredCircle(R.color.dialogErrorBackgroundColor)
                .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                .setCancelable(cancelable)
                .setButtonBackgroundColor(R.color.dialogErrorBackgroundColor)
                .setButtonText(buttonText)
                .setButtonTextColor(R.color.white)
                .setErrorButtonClick(closure);

        return awesomeErrorDialog;
    }


    public void hideInfoDialogWithTwoButton(){

        if (awesomeInfoDialog!=null) awesomeInfoDialog.hide();
    }

    public void hideProgress(){

        if (awesomeProgressDialog!=null) awesomeProgressDialog.hide();
    }

    public void hideErrorDialog(){

        if (awesomeErrorDialog!=null) awesomeErrorDialog.hide();
    }


    private SpannableString getSpanningString(String s, int style){

        SpannableString span = new SpannableString(s);

        span.setSpan(new TextAppearanceSpan(context, style), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return span;
    }
}
