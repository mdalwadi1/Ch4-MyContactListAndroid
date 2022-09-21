package com.example.ch4_mycontactlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

//DatePickerDialog is subclass of DialogFragment
public class DatePickerDialog extends DialogFragment {

    //Class variable declared to hold selected date
    Calendar selectedDate;

    //Listener that communicates user's actions from dialog to activity
    public interface SaveDateListener {
        void didFinishDatePickerDialog(Calendar selectedTime);
    }

    public DatePickerDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    //LayoutInflator = instantiates layout XML file into corresponding View objects
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.select_date, container);

        getDialog().setTitle("Select Date");
        selectedDate = Calendar.getInstance();

        final CalendarView cv = view.findViewById(R.id.calendarView);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //Calendar objects hold dates and times as numbr of milliseconds from Jan 1, 1970
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                selectedDate.set(year, month, day);
            }
        });

        Button saveButton = view.findViewById(R.id.buttonSelect);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //On click, it sends the last selected date to saveItem method
                saveItem(selectedDate);
            }
        });

        Button cancelButton = view.findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return view;
    }

    private void saveItem(Calendar selectedTime) {
        SaveDateListener activity = (SaveDateListener) getActivity();
        activity.didFinishDatePickerDialog(selectedTime);
        getDialog().dismiss();
    }
}