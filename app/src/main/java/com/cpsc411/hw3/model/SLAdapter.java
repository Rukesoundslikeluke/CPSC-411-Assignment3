package com.cpsc411.hw3.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cpsc411.hw3.R;

import java.util.ArrayList;

public class SLAdapter extends BaseAdapter {
    ArrayList<Student> mStudentList;
    StudentDB mStudentDB = StudentDB.getInstance();

    public SLAdapter(){
        mStudentList = mStudentDB.retrieveStudentObjects();
    }

    @Override
    public int getCount() {
        return mStudentDB.getStudentList().size();
    }

    @Override
    public Object getItem(int i) {
        return mStudentDB.getStudentList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row_view;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            row_view = inflater.inflate(R.layout.student_row, viewGroup, false);
        } else row_view = view;
        Student s = mStudentDB.getStudentList().get(i);
        //
        ((TextView) row_view.findViewById(R.id.first_name)).setText(s.getFirstName());
        ((TextView) row_view.findViewById(R.id.last_name)).setText(s.getLastName());
        return row_view;
    }
}
