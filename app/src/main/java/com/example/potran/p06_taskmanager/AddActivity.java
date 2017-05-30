package com.example.potran.p06_taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    Button btnAdd, btnCancel;
    EditText etName, etDes, etTime;

    int rc = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        etName = (EditText) findViewById(R.id.editTextName);
        etDes = (EditText) findViewById(R.id.editTextDescription);
        etTime = (EditText) findViewById(R.id.editTextTime);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String des = etDes.getText().toString();
//                final RadioButton rbStars = (RadioButton)findViewById(selectedButtonId);
                DBHelper db = new DBHelper(AddActivity.this);
                db.insertTask(name,des);


                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, Integer.parseInt(etTime.getText().toString()));

                Intent intent = new Intent(AddActivity.this,
                        ScheduledNotificationReceiver.class);
                intent.putExtra("title",name);
                intent.putExtra("des", des);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        AddActivity.this, rc,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);


                setResult(RESULT_OK, intent);
                db.close();
                finish();



            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddActivity.this, MainActivity.class);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }


}
