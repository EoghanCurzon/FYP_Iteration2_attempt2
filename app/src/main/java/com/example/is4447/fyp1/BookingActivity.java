
package com.example.is4447.fyp1;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper myDb;

    EditText bookingEmail;
    TextView bookingCost;
    Button btnConfirmBooking;

    private static final String TAG = "BookingActivity";
    private TextView mDisplayDate;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView selectedTimeSlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        myDb = new DatabaseHelper(this);

        bookingCost = (TextView) findViewById(R.id.tvCost);
        bookingEmail = (EditText) findViewById(R.id.etEmail);
        btnConfirmBooking = (Button) findViewById(R.id.btnConfirmBooking);

        addBookingData();

        mDisplayDate = (TextView) findViewById(R.id.tvDatePicker);

        //passing cost amount from previous activity using intent, learnt in IS4447
        if (getIntent().hasExtra("PASSINGCOST")){
            TextView tvCost = (TextView) findViewById(R.id.tvCost);
            String passedCost = getIntent().getExtras().getString("PASSINGCOST");
            tvCost.setText(passedCost);
        }

        /* code for the timeslot spinner taken from YouTube video https://www.youtube.com/watch?v=on_OrrX7Nw4 */
        Spinner timeSlotSpinner = findViewById(R.id.spTimeSlot);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.timeslots, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSlotSpinner.setAdapter(adapter);
        timeSlotSpinner.setOnItemSelectedListener(this);

        selectedTimeSlot = (TextView) findViewById(R.id.tvSelectedTimeSlot);


        /* Code for the picking the date taken from YouTube video https://www.youtube.com/watch?v=hwe1abDO2Ag */
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        BookingActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        /* Initialise onDateSetListener Object (https://www.youtube.com/watch?v=hwe1abDO2Ag */
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: yyyy/mm/dd: " + year + "/" + month + "/" + dayOfMonth);

                String date = year + "/" + month + "/" + dayOfMonth;
                mDisplayDate.setText(date);
                Toast.makeText(BookingActivity.this, "Date selected = " + date, Toast.LENGTH_SHORT).show();

            }
        };

    }

    /* Spinner code from https://www.youtube.com/watch?v=on_OrrX7Nw4 */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String timeslot = parent.getItemAtPosition(position).toString();
        selectedTimeSlot.setText(timeslot);
        Toast.makeText(this, "Time selected = " + timeslot, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Toast.makeText(this, "Please select a timeslot", Toast.LENGTH_SHORT).show();

    }

/* code for inserting data into database from https://www.youtube.com/watch?v=kDZES1wtKUY&t=1511s */
    public void addBookingData() {
        btnConfirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             boolean isInserted = myDb.insertBookingData(bookingEmail.getText().toString(), mDisplayDate.getText().toString(), selectedTimeSlot.getText().toString(), bookingCost.getText().toString());
             if (isInserted = true)
                 Toast.makeText(BookingActivity.this, "Booking confirmed!", Toast.LENGTH_SHORT).show();
             else
                 Toast.makeText(BookingActivity.this, "Error confirming booking.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}