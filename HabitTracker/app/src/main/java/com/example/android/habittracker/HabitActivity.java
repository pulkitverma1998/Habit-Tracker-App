package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

public class HabitActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void insertHabit() {

        HabitDbHelper habitDbHelper = new HabitDbHelper(this);

        SQLiteDatabase db = habitDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(HabitEntry.COLUMN_HABIT_NAME, "Exercise");
        values.put(HabitEntry.COLUMN_HABIT_DESCRIPTION, "1 hour of daily workout");
        values.put(HabitEntry.COLUMN_HABIT_START_DATE, "22-03-2017");
        values.put(HabitEntry.COLUMN_GOAL_ACHIEVED_OR_NOT, HabitEntry.GOAL_ACHIEVED);

        db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    private Cursor read() {

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_DESCRIPTION,
                HabitEntry.COLUMN_HABIT_START_DATE,
                HabitEntry.COLUMN_GOAL_ACHIEVED_OR_NOT
        };

        mDbHelper = new HabitDbHelper(this);

        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor = database.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        return cursor;
    }

    private void displayDatabaseInfo() {

        Cursor mCursor = read();

        try {

            // Figure out the index of each column
            int idColumnIndex = mCursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = mCursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int descriptionColumnIndex = mCursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DESCRIPTION);
            int habitStartDateColumnIndex = mCursor.getColumnIndex(HabitEntry.COLUMN_HABIT_START_DATE);
            int goalAchievedOrNotColumnIndex = mCursor.getColumnIndex(HabitEntry.COLUMN_GOAL_ACHIEVED_OR_NOT);

            // Iterate through all the returned rows in the cursor
            while (read().moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = mCursor.getInt(idColumnIndex);
                String currentName = mCursor.getString(nameColumnIndex);
                String currentDescription = mCursor.getString(descriptionColumnIndex);
                String currentHabitStartDate = mCursor.getString(habitStartDateColumnIndex);
                int currentGoalAchievedOrNot = mCursor.getInt(goalAchievedOrNotColumnIndex);

                Log.v("HabitActivity", currentID + " - "
                        + currentName + " - "
                        + currentDescription + " - "
                        + currentHabitStartDate + " - "
                        + currentGoalAchievedOrNot + " - ");

            }

        } finally {
            mCursor.close();
        }
    }
}
