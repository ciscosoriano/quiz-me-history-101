package com.madgorillastudios.cisco.quizme_history101;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.madgorillastudios.cisco.quizme_history101.data.Constants;
import com.madgorillastudios.cisco.quizme_history101.data.DbHelper;

public class ResultActivity extends AppCompatActivity {
    private static final String TAG = ResultActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        setContentView(R.layout.activity_score);

        DbHelper questionsDb = new DbHelper(getApplicationContext());
        int questionsDbCount = questionsDb.getQuestionsCount();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String playerName = sharedPref.getString(Constants.PLAYER_NAME_KEY, Constants.PLAYER_NAME_DEFAULT_VALUE);

        TextView tvFinalScoreHeading = (TextView) findViewById(R.id.text_view_score_heading);
        tvFinalScoreHeading.setText("Your Score " + playerName + "!");

        String quizScore = sharedPref.getString(Constants.QUIZ_SCORE_KEY, Constants.QUIZ_SCORE_DEFAULT_VALUE);

        RatingBar rbFinalScoreRate = (RatingBar) findViewById(R.id.rating_bar_score);
        rbFinalScoreRate.setRating(Integer.valueOf(quizScore));

        TextView tvFinalScore = (TextView) findViewById(R.id.text_view_final_score);
        tvFinalScore.setText(quizScore + "/" + String.valueOf(questionsDbCount));

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear().commit();

        Button btnReturnHome = (Button) findViewById(R.id.button_return_home);
        btnReturnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {}
}