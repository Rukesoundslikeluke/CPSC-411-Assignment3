package com.cpsc411.hw3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.cpsc411.hw3.R;
import com.cpsc411.hw3.model.Course;
import com.cpsc411.hw3.model.Student;
import com.cpsc411.hw3.model.StudentDB;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {
    protected Menu addMenu;
    ArrayList<Course> courses = new ArrayList<Course>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final GridLayout g = findViewById(R.id.course_grid);
        g.setBackgroundResource(R.drawable.thin_border);

        final EditText courseID = new EditText(this);
        courseID.setSingleLine();
        courseID.setText("");
        courseID.setTextSize(14);
        g.addView(courseID);

        final EditText courseGrade = new EditText(this);
        courseGrade.setSingleLine();
        courseGrade.setText("");
        courseGrade.setTextSize(14);
        g.addView(courseGrade);

        Button addButton = findViewById(R.id.add_course);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String cCourse = courseID.getText().toString();
                String cGrade = courseGrade.getText().toString();
                TextView c = findViewById(R.id.cwid_add);
                String cwid = c.getText().toString();
                if (!cCourse.equals("") && !cGrade.equals("") && !cwid.equals("")){
                    Log.d("CPSC11", "Adding: " + cCourse + " " + cGrade);
                    TextView id = new TextView(view.getContext());
                    id.setText(courseID.getText().toString());
                    id.setTextSize(14);
                    id.setSingleLine();
                    g.addView(id);

                    TextView grade = new TextView(view.getContext());
                    grade.setText(courseGrade.getText().toString());
                    grade.setTextSize(14);
                    grade.setSingleLine();
                    g.addView(grade);

                    courses.add(new Course(courseID.getText().toString(), courseGrade.getText().toString(), cwid));
                    courseID.setText("");
                    courseGrade.setText("");
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.add_menu,menu);
        menu.findItem(R.id.action_Done).setVisible(true);
        addMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    //When done option is pressed we will send all the filled values to the db
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.action_Done){
            TextView f = findViewById(R.id.first_name_add);
            TextView l = findViewById(R.id.last_name_add);
            TextView c = findViewById(R.id.cwid_add);
            String fname = f.getText().toString();
            String lname = l.getText().toString();
            String cwid = c.getText().toString();

            Log.d("CPSC11", fname + "\n" + lname+ "\n" + cwid+ "\n" + courses.toString());
            if(!cwid.equals("") && !fname.equals("") && !lname.equals("") && !courses.isEmpty()){
                Student student = new Student(fname,lname,cwid,courses);
                StudentDB.getInstance().addStudent(student);
                finish();
            }
            else{
                Log.d("CPSC11","Ding" );
                final AlertDialog.Builder dAlert = new AlertDialog.Builder(this);
                dAlert.setMessage("Not all fields inputted!");
                dAlert.setTitle("Error");
                dAlert.setPositiveButton("OK", null);
                dAlert.create().show();
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
