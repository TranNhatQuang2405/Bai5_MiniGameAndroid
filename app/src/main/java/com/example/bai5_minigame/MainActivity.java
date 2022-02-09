package com.example.bai5_minigame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textViewScore;
    ImageButton imageButtonStart;
    CheckBox checkBoxOne, checkBoxTwo, checkBoxThree;
    SeekBar seekBarOne, seekBarTwo, seekBarThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ánh Xạ
        AnhXa();

        //CheckBox
        checkBoxOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    checkBoxTwo.setChecked(false);
                    checkBoxThree.setChecked(false);
                }
            }
        });

        checkBoxTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    checkBoxOne.setChecked(false);
                    checkBoxThree.setChecked(false);
                }
            }
        });
        checkBoxThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    checkBoxOne.setChecked(false);
                    checkBoxTwo.setChecked(false);
                }
            }
        });

        //Main game
        CountDownTimer countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long l) {
                int number = 5;
                Random random = new Random();
                int num1 = random.nextInt(number);
                int num2 = random.nextInt(number);
                int num3 = random.nextInt(number);
                if(seekBarOne.getProgress()>= seekBarOne.getMax()){
                    this.cancel();
                    Toast.makeText(MainActivity.this, "ONE WIN", Toast.LENGTH_LONG).show();
                    if(win(1)){
                        AddScore();
                        Toast.makeText(MainActivity.this, "You Won 5 Score", Toast.LENGTH_LONG).show();
                    }else{
                        SubScore();
                        Toast.makeText(MainActivity.this, "You Lose 5 Score", Toast.LENGTH_LONG).show();
                    }
                    EnableCheckBox();
                    imageButtonStart.setVisibility(View.VISIBLE);
                }
                else if(seekBarTwo.getProgress()>=seekBarTwo.getMax()){
                    this.cancel();
                    Toast.makeText(MainActivity.this, "TWO WIN", Toast.LENGTH_LONG).show();
                    if(win(2)){
                        AddScore();
                        Toast.makeText(MainActivity.this, "You Won 5 Score", Toast.LENGTH_LONG).show();
                    }else{
                        SubScore();
                        Toast.makeText(MainActivity.this, "You Lose 5 Score", Toast.LENGTH_LONG).show();
                    }
                    EnableCheckBox();
                    imageButtonStart.setVisibility(View.VISIBLE);

                }
                else if(seekBarThree.getProgress()>=seekBarThree.getMax()){
                    this.cancel();
                    Toast.makeText(MainActivity.this, "THREE WIN", Toast.LENGTH_LONG).show();
                    if(win(3)){
                        AddScore();
                        Toast.makeText(MainActivity.this, "You Won 5 Score", Toast.LENGTH_LONG).show();
                    }else{
                        SubScore();
                        Toast.makeText(MainActivity.this, "You Lose 5 Score", Toast.LENGTH_LONG).show();
                    }
                    EnableCheckBox();
                    imageButtonStart.setVisibility(View.VISIBLE);
                }

                seekBarOne.setProgress(seekBarOne.getProgress()+num1);
                seekBarTwo.setProgress(seekBarTwo.getProgress()+num2);
                seekBarThree.setProgress(seekBarThree.getProgress()+num3);

            }

            @Override
            public void onFinish() {

            }
        };
        imageButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isCheckedOne()) {
                    seekBarOne.setProgress(0);
                    seekBarOne.setEnabled(false);
                    seekBarTwo.setProgress(0);
                    seekBarTwo.setEnabled(false);
                    seekBarThree.setProgress(0);
                    seekBarThree.setEnabled(false);
                    InEnableCheckBox();
                    imageButtonStart.setVisibility(View.INVISIBLE);
                    countDownTimer.start();

                }
                else{
                    Toast.makeText(MainActivity.this, "You Have To Choose One", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void EnableCheckBox(){
        checkBoxOne.setEnabled(true);
        checkBoxThree.setEnabled(true);
        checkBoxTwo.setEnabled(true);
    }
    private void InEnableCheckBox(){
        checkBoxOne.setEnabled(false);
        checkBoxThree.setEnabled(false);
        checkBoxTwo.setEnabled(false);
    }
    private boolean isCheckedOne(){
        return checkBoxOne.isChecked() || checkBoxTwo.isChecked() || checkBoxThree.isChecked();
    }
    private boolean win(int Winner){
        if(checkBoxOne.isChecked() && Winner == 1){
            return true;
        }
        else if(checkBoxTwo.isChecked() && Winner == 2){
            return true;
        }
        else if(checkBoxThree.isChecked() && Winner == 3){
            return true;
        }
        else
            return false;
    }

    private void SubScore(){
        int curScore = Integer.parseInt(textViewScore.getText().toString());
        textViewScore.setText(curScore - 5 + "");
    }
    private void AddScore(){
        int curScore = Integer.parseInt(textViewScore.getText().toString());
        textViewScore.setText(curScore + 5 + "");
    }

    private void AnhXa(){
        textViewScore = (TextView) findViewById(R.id.score);
        imageButtonStart = (ImageButton) findViewById(R.id.btnStart);
        checkBoxOne = (CheckBox) findViewById(R.id.checkBoxOne);
        checkBoxTwo = (CheckBox) findViewById(R.id.checkBoxTwo);
        checkBoxThree = (CheckBox) findViewById(R.id.checkBoxThree);
        seekBarOne = (SeekBar) findViewById(R.id.seekBarOne);
        seekBarTwo = (SeekBar) findViewById(R.id.seekBarTwo);
        seekBarThree = (SeekBar) findViewById(R.id.seekBarThree);

    }
}