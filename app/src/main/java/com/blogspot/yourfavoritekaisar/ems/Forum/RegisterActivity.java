package com.blogspot.yourfavoritekaisar.ems.Forum;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.yourfavoritekaisar.ems.R;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {
    EditText username, password;
    Button registerButton;
    String user, pass;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        registerButton = findViewById(R.id.registerButton);
        login = findViewById(R.id.RegisterLogin);

        Firebase.setAndroidContext(this);

        login.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));

        registerButton.setOnClickListener(v -> {
            user = username.getText().toString();
            pass = password.getText().toString();

            if (user.equals("")) {
                username.setError("can't be blank");
            } else if (pass.equals("")) {
                password.setError("can't be blank");
            } else if (!user.matches("[A-Za-z0-9]+")) {
                username.setError("only alphabet or number allowed");
            } else if (user.length() < 5) {
                username.setError("at least 5 characters long");
            } else if (pass.length() < 5) {
                password.setError("at least 5 characters long");
            } else {
                final ProgressDialog pd = new ProgressDialog(RegisterActivity.this);
                pd.setMessage("Loading...");
                pd.show();

                String url = "https://chatapp-60323.firebaseio.com/users.json";

                StringRequest request = new StringRequest(Request.Method.GET, url, s -> {
                    Firebase reference = new Firebase("https://chatapp-60323.firebaseio.com/users");

                    if (s.equals("null")) {
                        reference.child(user).child("password").setValue(pass);
                        Toast.makeText(RegisterActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                    } else {
                        try {
                            JSONObject obj = new JSONObject(s);

                            if (!obj.has(user)) {
                                reference.child(user).child("password").setValue(pass);
                                Toast.makeText(RegisterActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "username already exists", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    pd.dismiss();
                }, volleyError -> {
                    System.out.println("" + volleyError);
                    pd.dismiss();
                });

                RequestQueue rQueue = Volley.newRequestQueue(RegisterActivity.this);
                rQueue.add(request);
            }
        });
    }

    @OnClick(R.id.RegisterLogin)
    public void onViewClicked() {
        Intent pindah = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(pindah);
    }
}
