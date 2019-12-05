package com.cpsc411.hw3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cpsc411.hw3.model.Course;
import com.cpsc411.hw3.model.SLAdapter;
import com.cpsc411.hw3.model.Student;
import com.cpsc411.hw3.model.StudentDB;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {
    protected ListView mSummaryView;
    protected Menu addMenu;
    protected SLAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_list_lview);
        mSummaryView = findViewById(R.id.summary_list_id);

        adapter = new SLAdapter();
        mSummaryView.setAdapter(adapter);
    }

    @Override
    protected void onStart(){
        adapter.notifyDataSetChanged();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        menu.findItem(R.id.action_Add).setVisible(true);
        addMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.action_Add){
            Intent intent = new Intent(this,AddActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
