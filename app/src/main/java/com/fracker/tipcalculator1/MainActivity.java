package com.fracker.tipcalculator1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private double billAmount = 0.0;
    private double percent  = 0.15;
    private TextView amountTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private TextView percentTextView;

    private TextWatcher textWatcher  = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
           try {
               billAmount = Double.parseDouble(s.toString());
               amountTextView.setText(currencyFormat.format(billAmount));
           }catch (NumberFormatException ex){
               amountTextView.setText("");
               billAmount = 0.00;
           }

            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    } ;

    private void calculate() {

        percentTextView.setText(percentFormat.format(percent));

        double tip = billAmount * percent;
        double total = billAmount + tip;
        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));


    }

    private SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            percent = progress/100.00;
            calculate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountTextView = (TextView) findViewById(R.id.amountTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);

        totalTextView.setText(currencyFormat.format(0));
        tipTextView.setText(percentFormat.format(0));

        percentTextView = (TextView) findViewById(R.id.percentTextView);

         EditText amountEditTextView = (EditText) findViewById(R.id.amoutEditText);
          amountEditTextView.addTextChangedListener(textWatcher);

         SeekBar percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);
         percentSeekBar.setOnSeekBarChangeListener(seekBarListener);
    }



}
