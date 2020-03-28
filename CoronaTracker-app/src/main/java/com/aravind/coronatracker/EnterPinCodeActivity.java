package com.aravind.coronatracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class EnterPinCodeActivity extends AppCompatActivity {

    EditText pinCode;

    public static void showSnackBar(View view, String Message) {
        Snackbar.make(view, Message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pincode);
        Button btn = findViewById(R.id.submit);
        pinCode = findViewById(R.id.pincode);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(pinCode.getText().toString().length() < 6 || (pinCode.getText().toString().equals("") || (pinCode.getText().toString() == "") || (pinCode.getText().toString().isEmpty())))) {
                    hideSoftKeyboard();
                    Intent intent = new Intent(EnterPinCodeActivity.this, QuarantineResultActivity.class);
                    intent.putExtra("PINCODE", pinCode.getText().toString());
                    startActivity(intent);
                } else {
                    hideSoftKeyboard();
                    showSnackBar(findViewById(R.id.layoutMain), "Incorrect Pincode");
                }
            }
        });
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * Shows the soft keyboard
     */
    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }
}
