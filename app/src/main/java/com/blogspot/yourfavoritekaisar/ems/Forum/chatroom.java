package com.blogspot.yourfavoritekaisar.ems.Forum;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blogspot.yourfavoritekaisar.ems.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class chatroom extends AppCompatActivity  {

    private EditText input_msg;
    private TextView chat_conversation;
    private String user_name;
    private DatabaseReference root;
    private String temp_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        Button btn_send_msg = findViewById(R.id.button);
        input_msg = findViewById(R.id.editText);
        chat_conversation = findViewById(R.id.textView);
        user_name = Objects.requireNonNull(Objects.requireNonNull(getIntent().getExtras()).get("user_name")).toString();
        String room_name = Objects.requireNonNull(getIntent().getExtras().get("room_name")).toString();
        setTitle("Room - "+ room_name);

        root = FirebaseDatabase.getInstance().getReference().child(room_name);

        btn_send_msg.setOnClickListener(view -> {

            Map<String,Object> map = new HashMap<>();
            temp_key = root.push().getKey();
            root.updateChildren(map);

            DatabaseReference message_root = root.child(temp_key);
            Map<String,Object> map2 = new HashMap<>();
            map2.put("name",user_name);
            map2.put("msg",input_msg.getText().toString());

            message_root.updateChildren(map2);

        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {

                append_chat_conversatin(dataSnapshot);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {
                append_chat_conversatin(dataSnapshot);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void append_chat_conversatin(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();
        while (i.hasNext())
        {
            String chat_msg = (String) ((DataSnapshot) i.next()).getValue();
            String chat_user_name = (String) ((DataSnapshot) i.next()).getValue();

            chat_conversation.append(chat_user_name + " : "+ chat_msg +"\n");

        }
    }
}
