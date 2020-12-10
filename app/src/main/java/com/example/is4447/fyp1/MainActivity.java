package com.example.is4447.fyp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAdd, btnClear, btnConfirm1;
    EditText StaRefAmount, LarRefAmount, WheBinAmount, SevenLitreAmount, TwelveLitreAmount, TwentyFiveLitreAmount;
    TextView FinalAmount, staRefSummary, larRefSummary, wheBinSummary, sevLitSummary, twelLitSummary, twefiLitSummary;
    CheckBox recyclingCheck;
    int staRef, larRef, wheBin, sevLit, twelLit, twefiLit, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        btnConfirm1 = findViewById(R.id.btnConfirm1);
        StaRefAmount = findViewById(R.id.etStaRefBag);
        LarRefAmount = findViewById(R.id.etLarRefBag);
        WheBinAmount = findViewById(R.id.etWheBin);
        SevenLitreAmount = findViewById(R.id.et7Litre);
        TwelveLitreAmount = findViewById(R.id.et12Litre);
        TwentyFiveLitreAmount = findViewById(R.id.et25Litre);
        recyclingCheck = findViewById(R.id.cbxRecycling);
        FinalAmount = findViewById(R.id.tvTotalCostResult);
        staRefSummary = findViewById(R.id.tvStaRefSummary);
        larRefSummary = findViewById(R.id.tvLarRefSummary);
        wheBinSummary = findViewById(R.id.tvWheBinSummary);
        sevLitSummary = findViewById(R.id.tv7LitSummary);
        twelLitSummary = findViewById(R.id.tv12LitSummary);
        twefiLitSummary = findViewById(R.id.tv25LitSummary);

        btnAdd.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnConfirm1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        staRef = Integer.parseInt(StaRefAmount.getText().toString());
        larRef = Integer.parseInt(LarRefAmount.getText().toString());
        wheBin = Integer.parseInt(WheBinAmount.getText().toString());
        sevLit = Integer.parseInt(SevenLitreAmount.getText().toString());
        twelLit = Integer.parseInt(TwelveLitreAmount.getText().toString());
        twefiLit = Integer.parseInt(TwentyFiveLitreAmount.getText().toString());

        switch (v.getId()) {

            case R.id.btnAdd:
            result = (staRef*4) + (larRef*8) + (wheBin*12) + sevLit + (twelLit*2) + (twefiLit*4);
            if(recyclingCheck.isChecked()) {
            result = result + 2;
            }
            FinalAmount.setText("€" + String.valueOf(result));
            if(StaRefAmount.getText().toString() != "0") {

                staRefSummary.setText(staRef + " x Standard Refuse Bag");
            }
                if(LarRefAmount.getText().toString() != "0") {
                    larRefSummary.setText(larRef + " x Large Refuse Bag");
                }
                if(WheBinAmount.getText().toString() != "0") {
                    wheBinSummary.setText(wheBin + " x Wheelie Bin");
                }
                if(SevenLitreAmount.getText().toString() != "0") {
                    sevLitSummary.setText(sevLit + " x 7 Litre Kitchen Caddy");
                }
                if(TwelveLitreAmount.getText().toString() != "0") {
                    twelLitSummary.setText(twelLit + " x 12 Litre Kitchen Caddy");
                }
                if(TwentyFiveLitreAmount.getText().toString() != "0") {
                    twefiLitSummary.setText(twefiLit + " x 25 Litre Kitchen Caddy");
                }
            break;

            case R.id.btnClear:
                StaRefAmount.setText("0");
                LarRefAmount.setText("0");
                WheBinAmount.setText("0");
                SevenLitreAmount.setText("0");
                TwelveLitreAmount.setText("0");
                TwentyFiveLitreAmount.setText("0");
                FinalAmount.setText("0");
                staRefSummary.setText("0 x Standard Refuse Bag");
                larRefSummary.setText("0 x Large Refuse Bag");
                wheBinSummary.setText("0 x Wheelie Bin");
                sevLitSummary.setText("0 x 7 Litre Kitchen Caddy");
                twelLitSummary.setText("0 x 12 Litre Kitchen Caddy");
                twefiLitSummary.setText("0 x 25 Litre Kitchen Caddy");
                break;

            case R.id.btnConfirm1:
                if (FinalAmount.getText().equals("0")){
                    Toast.makeText(this, "Please input a waste amount", Toast.LENGTH_SHORT).show();
                    StaRefAmount.requestFocus();
                }
                else {
                    Intent confirmIntent = new Intent(getApplicationContext(), BookingActivity.class);
                    confirmIntent.putExtra("PASSINGCOST", FinalAmount.getText().toString());
                    startActivity(confirmIntent);
                }


        }
    }
}