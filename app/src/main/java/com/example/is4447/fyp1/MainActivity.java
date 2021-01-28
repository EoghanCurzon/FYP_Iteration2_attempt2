package com.example.is4447.fyp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView result, domcost, foodcost;
    int domesticCost = 0;
    int foodCostFig = 0;
    int cost = 3;
    CheckBox recyclingCheck;
    Button btnClear, btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /* Code for spinners taken from YouTube video https://www.youtube.com/watch?v=on_OrrX7Nw4 */
        Spinner domesticSpinner = findViewById(R.id.spDomestic);
        ArrayAdapter<CharSequence> domesticAdapter = ArrayAdapter.createFromResource(this, R.array.domestic, android.R.layout.simple_spinner_item);
        domesticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        domesticSpinner.setAdapter(domesticAdapter);
        domesticSpinner.setOnItemSelectedListener(this);

        Spinner foodSpinner = findViewById(R.id.spFood);
        ArrayAdapter<CharSequence> foodAdapter = ArrayAdapter.createFromResource(this, R.array.food, android.R.layout.simple_spinner_item);
        foodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodSpinner.setAdapter(foodAdapter);
        foodSpinner.setOnItemSelectedListener(this);

        result = findViewById(R.id.tvCost);
        domcost = findViewById(R.id.tvDomCostFig);
        foodcost = findViewById(R.id.tvFoodCostFig);
        recyclingCheck = findViewById(R.id.cbxRecyling);
        btnClear = findViewById(R.id.btnClear);
        btnConfirm = findViewById(R.id.btnConfirm1);

        //clear button code
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("€0");
                cost = 3;
                domesticCost = 0;
                foodCostFig = 0;
                domcost.setText("€0");
                foodcost.setText("€0");
            }
        });

        /* Code for intents and passing data learnt in IS4447 Module */
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result.getText().equals("0")) {
                    Toast.makeText(MainActivity.this, "Please input a waste amount", Toast.LENGTH_SHORT).show();
                } else {
                    //passing the user's inputted cost to the next activity, and moving on to that activity.
                    Intent confirmIntent = new Intent(getApplicationContext(), BookingActivity.class);
                    confirmIntent.putExtra("PASSINGCOST", result.getText().toString());
                    startActivity(confirmIntent);
                }
            }
        });

        /* code for checkbox method from https://developer.android.com/reference/android/widget/CompoundButton.OnCheckedChangeListener */
        recyclingCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               //when recycling checkbox is checked, cost increases by €2
                if (recyclingCheck.isChecked()) {
                    cost = cost + 2;
                    result.setText("€" + String.valueOf(cost));
                }
            }
        });
    }

    //switch case statement for the different spinners to add to cost variable depending on item selected in spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.spDomestic:
                if (parent.getItemAtPosition(position).equals("Standard waste bag - €4")) {
                    cost = cost + 4;
                    domesticCost = domesticCost + 4;
                    domcost.setText("€" + String.valueOf(domesticCost));
                    result.setText("€" + String.valueOf(cost));
                    Toast.makeText(parent.getContext(), "added!", Toast.LENGTH_SHORT).show();
                }

                if (parent.getItemAtPosition(position).equals("Large waste bag - €8")) {
                    cost = cost + 8;
                    domesticCost = domesticCost + 8;
                    domcost.setText("€" + String.valueOf(domesticCost));
                    result.setText("€" + String.valueOf(cost));
                    Toast.makeText(parent.getContext(), "added!", Toast.LENGTH_SHORT).show();
                }

                if (parent.getItemAtPosition(position).equals("Wheelie bin - €12")) {
                    cost = cost + 12;
                    domesticCost = domesticCost + 12;
                    domcost.setText("€" + String.valueOf(domesticCost));
                    result.setText("€" + String.valueOf(cost));
                    Toast.makeText(parent.getContext(), "added!", Toast.LENGTH_SHORT).show();
                }

                if (parent.getItemAtPosition(position).equals("Single mattress - €10")) {
                    cost = cost + 10;
                    domesticCost = domesticCost + 10;
                    domcost.setText("€" + String.valueOf(domesticCost));
                    result.setText("€" + String.valueOf(cost));
                    Toast.makeText(parent.getContext(), "added!", Toast.LENGTH_SHORT).show();
                }

                if (parent.getItemAtPosition(position).equals("Double mattress - €20")) {
                    cost = cost + 20;
                    domesticCost = domesticCost + 20;
                    domcost.setText("€" + String.valueOf(domesticCost));
                    result.setText("€" + String.valueOf(cost));
                    Toast.makeText(parent.getContext(), "added!", Toast.LENGTH_SHORT).show();
                }

                if (parent.getItemAtPosition(position).equals("Single armchair - €10")) {
                    cost = cost + 10;
                    domesticCost = domesticCost + 10;
                    domcost.setText("€" + String.valueOf(domesticCost));
                    result.setText("€" + String.valueOf(cost));
                    Toast.makeText(parent.getContext(), "added!", Toast.LENGTH_SHORT).show();
                }

                if (parent.getItemAtPosition(position).equals("Double sofa/settee - €15")) {
                    cost = cost + 15;
                    domesticCost = domesticCost + 15;
                    domcost.setText("€" + String.valueOf(domesticCost));
                    result.setText("€" + String.valueOf(cost));
                    Toast.makeText(parent.getContext(), "added!", Toast.LENGTH_SHORT).show();
                }

                if (parent.getItemAtPosition(position).equals("3 Seater sofa/settee - €20")) {
                    cost = cost + 20;
                    domesticCost = domesticCost + 20;
                    domcost.setText("€" + String.valueOf(domesticCost));
                    result.setText("€" + String.valueOf(cost));
                    Toast.makeText(parent.getContext(), "added!", Toast.LENGTH_SHORT).show();
                }

                if (parent.getItemAtPosition(position).equals("Carpet (Standard 12ft x 12ft) - €15")) {
                    cost = cost + 15;
                    domesticCost = domesticCost + 15;
                    domcost.setText("€" + String.valueOf(domesticCost));
                    result.setText("€" + String.valueOf(cost));
                    Toast.makeText(parent.getContext(), "added!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.spFood:
                if (parent.getItemAtPosition(position).equals("7 Litre kitchen caddy - €1")) {
                    cost = cost + 1;
                    foodCostFig = foodCostFig + 1;
                    foodcost.setText("€" + String.valueOf(foodCostFig));
                    result.setText("€" + String.valueOf(cost));
                    Toast.makeText(parent.getContext(), "added!", Toast.LENGTH_SHORT).show();
                }

                if (parent.getItemAtPosition(position).equals("12 Litre kitchen caddy - €2")) {
                    cost = cost + 2;
                    foodCostFig = foodCostFig + 2;
                    foodcost.setText("€" + String.valueOf(foodCostFig));
                    result.setText("€" + String.valueOf(cost));
                    Toast.makeText(parent.getContext(), "added!", Toast.LENGTH_SHORT).show();
                }

                if (parent.getItemAtPosition(position).equals("25 Litre kitchen caddy - €4")) {
                    cost = cost + 4;
                    foodCostFig = foodCostFig + 4;
                    foodcost.setText("€" + String.valueOf(foodCostFig));
                    result.setText("€" + String.valueOf(cost));
                    Toast.makeText(parent.getContext(), "added!", Toast.LENGTH_SHORT).show();
                }

                if (parent.getItemAtPosition(position).equals("1-3 Bags of green waste - €5")) {
                    cost = cost + 5;
                    foodCostFig = foodCostFig + 5;
                    foodcost.setText("€" + String.valueOf(foodCostFig));
                    result.setText("€" + String.valueOf(cost));
                    Toast.makeText(parent.getContext(), "added!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}