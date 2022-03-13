package edu.weber.w01311060.cs3270a8;

import android.app.Activity;
import android.bluetooth.le.ScanSettings;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.proto.ProtoOutputStream;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.weber.w01311060.cs3270a8.models.Courses;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseViewFragment extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View root;
    private TextView idInfo, nameInfo, courseCodeInfo, startAtInfo, endAtInfo;
    private onCourseViewListener mCallBack;
    private Courses course;

    public CourseViewFragment()
    {
        // Required empty public constructor
    }
    public interface onCourseViewListener
    {
        void onCourseViewClick(Courses course, CourseEditFragment edit);
        void onDeleteClick(Courses course, DeleteCourseDialog delete);
    }

    @Override
    public void onAttach(@NonNull Activity activity)
    {
        super.onAttach(activity);
        try
        {
            mCallBack = (CourseViewFragment.onCourseViewListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + "must implement onCourseViewListener");
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseViewFragment newInstance(String param1, String param2)
    {
        CourseViewFragment fragment = new CourseViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = root.findViewById(R.id.toolbar);
        toolbar.setTitle("View Course");
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        idInfo = root.findViewById(R.id.idInfo);
        nameInfo = root.findViewById(R.id.nameInfo);
        courseCodeInfo = root.findViewById(R.id.courseCodeInfo);
        startAtInfo = root.findViewById(R.id.startAtInfo);
        endAtInfo = root.findViewById(R.id.endAtInfo);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater)
    {
        inflater.inflate(R.menu.courseview, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.edit:
                CourseEditFragment dialog = new CourseEditFragment();
                dialog.show(getParentFragmentManager(), "editDialog");
                mCallBack.onCourseViewClick(course, dialog);
                return true;
            case R.id.delete:
                DeleteCourseDialog deleteDialog = new DeleteCourseDialog();
                deleteDialog.setCancelable(false);
                deleteDialog.show(getParentFragmentManager(), "deleteDialog");
                mCallBack.onDeleteClick(course, deleteDialog);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_course_view, container, false);
    }

    public void showCourse(Courses course)
    {
        this.course = course;
        setCourseText();
    }

    public void setCourseText()
    {
        idInfo.setText(course.getId());
        nameInfo.setText(course.getName());
        courseCodeInfo.setText(course.getCourseCode());
        startAtInfo.setText(course.getStartAt());
        endAtInfo.setText(course.getEndAt());
    }
}