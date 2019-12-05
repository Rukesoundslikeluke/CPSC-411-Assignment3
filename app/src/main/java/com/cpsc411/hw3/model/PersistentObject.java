package com.cpsc411.hw3.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class PersistentObject {
    public abstract void insert(SQLiteDatabase db);
    public abstract void initFrom(Cursor c, SQLiteDatabase db);
}
