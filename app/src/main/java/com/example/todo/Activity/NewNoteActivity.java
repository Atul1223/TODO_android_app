package com.example.todo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.example.todo.Database.NotesDatabase;
import com.example.todo.MainActivity;
import com.example.todo.R;
import com.example.todo.databinding.ActivityNewNoteBinding;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewNoteActivity extends AppCompatActivity {

    ActivityNewNoteBinding activityNewNoteBinding;
    String title="",subtitle="",note="",priority="",toastString="",deadLine;
    NotesDatabase notesDatabase;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNewNoteBinding=ActivityNewNoteBinding.inflate(getLayoutInflater());
        setContentView(activityNewNoteBinding.getRoot());
        title="";
        subtitle="";
        note="";
        priority="";
        toastString="";
        deadLine="";
        notesDatabase=new NotesDatabase(getApplicationContext(),"notes_db",null,1);

       activityNewNoteBinding.greenPriority.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(activityNewNoteBinding.greenPriority.getDrawable()==null){
                   activityNewNoteBinding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
                   activityNewNoteBinding.redPriority.setImageDrawable(null);
                   activityNewNoteBinding.yellowPriority.setImageDrawable(null);
                   priority="Green";
               }
               else{
                   activityNewNoteBinding.greenPriority.setImageDrawable(null);
               }

           }
       });

        activityNewNoteBinding.redPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activityNewNoteBinding.redPriority.getDrawable()==null){
                    activityNewNoteBinding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
                    activityNewNoteBinding.greenPriority.setImageDrawable(null);
                    activityNewNoteBinding.yellowPriority.setImageDrawable(null);
                    priority="Red";
                }
                else{
                    activityNewNoteBinding.redPriority.setImageDrawable(null);
                }

            }
        });

        activityNewNoteBinding.yellowPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activityNewNoteBinding.yellowPriority.getDrawable()==null){
                    activityNewNoteBinding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
                    activityNewNoteBinding.redPriority.setImageDrawable(null);
                    activityNewNoteBinding.greenPriority.setImageDrawable(null);
                    priority="Yellow";
                }
                else{
                    activityNewNoteBinding.yellowPriority.setImageDrawable(null);
                }
            }
        });


        activityNewNoteBinding.doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title=activityNewNoteBinding.noteTitleEditText.getText().toString();
                subtitle=activityNewNoteBinding.noteSubTitleEditText.getText().toString();
                note=activityNewNoteBinding.noteEditText.getText().toString();
                deadLine=activityNewNoteBinding.deadLineDateEditText.getText().toString();

                if(subtitle.isEmpty()||note.isEmpty()||priority.isEmpty()||title.isEmpty()||deadLine.isEmpty()){
                    toastString="Some fields are missing...";
                }
                else{
                    toastString="Saving...";
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar c = Calendar.getInstance();
                    String date = "Created on: "+sdf.format(c.getTime());
                    notesDatabase.insertData(title,subtitle,priority,note,date,deadLine);
                    intent=new Intent(NewNoteActivity.this, MainActivity.class);
                }
                Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_SHORT).show();
                if(toastString.equals("Saving...")){
                    startActivity(intent);
                }

            }
        });
    }

}