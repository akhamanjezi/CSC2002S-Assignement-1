package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CalculatorActivity extends AppCompatActivity {

    ArrayList<DiaryEntry> entryArrayList;
    DiaryEntry newEntry;
    int currentIndex = -1;
    EditText breakfastInput;
    EditText lunchInput;
    EditText dinnerInput;
    EditText snacksInput;
    EditText weightliftingInput;
    EditText cardioInput;
    EditText mixedInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        loadData();

         breakfastInput = findViewById(R.id.breakfastTextInput);
         lunchInput = findViewById(R.id.lunchTextInput);
         dinnerInput = findViewById(R.id.dinnerTextInput);
         snacksInput = findViewById(R.id.snacksTextInput);
         weightliftingInput = findViewById(R.id.weightliftingTextInput);
         cardioInput = findViewById(R.id.cardioTextInput);
         mixedInput = findViewById(R.id.mixedTextInput);

        Button buttonSave = findViewById(R.id.saveButton);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentIndex != -1){
                    entryArrayList.set(currentIndex,newEntry);
                }else{
                    entryArrayList.add(newEntry);
                    currentIndex = entryArrayList.size()-1;
                }
                saveData();
                Intent viewEntry = new Intent(getApplicationContext(), EntryActivity.class);
                viewEntry.putExtra("Uniqid", "From_calculator");
                viewEntry.putExtra("index", (currentIndex)+"");

                startActivity(viewEntry);
                finish();
            }
        });

        Intent intent = this.getIntent();
        if(intent.getExtras().getString("Uniqid").equals("From_diary")){
            currentIndex = Integer.parseInt(intent.getExtras().getString("index"));
            newEntry = entryArrayList.get(currentIndex);
            edit();
            updateTotals();
        }else{
            newEntry = new DiaryEntry();
        }

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateEntry();
                updateTotals();

            }
        };

        breakfastInput.addTextChangedListener(textWatcher);
        lunchInput.addTextChangedListener(textWatcher);
        dinnerInput.addTextChangedListener(textWatcher);
        snacksInput.addTextChangedListener(textWatcher);
        weightliftingInput.addTextChangedListener(textWatcher);
        cardioInput.addTextChangedListener(textWatcher);
        mixedInput.addTextChangedListener(textWatcher);

    }

    private void updateEntry(){
        EditText breakfastText = findViewById(R.id.breakfastTextInput);
        String breafastKJ = (breakfastText.getText().length() != 0) ? breakfastText.getText().toString() : 0 + "";
        newEntry.setBreakfast(breafastKJ);
        EditText lunchText = findViewById(R.id.lunchTextInput);
        String lunchKJ = (lunchText.getText().length() != 0) ? lunchText.getText().toString() : 0 + "";
        newEntry.setLunch(lunchKJ);
        EditText dinnerText = findViewById(R.id.dinnerTextInput);
        String dinnerKJ = (dinnerText.getText().length() != 0) ? dinnerText.getText().toString() : 0 + "";
        newEntry.setDinner(dinnerKJ);
        EditText snacksText = findViewById(R.id.snacksTextInput);
        String snacksKJ = (snacksText.getText().length() != 0) ? snacksText.getText().toString() : 0 + "";
        newEntry.setSnacks(snacksKJ);
        EditText weightliftingText = findViewById(R.id.weightliftingTextInput);
        String weightliftingKJ = (weightliftingText.getText().length() != 0) ? weightliftingText.getText().toString() : 0 + "";
        newEntry.setWeightlifting(weightliftingKJ);
        EditText cardioText = findViewById(R.id.cardioTextInput);
        String cardioKJ = (cardioText.getText().length() != 0) ? cardioText.getText().toString() : 0 + "";
        newEntry.setCardio(cardioKJ);
        EditText mixedText = findViewById(R.id.mixedTextInput);
        String mixedKJ = (mixedText.getText().length() != 0) ? mixedText.getText().toString() : 0 + "";
        newEntry.setMixed(mixedKJ);

    }

    private void updateTotals(){
        ((TextView) findViewById(R.id.foodTotalValueView)).setText(newEntry.getFoodKJ());
        ((TextView) findViewById(R.id.exerciseTotalValueView)).setText(newEntry.getExerciseKJ());
        ((TextView) findViewById(R.id.foodTotalNKIView)).setText(newEntry.getFoodKJ());
        ((TextView) findViewById(R.id.exerciseTotalNKIView)).setText(newEntry.getExerciseKJ());
        ((TextView) findViewById(R.id.NKITotalView)).setText(newEntry.getNKI());

    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(entryArrayList);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<DiaryEntry>>() {}.getType();
        entryArrayList = gson.fromJson(json, type);

        if (entryArrayList == null) {
            entryArrayList = new ArrayList<>();
        }
    }

    private void edit() {

        breakfastInput.setText(newEntry.getBreakfast());
        lunchInput.setText(newEntry.getLunch());
        dinnerInput.setText(newEntry.getDinner());
        snacksInput.setText(newEntry.getSnacks());
        weightliftingInput.setText(newEntry.getWeightlifting());
        cardioInput.setText(newEntry.getCardio());
        mixedInput.setText(newEntry.getMixed());
    }

}
