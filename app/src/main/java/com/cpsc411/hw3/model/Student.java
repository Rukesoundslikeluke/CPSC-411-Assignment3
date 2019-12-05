package com.cpsc411.hw3.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Student extends PersistentObject{
    protected String mFirstName;
    protected String mLastName;
    protected String mCWID;
    protected ArrayList<Course> mCourses;

    public Student(){

    }

    public Student(String fName, String lName, String CWID, ArrayList<Course> courses){
        mFirstName = fName;
        mLastName = lName;
        mCWID = CWID;
        mCourses = courses;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getCWID() {
        return mCWID;
    }

    @Override
    public void insert(SQLiteDatabase db){
        ContentValues vals = new ContentValues();
        vals.put("FirstName", mFirstName);
        vals.put("LastName", mLastName);
        vals.put("CWID", mCWID);
        db.insert("Student",null,vals);
        for (int i=0;i<mCourses.size();i++){
            mCourses.get(i).insert(db);
        }
    }

    @Override
    public void initFrom(Cursor c, SQLiteDatabase db){
        mFirstName = c.getString(c.getColumnIndex("FirstName"));
        mLastName = c.getString(c.getColumnIndex("LastName"));
        mCWID = c.getString(c.getColumnIndex("CWID"));
        mCourses = new ArrayList<Course>();
        Cursor cursor = db.query("COURSE",null,"Owner=?",new String[]{mCWID},null,null,null);
        if (cursor.getCount()>0){
            while(cursor.moveToNext()){
                Course cObj = new Course();
                cObj.initFrom(cursor, db);
                mCourses.add(cObj);
            }
        }
    }

    public void setCWID(String CWID) {
        mCWID = CWID;
    }

    public ArrayList<Course> getCourses() {
        return mCourses;
    }

    public void setCourses(ArrayList<Course> courses) {
        mCourses = courses;
    }
}
