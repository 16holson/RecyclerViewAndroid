package edu.weber.w01311060.cs3270a8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import edu.weber.w01311060.cs3270a8.db.AppDatabase;
import edu.weber.w01311060.cs3270a8.db.CourseDAO;
import edu.weber.w01311060.cs3270a8.models.Courses;

public class MainActivity extends AppCompatActivity
{

    private List<Courses> courses;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CourseEditFragment dialog = new CourseEditFragment();
                dialog.show(getSupportFragmentManager(), "dialog");
            }
        });

        Button databtn = findViewById(R.id.databtn);
        databtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        courses = AppDatabase.getInstance(getApplicationContext())
                                .getCourseDao()
                                .getAll();
                        for (Courses c : courses)
                        {
                            Log.d("Database", "Course: " + c.toString());
                        }
                    }
                }).start();
            }
        });

    }
}