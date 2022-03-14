package edu.weber.w01311060.cs3270a8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.weber.w01311060.cs3270a8.db.AppDatabase;
import edu.weber.w01311060.cs3270a8.db.CourseDAO;
import edu.weber.w01311060.cs3270a8.models.Courses;

public class MainActivity extends AppCompatActivity implements CourseListFragment.onClickCourse, CourseViewFragment.onCourseViewListener, CourseEditFragment.onSaveCourse, DeleteCourseDialog.onDeleteListener
{
    private CourseViewFragment cvf;
    private FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        fm = getSupportFragmentManager();
    }

    @Override
    public void updateCourse(Courses course, CourseViewFragment cv)
    {
          cvf = cv;
          cv.showCourse(course);
    }

    @Override
    public void onCourseViewClick(Courses course, CourseEditFragment edit)
    {
        //I'm pretty sure I shouldn't be doing this
        edit.saveCourse(course);
    }

    @Override
    public void onDeleteClick(Courses course, DeleteCourseDialog delete)
    {
        delete.setCourse(course);
    }

    @Override
    public void reloadCourse()
    {
        cvf.setCourseText();
    }

    @Override
    public void onReload()
    {
        cvf.dismiss();
    }
}