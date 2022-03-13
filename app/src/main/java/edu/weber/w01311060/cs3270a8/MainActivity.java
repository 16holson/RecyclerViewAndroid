package edu.weber.w01311060.cs3270a8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

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

public class MainActivity extends AppCompatActivity implements CourseListFragment.onClickCourse
{
    private CourseViewFragment cv;
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
    public void updateCourse(Courses course)
    {
        //change fragment to CourseViewFragment and send the course
        fm.beginTransaction()
                .replace(R.id.fragmentContainerView, new CourseViewFragment(), "CourseViewFragment")
                .addToBackStack(null)
                .commit();
                //.commitNow();
        fm.executePendingTransactions();
        if(cv == null)
        {
            cv = (CourseViewFragment) fm.findFragmentByTag("CourseViewFragment");
        }
        if(cv != null)
        {
            cv.showCourse(course);
        }

    }
}