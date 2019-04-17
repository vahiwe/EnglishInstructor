package com.example.englishinstructor;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mPassageSound;
    private ImageView mPlayPassage;
    private CheckBox box1;
    private CheckBox box2;
    private CheckBox box3;
    private CheckBox box4;
    private CheckBox box5;
    private int score = 0;
    private ImageView wrong_1;
    private ImageView wrong_2;
    private ImageView wrong_3;
    private ImageView wrong_4;
    private ImageView wrong_5;
    private RadioGroup questOne;
    private RadioGroup questTwo;
    private RadioGroup questFour;
    private RadioGroup questFive;
    private RadioButton quest1a;
    private RadioButton quest1c;
    private RadioButton quest1d;
    private RadioButton quest2a;
    private RadioButton quest2b;
    private RadioButton quest2d;
    private RadioButton quest4b;
    private RadioButton quest4c;
    private RadioButton quest4d;
    private RadioButton quest5b;
    private RadioButton quest5c;
    private RadioButton quest5d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playSound();
        initializeSomeVariables();
    }

    private void initializeSomeVariables() {
        quest1a = findViewById(R.id.quest_1_id_a);
        quest1c = findViewById(R.id.quest_1_id_c);
        quest1d = findViewById(R.id.quest_1_id_d);
        quest2a = findViewById(R.id.quest_2_id_a);
        quest2b = findViewById(R.id.quest_2_id_b);
        quest2d = findViewById(R.id.quest_2_id_d);
        quest4b = findViewById(R.id.quest_4_id_b);
        quest4c = findViewById(R.id.quest_4_id_c);
        quest4d = findViewById(R.id.quest_4_id_d);
        quest5b = findViewById(R.id.quest_5_id_b);
        quest5c = findViewById(R.id.quest_5_id_c);
        quest5d = findViewById(R.id.quest_5_id_d);
        questOne = findViewById(R.id.quest_1);
        questTwo = findViewById(R.id.quest_2);
        questFour = findViewById(R.id.quest_4);
        questFive = findViewById(R.id.quest_5);
        box1 = findViewById(R.id.quest_3_id_a);
        box2 = findViewById(R.id.quest_3_id_b);
        box3 = findViewById(R.id.quest_3_id_c);
        box4 = findViewById(R.id.quest_3_id_d);
        box5 = findViewById(R.id.quest_3_id_e);
        wrong_1 = findViewById(R.id.wrong_1);
        wrong_2 = findViewById(R.id.wrong_2);
        wrong_3 = findViewById(R.id.wrong_3);
        wrong_4 = findViewById(R.id.wrong_4);
        wrong_5 = findViewById(R.id.wrong_5);
    }

    private void playSound() {
        mPlayPassage = findViewById(R.id.playtext);

        mPlayPassage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri nat = Uri.parse("android.resource://com.example.englishinstructor/" + R.raw.nature);
                //mPassageSound = MediaPlayer.create(getApplicationContext(), R.raw.nature);
                if (mPassageSound != null) {
                    mPassageSound.stop();
                    mPassageSound.release();
                    mPassageSound = null;
                } else {
                    mPassageSound = new MediaPlayer();
                    mPassageSound.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    try {
                        mPassageSound.setDataSource(getApplicationContext(), nat);
                    } catch (Exception e) {

                    }
                    mPassageSound.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });
                    mPassageSound.prepareAsync();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPassageSound != null) {
            mPassageSound.release();
            mPassageSound = null;
        }
    }

    public void wrongAnswer() {
        if (quest1a.isChecked() || quest1c.isChecked() || quest1d.isChecked()) {
            wrong_1.setVisibility(View.VISIBLE);
        }
        if (quest2a.isChecked() || quest2b.isChecked() || quest2d.isChecked()) {
            wrong_2.setVisibility(View.VISIBLE);
        }
        if (!(box3.isChecked() && box5.isChecked() && box1.isChecked() && !(box2.isChecked()) && !(box4.isChecked()))) {
            wrong_3.setVisibility(View.VISIBLE);
        }
        if (quest4b.isChecked() || quest4c.isChecked() || quest4d.isChecked()) {
            wrong_4.setVisibility(View.VISIBLE);
        }
        if (quest5b.isChecked() || quest5c.isChecked() || quest5d.isChecked()) {
            wrong_5.setVisibility(View.VISIBLE);
        }
    }

    public void reset () {
        questOne.clearCheck();
        questTwo.clearCheck();
        questFour.clearCheck();
        questFive.clearCheck();
        box1.setChecked(false);
        box2.setChecked(false);
        box3.setChecked(false);
        box4.setChecked(false);
        box5.setChecked(false);
        wrong_1.setVisibility(View.INVISIBLE);
        wrong_2.setVisibility(View.INVISIBLE);
        wrong_3.setVisibility(View.INVISIBLE);
        wrong_4.setVisibility(View.INVISIBLE);
        wrong_5.setVisibility(View.INVISIBLE);
    }

    public void submit (View view) {
        if (mPassageSound !=null){
            if (mPassageSound.isPlaying()) {
                mPassageSound.stop();
                mPassageSound.release();
                mPassageSound = null;
            }
        }
        if ((questOne.getCheckedRadioButtonId() == -1) || (questTwo.getCheckedRadioButtonId() == -1)
                || (questFour.getCheckedRadioButtonId() == -1)
                || (questFive.getCheckedRadioButtonId() == -1) ||
                ((!box1.isChecked()) && (!box2.isChecked()) && (!box3.isChecked())
                && (!box4.isChecked()) && (!box5.isChecked()))) {
            Toast check2 = Toast.makeText(this, "Please answer all questions!!! ", Toast.LENGTH_SHORT);
            check2.setGravity(Gravity.CENTER, 0, 0);
            check2.show();
            return;
        }

        String messag = "Check the questions answered wrongly while app resets!!!";
        wrongAnswer();
        String order = endMessage(score);
        Toast check4 = Toast.makeText(this, order, Toast.LENGTH_LONG);
        check4.setGravity(Gravity.CENTER, 0, 0);
        check4.show();
        if (score < 50) {
            Toast check5 = Toast.makeText(this, messag, Toast.LENGTH_LONG);
            check5.setGravity(Gravity.CENTER, 0, 0);
            check5.show();
        } else {
            Toast check5 = Toast.makeText(this, "Wait while app resets!!!", Toast.LENGTH_LONG);
            check5.setGravity(Gravity.CENTER, 0, 0);
            check5.show();
        }
        Toast check6 = Toast.makeText(this, "Resetting..............", Toast.LENGTH_LONG);
        check6.setGravity(Gravity.CENTER, 0, 0);
        check6.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                reset();
            }
        }, 15000);
    }

    public String endMessage(int score) {
        String message = "HI, ";
        message += "\nYou scored " + score + "%\n";
        switch (score) {
            case 0:
                message += "That's very poor ";
                break;
            case 10:
                message += "Try again";
                break;
            case 20:
                message += "That's appalling";
                break;
            case 30:
                message += "Nice try, but you can do better";
                break;
            case 40:
                message += "Nice job";
                break;
            case 50:
                message += "You did great";
                break;
        }
        return message;
    }


    public void question1(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.quest_1_id_a:
                if (checked) {
                    return;
                }
                break;

            case R.id.quest_1_id_b:
                if (checked) {
                    score += 10;
                }
                break;

            case R.id.quest_1_id_c:
                if (checked) {
                    return;
                }
                break;

            case R.id.quest_1_id_d:
                if (checked) {
                    return;
                }
                break;
        }
    }

    public void question2(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.quest_2_id_a:
                if (checked) {
                    return;
                }
                break;

            case R.id.quest_2_id_b:
                if (checked) {
                    return;
                }
                break;

            case R.id.quest_2_id_c:
                if (checked) {
                    score += 10;
                }
                break;

            case R.id.quest_2_id_d:
                if (checked) {
                    return;
                }
                break;
        }
    }

    public void question3(View view) {
        boolean val1 = box1.isChecked();
        boolean val2 = box2.isChecked();
        boolean val3 = box3.isChecked();
        boolean val4 = box4.isChecked();
        boolean val5 = box5.isChecked();

        if (val3 && val5 && val1 && !val2 && !val4) {
            score += 10;
        }

    }

    public void question4(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.quest_4_id_a:
                if (checked) {
                    score += 10;
                }
                break;

            case R.id.quest_4_id_b:
                if (checked) {
                    return;
                }
                break;

            case R.id.quest_4_id_c:
                if (checked) {
                    return;
                }
                break;

            case R.id.quest_4_id_d:
                if (checked) {
                    return;
                }
                break;
        }
    }

    public void question5(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.quest_5_id_a:
                if (checked) {
                    score += 10;
                }
                break;

            case R.id.quest_5_id_b:
                if (checked) {
                    return;
                }
                break;

            case R.id.quest_5_id_c:
                if (checked) {
                    return;
                }
                break;

            case R.id.quest_5_id_d:
                if (checked) {
                    return;
                }
                break;
        }
    }


}
