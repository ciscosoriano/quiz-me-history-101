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
import android.widget.EditText;
import android.widget.Toast;

import com.madgorillastudios.cisco.quizme_history101.data.Constants;

public class PlayerNameActivity extends AppCompatActivity {
    private static final String TAG = PlayerNameActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        setContentView(R.layout.activity_player_name);

        Button btnStartQuiz = (Button) findViewById(R.id.button_start_quiz);
        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            EditText etPlayerName = (EditText) findViewById(R.id.edit_text_player_name);
            if (!etPlayerName.getText().toString().isEmpty()) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(Constants.PLAYER_NAME_KEY, etPlayerName.getText().toString());
                editor.commit();

                Log.i(TAG, "playerName Commit Success! " + etPlayerName.getText().toString());

                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.enter_player_name_warning, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
            }
            }
        });
    }
}