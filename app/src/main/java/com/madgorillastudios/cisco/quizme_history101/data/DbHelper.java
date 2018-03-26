package com.madgorillastudios.cisco.quizme_history101.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.madgorillastudios.cisco.quizme_history101.R;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "questionsMasterDb";
    private static final String QUESTIONS_TABLE_NAME = "questionsTable";
    private static final String QUESTIONS_ID = "id";
    private static final String QUESTIONS_QUESTION = "question";
    private static final String QUESTIONS_FIRST_CHOICE = "firstChoice";
    private static final String QUESTIONS_SECOND_CHOICE = "secondChoice";
    private static final String QUESTIONS_THIRD_CHOICE = "thridChoice";
    private static final String QUESTIONS_FOURTH_CHOICE = "fourthChoice";
    private static final String QUESTIONS_CORRECT_ANSWER = "correctAnswer";

    private SQLiteDatabase localDatabase;

    private Context mContext;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        localDatabase = db;
        String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + QUESTIONS_TABLE_NAME +
                "(" +
                QUESTIONS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QUESTIONS_QUESTION + " TEXT, " +
                QUESTIONS_FIRST_CHOICE + " TEXT, " +
                QUESTIONS_SECOND_CHOICE + " TEXT, " +
                QUESTIONS_THIRD_CHOICE + " TEXT, " +
                QUESTIONS_FOURTH_CHOICE + " TEXT, " +
                QUESTIONS_CORRECT_ANSWER + " TEXT " +
                ")";

        db.execSQL(CREATE_QUESTIONS_TABLE);

        initializeDefaultQuestions();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + QUESTIONS_TABLE_NAME);
            onCreate(db);
        }
    }

    public void initializeDefaultQuestions() {
        Resources res = mContext.getResources();
        String[] questionList = res.getStringArray(R.array.questions);
        String[] choiceList = res.getStringArray(R.array.question_choices);
        String[] answerKeyList = res.getStringArray(R.array.correct_answer_keys);

        insertQuestion(new Question(questionList[0], choiceList[0], choiceList[1], choiceList[2], choiceList[3], answerKeyList[0]));
        insertQuestion(new Question(questionList[1], choiceList[4], choiceList[5], choiceList[6], choiceList[7], answerKeyList[1]));
        insertQuestion(new Question(questionList[2], choiceList[8], choiceList[9], choiceList[10], choiceList[11], answerKeyList[2]));
        insertQuestion(new Question(questionList[3], choiceList[12], choiceList[13], choiceList[14], choiceList[15], answerKeyList[3]));
    }

    public void insertQuestion(Question newQuestion) {
        ContentValues values = new ContentValues();
        values.put(QUESTIONS_QUESTION, newQuestion.getQuestion());
        values.put(QUESTIONS_FIRST_CHOICE, newQuestion.getFirstChoice());
        values.put(QUESTIONS_SECOND_CHOICE, newQuestion.getSecondChoice());
        values.put(QUESTIONS_THIRD_CHOICE, newQuestion.getThirdChoice());
        values.put(QUESTIONS_FOURTH_CHOICE, newQuestion.getFourthChoice());
        values.put(QUESTIONS_CORRECT_ANSWER, newQuestion.getCorrectAnswer());

        localDatabase.insert(QUESTIONS_TABLE_NAME, null, values);
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<Question>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + QUESTIONS_TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestionId(cursor.getInt(0));
                question.setQuestion(cursor.getString(1));
                question.setFirstChoice(cursor.getString(2));
                question.setSecondChoice(cursor.getString(3));
                question.setThirdChoice(cursor.getString(4));
                question.setFourthChoice(cursor.getString(5));
                question.setCorrectAnswer(cursor.getString(6));
                questionList.add(question);
            } while (cursor.moveToNext());
        }

        return questionList;
    }

    public int getQuestionsCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + QUESTIONS_TABLE_NAME, null);

        return cursor.getCount();
    }
}