package com.layemut.fieldcontrolledclicklistener.models;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * @author Özcan Candağ - ozcancandag@gmail.com.
 */
public class Controlee {

    /**
     * Candidate view to be controlled
     */
    private View view;

    /**
     * If operation fails when controlling {@link CheckBox}
     * should the error message be displayed on {@link android.support.design.widget.Snackbar}?
     * on default, it is displayed on {@link android.widget.Toast}
     */
    private Boolean showSnackBar;

    /**
     * if control fails, what error message should be displayed
     */
    private String errorMessage;

    public View getView() {
        return view;
    }

    public Controlee withView(View view) {
        if (view instanceof EditText ||
                view instanceof Spinner ||
                view instanceof CheckBox) {
            this.view = view;
        } else {
            throw new IllegalArgumentException("View can only be EditText, Spinner or Checkbox.");
        }
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Controlee withErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public Controlee showSnackBar() {
        this.showSnackBar = true;
        return this;
    }

    public boolean getShowSnackBar() {
        return showSnackBar != null;
    }
}
