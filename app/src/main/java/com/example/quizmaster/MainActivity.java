package com.example.quizmaster;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //stating variables here
    int results = 0;
    CheckBox q1Answer1Checkbox;
    CheckBox q1Answer2Checkbox;
    CheckBox q1Incorrect1Checkbox;
    CheckBox q1Incorrect2Checkbox;
    CheckBox q2Answer1Checkbox;
    CheckBox q2Answer2Checkbox;
    CheckBox q2Incorrect1Checkbox;
    CheckBox q2Incorrect2Checkbox;
    RadioGroup q4RadioGroup;
    RadioButton q4ARadiobutton;
    RadioButton q4BRadiobutton;
    EditText q3EditText;
    Button submitClick;
    Button retakeClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //defining variables here with ids
        q1Answer1Checkbox = (CheckBox) findViewById(R.id.checkbox_choice1a);
        q1Answer2Checkbox = (CheckBox) findViewById(R.id.checkbox_choice1b);
        q1Incorrect1Checkbox = (CheckBox) findViewById(R.id.checkbox_choice1c);
        q1Incorrect2Checkbox = (CheckBox) findViewById(R.id.checkbox_choice1d);

        q2Answer1Checkbox = (CheckBox) findViewById(R.id.checkbox_choice2a);
        q2Answer2Checkbox = (CheckBox) findViewById(R.id.checkbox_choice2b);
        q2Incorrect1Checkbox = (CheckBox) findViewById(R.id.checkbox_choice2c);
        q2Incorrect2Checkbox = (CheckBox) findViewById(R.id.checkbox_choice2d);

        q4RadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        q4ARadiobutton = (RadioButton) findViewById(R.id.radiobutton_4a);
        q4BRadiobutton = (RadioButton) findViewById(R.id.radiobutton_4b);
        q3EditText = (EditText) findViewById(R.id.entertext);

        submitClick = (Button) findViewById(R.id.Submit);
        retakeClick = (Button) findViewById(R.id.Retake);
    }

    //set results to a string that can be changed
    private void displayResults(int score) {
        TextView scoreView = findViewById(R.id.Results);
        scoreView.setText(String.valueOf(score));
    }

    //resets score and clears all fields
    private void resetScore() {
        results = 0;
        displayResults(results);
        q1Answer1Checkbox.setChecked(false);
        q1Answer2Checkbox.setChecked(false);
        q1Incorrect1Checkbox.setChecked(false);
        q1Incorrect2Checkbox.setChecked(false);
        q2Answer1Checkbox.setChecked(false);
        q2Answer2Checkbox.setChecked(false);
        q2Incorrect1Checkbox.setChecked(false);
        q2Incorrect2Checkbox.setChecked(false);
        q3EditText.setText("");
        q4RadioGroup.clearCheck();
        q4ARadiobutton.setChecked(false);
        q4BRadiobutton.setChecked(false);
    }

    //Validates checkbox answers to only add to score if both answers are selected
    private void complete() {
        if (q1Answer1Checkbox.isChecked() && q1Answer2Checkbox.isChecked() && !q1Incorrect1Checkbox.isChecked() && !q1Incorrect2Checkbox.isChecked()) { //Here the variables are referenced, it states if only these two are checked then add 25 to total score
            results = results + 25;
        }

        // Question 2 Checkbox
        if (q2Answer1Checkbox.isChecked() && q2Answer2Checkbox.isChecked() && !q2Incorrect1Checkbox.isChecked() && !q2Incorrect2Checkbox.isChecked()) { //Here the variables are referenced, it states if only these two are checked then add 25 to total score
            results = results + 25;
        }

        //Validates if edittext equals Test (caps or no caps) then add to score
        String Question3String = q3EditText.getText().toString().trim();
        String Question3String2 = q3EditText.getText().toString().trim();
        if (Question3String.equalsIgnoreCase("Test")) {
            results = results + 25;
        } else if (Question3String2.equalsIgnoreCase("Test2")) {
            results = results + 25;
        }

        // Validates if radiobutton answer is pressed then it adds to score else it does not

        if (q4ARadiobutton.isChecked()) {
            results = results + 25;
        }
    }

    //Toast message to display incorrect answers
    private void checkErrors() {
        if (results == 100) return;
        String errors = "";
        if (!q1Answer1Checkbox.isChecked() || !q1Answer2Checkbox.isChecked() || q1Incorrect1Checkbox.isChecked() || q1Incorrect2Checkbox.isChecked()) {
            errors += "1 ";
        }

        if (!q2Answer1Checkbox.isChecked() || !q2Answer2Checkbox.isChecked() || q2Incorrect1Checkbox.isChecked() || q2Incorrect2Checkbox.isChecked()) {
            errors += "2 ";
        }

        String Question3String = q3EditText.getText().toString().trim();
        if (!((Question3String.equalsIgnoreCase("Test")) || (Question3String.equalsIgnoreCase("Test2")))) {
            errors += "3 ";
        }

        if (!q4ARadiobutton.isChecked()) {
            errors += "4 ";
        }
        String message = "Your answer was incorrect in questions: " + errors;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    //When complete button is clicked, it runs the complete method, displays the toast, displays the results and transforms to the retake button
    public void submitClick(View v) {
        submitClick.setVisibility(View.GONE);
        retakeClick.setVisibility(View.VISIBLE);
        complete();
        checkErrors();
        displayResults(results);
    }

    //When retake button is clicked, it changes to complete button and clears all fields
    public void retakeClick(View t) {
        submitClick.setVisibility(View.VISIBLE);
        retakeClick.setVisibility(View.GONE);
        resetScore();
    }
}