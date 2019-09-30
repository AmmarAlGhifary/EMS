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

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    TextView registerUser;
    EditText username, password;
    Button loginButton;
    String user, pass;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        registerUser = findViewById(R.id.registerButton);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.register);


        loginButton.setOnClickListener(v -> {
            user = username.getText().toString();
            pass = password.getText().toString();

            if (user.equals("")) {
                username.setError("can't be blank");
            } else if (pass.equals("")) {
                password.setError("can't be blank");
            } else {
                String url = "https://chatapp-60323.firebaseio.com/users.json";
                final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                pd.setMessage("Loading...");
                pd.show();

                StringRequest request = new StringRequest(Request.Method.GET, url, s -> {
                    if (s.equals("null")) {
                        Toast.makeText(LoginActivity.this, "user not found", Toast.LENGTH_LONG).show();
                    } else {
                        try {
                            JSONObject obj = new JSONObject(s);

                            if (!obj.has(user)) {
                                Toast.makeText(LoginActivity.this, "user not found", Toast.LENGTH_LONG).show();
                            } else if (obj.getJSONObject(user).getString("password").equals(pass)) {
                                UsersDetail.username = user;
                                UsersDetail.password = pass;
                                startActivity(new Intent(LoginActivity.this, UserActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "incorrect password", Toast.LENGTH_LONG).show();
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

                RequestQueue rQueue = Volley.newRequestQueue(LoginActivity.this);
                rQueue.add(request);
            }

        });
    }

    @OnClick(R.id.register)
    public void onViewClicked() {
        Intent pindah = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(pindah);
    }
}
