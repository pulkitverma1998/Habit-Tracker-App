package com.example.android.habittracker.data;

import android.provider.BaseColumns;

public final class HabitContract {

    private HabitContract() {

    }

    public static class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habit";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_HABIT_DESCRIPTION = "description";
        public static final String COLUMN_HABIT_START_DATE = "date";
        public static final String COLUMN_GOAL_ACHIEVED_OR_NOT = "goal";

        public static final int GOAL_ACHIEVED = 1;
        public static final int GOAL_NOT_ACHIEVED = 0;

    }
}
