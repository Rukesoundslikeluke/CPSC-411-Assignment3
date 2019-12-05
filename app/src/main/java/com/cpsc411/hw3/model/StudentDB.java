package com.cpsc411.hw3.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

public class StudentDB {
    private static final StudentDB singleInstance = new StudentDB();
    private SQLiteDatabase mSQLiteDatabase;
    private ArrayList<Student> mStudentList;

    static public StudentDB getInstance(){
        return singleInstance;
    }

    public StudentDB(){
        File dbFile = MyAppContext.getAppContext().getDatabasePath("student.db");
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile,null);
        createSQLTables();
        Cursor cursor = mSQLiteDatabase.query("STUDENT", null, null, null, null, null,null );
        if (cursor.getCount() < 1){
            this.createStudentObjects();
        }
    }

    public ArrayList<Student> retrieveStudentObjects(){
        ArrayList<Student> studentList = new ArrayList<>();
        Cursor cursor = mSQLiteDatabase.query("STUDENT", null, null, null, null, null,null );
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Student sObj = new Student();
                sObj.initFrom(cursor, mSQLiteDatabase);
                studentList.add(sObj);
            }
        }

        mStudentList = studentList;
        return studentList;
    }

    public void createStudentObjects(){
        mStudentList = new ArrayList<Student>();
        ArrayList<Course> courses = new ArrayList<Course>();
        courses.add(new Course("CPSC121-02","A","619430639"));
        courses.add(new Course("MATH150A-09", "B+","619430639"));
        Student student = new Student("Michael","Rodriguez","619430639",courses);
        student.insert(mSQLiteDatabase);
        mStudentList.add(student);

        courses = new ArrayList<Course>();
        courses.add(new Course("CPSC131-01","C","313152870"));
        courses.add(new Course("CPSC353-05","F","313152870"));
        student = new Student("John","Smith","313152870",courses);
        student.insert(mSQLiteDatabase);
        mStudentList.add(student);
    }

    public void createSQLTables(){
        String sql = "CREATE TABLE IF NOT EXISTS STUDENT (FirstName Text, LastName Text, CWID Text)";
        mSQLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE IF NOT EXISTS COURSE (CourseID Text, Grade Text, Owner Text)";
        mSQLiteDatabase.execSQL(sql);
    }

    public ArrayList<Student> getStudentList(){
        return mStudentList;
    }

    public void setStudentList(ArrayList<Student> studentList){
        mStudentList = studentList;
    }

    public void addStudent(Student s){
        mStudentList.add(s);
        s.insert(mSQLiteDatabase);
    }
}
