package com.training.coronavirus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "QuestionsInfo";

    // Contacts table name
    private static final String TABLE_QUESTIONS = "questions";


    // Questions Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TEXT = "text";
    private static final String KEY_CHOICES = "choices";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TEXT + " TEXT,"
                + KEY_CHOICES + " TEXT" + ")";
//to create another table do the same steps

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        // Creating tables again
        onCreate(db);
    }

    // Adding new question
    public void addQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, question.getId());
        values.put(KEY_TEXT, question.getText()); // question Text
        values.put(KEY_CHOICES, question.getChoices());
        // Inserting Row
        db.insert(TABLE_QUESTIONS, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Questions
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(Integer.parseInt(cursor.getString(0)));
                question.setText(cursor.getString(1));
                question.setChoicesList(cursor.getString(2));

                // Adding contact to list
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        // return contact list
        return questionList;
    }

}

