package com.example.pumkin.firebasecsh;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference myRef2;

    TextView tvMessage;
    TextView tvMessage2;
    EditText etNewMessage;
    Button btUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMessage = (TextView) findViewById(R.id.tv_message);
        tvMessage2 = (TextView) findViewById(R.id.tv_message2);
        etNewMessage = (EditText) findViewById(R.id.et_newData);
        //btUpdate = (Button) findViewById(R.id.bt_update);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        myRef2 = database.getReference("message2");
/*
        //버튼 이벤트
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newMessage = etNewMessage.getText().toString();
                myRef.setValue(newMessage);
            }
        });
*/
        // Read from the database
        // 그리고 데이터베이스에 변경사항이 있으면 실행된다.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                //데이터를 화면에 출력해 준다.
                tvMessage.setText(value);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value2 = dataSnapshot.getValue(String.class);
                //데이터를 화면에 출력해 준다.
                tvMessage2.setText(value2);
                Log.d(TAG, "Value is: " + value2);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}


