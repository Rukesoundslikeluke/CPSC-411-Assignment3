package com.cpsc411.hw3.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Course extends PersistentObject{
    protected String mCourseId;
    protected String mGrade;
    protected String mOwner;

    public Course (){}
    public Course(String courseId, String grade, String owner){
        mCourseId = courseId;
        mGrade = grade;
        mOwner = owner;
    }

    public String getCourseId() {
        return mCourseId;
    }
    public void setCourseId(String mCourseId) {
        this.mCourseId = mCourseId;
    }

    public String getGrade() {
        return mGrade;
    }
    public void setGrade(String mGrade) {
        this.mGrade = mGrade;
    }

    @Override
    public void insert(SQLiteDatabase db){
        ContentValues vals = new ContentValues();
        vals.put("CourseID",mCourseId);
        vals.put("Grade",mGrade);
        vals.put("Owner", mOwner);
        db.insert("Course",null,vals);
    }
    @Override
    public void initFrom(Cursor c, SQLiteDatabase db){}
}
