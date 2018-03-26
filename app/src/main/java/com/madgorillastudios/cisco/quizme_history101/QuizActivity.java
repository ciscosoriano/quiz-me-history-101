package com.madgorillastudios.cisco.quizme_history101;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.madgorillastudios.cisco.quizme_history101.data.Constants;
import com.madgorillastudios.cisco.quizme_history101.data.DbHelper;
import com.madgorillastudios.cisco.quizme_history101.data.Question;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = QuizActivity.class.getName();

    private TextView tvQuestion;

    private RadioButton rbFirstChoice;
    private RadioButton rbSecondChoice;
    private RadioButton rbThirdChoice;
    private RadioButton rbFourthChoice;

    private Button btnNextQuestion;

    private Question mQuestion;
    private ArrayList<Question> mQuestionList;

    private int mQuizScore = 0;
    private int mQuestionId = 0;
    private int mQuestionsDbCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        setContentView(R.layout.activity_quiz);

        tvQuestion = (TextView) findViewById(R.id.text_view_question);
        rbFirstChoice = (RadioButton) findViewById(R.id.radio_button_first_choice);
        rbSecondChoice = (RadioButton) findViewById(R.id.radio_button_second_choice);
        rbThirdChoice = (RadioButton) findViewById(R.id.radio_button_third_choice);
        rbFourthChoice = (RadioButton) findViewById(R.id.radio_button_fourth_choice);

        DbHelper questionsDb = new DbHelper(getApplicationContext());

        mQuestionsDbCount = questionsDb.getQuestionsCount();

        mQuestionList = questionsDb.getAllQuestions();
        updateQuestion(mQuestionList.get(mQuestionId));

        btnNextQuestion = (Button) findViewById(R.id.button_next_question);
        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            RadioGroup rgChoices = (RadioGroup) findViewById(R.id.radio_group_choices);
            int rgChoicesId = rgChoices.getCheckedRadioButtonId();

            if(rgChoicesId == -1) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.select_answer_warning, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
            } else {
                RadioButton rdSelectedChoice = (RadioButton) rgChoices.findViewById(rgChoicesId);

                if(mQuestion.getCorrectAnswer().equalsIgnoreCase(rdSelectedChoice.getText().toString())) {
                    mQuizScore++;

                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(Constants.QUIZ_SCORE_KEY, String.valueOf(mQuizScore));
                    editor.commit();

                    Log.i(TAG, "quizScore Commit Success!");
                }

                rgChoices.clearCheck();

                mQuestionId++;

                if(mQuestionId < mQuestionsDbCount) {
                    updateQuestion(mQuestionList.get(mQuestionId));
                } else {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    startActivity(intent);
                }
            }
            }
        });
    }

    public void updateQuestion(Question newQuestion) {
        if(mQuestionId < mQuestionsDbCount) {
            mQuestion = newQuestion;

            tvQuestion.setText(mQuestion.getQuestion());
            rbFirstChoice.setText(mQuestion.getFirstChoice());
            rbSecondChoice.setText(mQuestion.getSecondChoice());
            rbThirdChoice.setText(mQuestion.getThirdChoice());
            rbFourthChoice.setText(mQuestion.getFourthChoice());

            if((mQuestionId + 1) == mQuestionsDbCount) {
                btnNextQuestion.setText("Check Score");
            }
        }
    }

    @Override
    public void onBackPressed() {}
}