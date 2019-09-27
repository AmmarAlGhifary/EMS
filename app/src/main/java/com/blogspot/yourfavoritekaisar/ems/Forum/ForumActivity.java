package com.blogspot.yourfavoritekaisar.ems.Forum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.blogspot.yourfavoritekaisar.ems.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ForumActivity extends AppCompatActivity  {
    private  EditText room_name;
    private String name;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();

    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_rooms = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add_room = findViewById(R.id.btnAdd_room);
        room_name = findViewById(R.id.etNeme_room);
        ListView listView = findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list_of_rooms);
        listView.setAdapter(arrayAdapter);

        request_user_name();
        add_room.setOnClickListener(view -> {

            Map<String,Object> map = new HashMap<>();
            map.put(room_name.getText().toString(),"Testing");
            root.updateChildren(map);

        });
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    set.add((snapshot).getKey());
                }
                list_of_rooms.clear();
                list_of_rooms.addAll(set);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener((adapterView, view, i, l) -> {

            Intent I = new Intent(getApplicationContext(), chatroom.class);
            I.putExtra("room_name",((TextView)view).getText().toString());
            I.putExtra("user_name",name);
            startActivity(I);
        });

    }

    private void request_user_name() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Name");
        final EditText input_field = new EditText(this);
        builder.setView(input_field);
        builder.setPositiveButton("OK ", (dialogInterface, i) -> name = input_field.getText().toString());
        builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
            dialogInterface.cancel();
            request_user_name();
        });
        builder.show();
    }
}