package com.example.jesna.emhealth;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by NIFU on 05-Sep-18.
 */

public class DBManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HEALTH.db";
    public static final String TABLE_QUESTION = "QUESTIONS";
    public static final String ID = "id";
    public static final String QUESTION = "question";
    public static final String OPTION1 = "option1";
    public static final String OPTION2 = "option2";
    public static final String OPTION3 = "option3";
    public static final String OPTION4 = "option4";
    public static final String OPTION5 = "option5";


    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_QUESTION + "(" + ID + " integer primary key,"
                + QUESTION + " text,"
                + OPTION1 + " text,"
                + OPTION2 + " text,"
                + OPTION3 + " text,"
                + OPTION4 + " text,"
                + OPTION5 + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int numOfRows() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        int numOfRows = (int) DatabaseUtils.queryNumEntries(sqLiteDatabase, TABLE_QUESTION);
        return numOfRows;
    }

    public boolean insertUserDetails(String _question, String _option1, String _option2, String _option3,
                                     String _option4, String _option5) {

        SQLiteDatabase db = this.getWritableDatabase();

        //db.execSQL("insert into " +POCKET_DIARY_TABLE_REGISTRATION+ "(" +PLAYERDETAILS_COLUMN_NAME+ "," +PLAYERDETAILS_COLUMN_PIN+ ")values("+name+","+Pin+","+gender+","+highscore+")");
        ContentValues contentValues = new ContentValues();
        contentValues.put(QUESTION, _question);
        contentValues.put(OPTION1, _option1);
        contentValues.put(OPTION2, _option2);
        contentValues.put(OPTION3, _option3);
        contentValues.put(OPTION4, _option4);
        contentValues.put(OPTION5, _option5);


        db.insert(TABLE_QUESTION, null, contentValues);

        return true;
    }

    /*public boolean getdetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_QUESTION + " where " + QUESTION + " = " + "'" + no + "'", null);
        if (res.getCount() == 0) {
            return false;
        } else {
            return true;
        }

    }*/
}