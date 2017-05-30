package com.example.potran.p06_taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

	//TODO Define the Database properties
	private static final String DATABASE_NAME = "task.db";
	private static final int DATABASE_VERSION = 1;

	private static final String TABLE_TASK = "task";
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_DESCRIPTION = "description";



	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//TODO CREATE TABLE Note
		String createTable = "CREATE TABLE " + TABLE_TASK + "("
				+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ COLUMN_NAME + " TEXT,"
				+ COLUMN_DESCRIPTION + " TEXT)";
		db.execSQL(createTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
		onCreate(db);
	}

	public void insertTask(String name, String des) {
		//TODO insert the data into the database
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, name);
		values.put(COLUMN_DESCRIPTION, des);
		db.insert(TABLE_TASK, null,values);
		db.close();
	}

//	public ArrayList<Task> getAllTask() {
//		//TODO return records in Java objects
//		ArrayList<Task> notes = new ArrayList<>();
//		String selectQuery = "SELECT " + COLUMN_ID + ", "
//				+ COLUMN_NAME + ", "
//				+ COLUMN_DESCRIPTION
//				+ " FROM " + TABLE_TASK;
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor cursor = db.rawQuery(selectQuery, null);
//
//		if (cursor.moveToFirst()){
//			do{
//				int id = cursor.getInt(0);
//				String name = cursor.getString(1);
//				String des = cursor.getString(2);
//				Task obj = new Task(id, name, des);
//				notes.add(obj);
//			} while (cursor.moveToNext());
//		}
//		return notes;
//	}

    public ArrayList<String> getTaskContent() {
        //TODO return records in Strings

        ArrayList<String> tasks = new ArrayList<String>();

        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_NAME + ", "
                +COLUMN_DESCRIPTION + " FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            // Loop while moveToNext() points to next row and returns true;
            // moveToNext() returns false when no more next row to move to
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String des = cursor.getString(2);
				tasks.add(id + " " + name + "\n" + des);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return tasks;
    }
}
