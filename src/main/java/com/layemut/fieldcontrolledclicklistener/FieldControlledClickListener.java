package com.layemut.fieldcontrolledclicklistener;


import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.layemut.fieldcontrolledclicklistener.models.Controlee;
import com.layemut.fieldcontrolledclicklistener.utils.Utils;


/**
 * Controls the given controlees regarding their view types,
 * If control fails sets appropriate warning messages else
 * calls {@link ControlListener#onControlSuccess()}
 *
 * @author Özcan Candağ - ozcancandag@gmail.com
 * @version 0.1
 * @since 0.1
 */
public class FieldControlledClickListener implements View.OnClickListener {

    /**
     * It is called if control operation succeeds
     */
    public interface ControlListener {
        void onControlSuccess();
    }

    AppCompatActivity context;
    Controlee[] controlees;
    ControlListener controlListener;

    public FieldControlledClickListener(AppCompatActivity context, Controlee... controlees) {
        this.context = context;
        this.controlees = controlees;
    }

    public FieldControlledClickListener setControlListener(ControlListener controlListener) {
        this.controlListener = controlListener;
        return this;
    }

    @Override
    public void onClick(View v) {
        if (controlListener == null) {
            throw new NullPointerException("Set a ControlListener so you can know operation succeeds.");
        }

        boolean success = true;
        for (Controlee controlee : controlees) {
            success = controlControlee(controlee);
        }

        if (success) {
            controlListener.onControlSuccess();
        }
    }

    /**
     * Chooses the control method regarding {@link Controlee#view}
     *
     * @param controlee
     * @return
     */
    private boolean controlControlee(Controlee controlee) {
        if (controlee.getView() instanceof EditText) {
            return !isEmpty(controlee);
        } else if (controlee.getView() instanceof Spinner) {
            return isSelected(controlee);
        } else if (controlee.getView() instanceof CheckBox) {
            return isChecked(controlee);
        }

        throw new IllegalArgumentException("How the hell could you manage to throw this exception?");
    }

    /**
     * This method is called when the view is {@link EditText} controlling
     * if it is empty.
     * <p/>
     * <p/>
     * If EditText in a TextInputLayout the error message is set to TextInputLayout
     * else the error message is set to EditText itself.
     *
     * @param controlee
     * @return true if EditText in controlee is empty, else false
     */
    private boolean isEmpty(Controlee controlee) {
        boolean isEmpty = ((EditText) controlee.getView()).getText().toString().isEmpty();

        if (isEmpty) {
            try {
                ((TextInputLayout) controlee.getView().getParent()).setErrorEnabled(true);
                ((TextInputLayout) controlee.getView().getParent()).setError(controlee.getErrorMessage());
            } catch (Throwable t) {
                ((EditText) controlee.getView()).setError(controlee.getErrorMessage());
            }
        }

        return isEmpty;
    }

    /**
     * This method is called when the view is {@link Spinner} controlling
     * if it's first row is selected.
     *
     * @param controlee
     * @return true if spinner's first row is NOT selected, else false
     */
    private boolean isSelected(Controlee controlee) {
        boolean isSelected = ((Spinner) controlee.getView()).getSelectedItemPosition() != 0;

        if (!isSelected) {
            Spinner spinner = (Spinner) controlee.getView();
            TextView errorText = (TextView) spinner.getSelectedView();
            errorText.setError(null);
            errorText.setTextColor(Color.rgb(213, 0, 0));
            errorText.setText(controlee.getErrorMessage());
            errorText.setTextSize(12);
        }

        return isSelected;
    }

    /**
     * This method is called when the view is {@link CheckBox} controlling
     * if it is checked.
     *
     * @param controlee
     * @return true if CheckBox is checked, else false
     */
    private boolean isChecked(Controlee controlee) {
        boolean isChecked = ((CheckBox) controlee.getView()).isChecked();

        if (!isChecked) {
            if (controlee.getShowSnackBar()) {
                Utils.showSnack(context, controlee.getErrorMessage());
            } else {
                Utils.showToast(context, controlee.getErrorMessage());
            }
        }

        return isChecked;
    }
}
