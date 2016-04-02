package com.layemut.fieldcontrolledclicklistener.utils;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * @author Özcan Candağ - ozcancandag@gmail.com
 */
public class Utils {

    /**
     * Shows snackbar for notice purposes, accepts string message.
     *
     * @param context
     * @param message
     */
    public static void showSnack(AppCompatActivity context, String message) {
        Snackbar.make(context.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Shows toast message for notice purposes, accepts string message
     *
     * @param context
     * @param message
     */
    public static void showToast(AppCompatActivity context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
