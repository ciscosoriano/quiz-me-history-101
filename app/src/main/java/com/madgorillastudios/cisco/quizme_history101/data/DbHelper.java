package com.madgorillastudios.cisco.quizme_history101.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
        Question questionOne = new Question("Who fought in the war of 1812?",
                "Andrew Jackson",
                "Arthur Wellsley",
                "Otto von Bismarck",
                "Napoleon",
                "Andrew Jackson");

        insertQuestion(questionOne);

        Question questionTwo = new Question("Which general famously stated 'I shall return'?",
                "Bull Halsey",
                "George Patton",
                "Douglas MacArthur",
                "Omar Bradley",
                "Douglas MacArthur");

        insertQuestion(questionTwo);

        Question questionThree = new Question("The first successful printing press was developed by this man",
                "Johannes Gutenburg",
                "Benjamin Franklin",
                "Sir Isaac Newton",
                "Martin Luther",
                "Johannes Gutenburg");

        insertQuestion(questionThree);

        Question questionFour = new Question("Which Roman Emporer build a massive wall across Norther Britain in 122 AD?",
                "Marcus Aurelius",
                "Hadrian",
                "Nero",
                "Augustus",
                "Hadrian");

        insertQuestion(questionFour);
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