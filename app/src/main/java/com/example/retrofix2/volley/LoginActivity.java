package com.example.retrofix2.volley;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.retrofix2.MainActivity;
import com.example.retrofix2.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText etName, etPassword;
    ProgressBar progressBar;
    @SuppressLint("MissingInflatedId")
    @Override
 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        progressBar = findViewById(R.id.progressBar);
        etName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etUserPassword);
        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
        findViewById(R.id.tvRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));

            }
        });
    }
    private void userLogin() {
        final String username =etName.getText().toString();
        final String password= etPassword.getText().toString();
        if (TextUtils.isEmpty(username)) {
            etName.setError("Please enter your username");
            etName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter your password");
            etPassword.requestFocus();
            return;
        }
        StringRequest stringRequest =new StringRequest(Request.Method.POST, contants.URL_LOGIN,
                new Response. Listener<String>() {
                    @Override
                    public void onResponse (String response) {
                        progressBar.setVisibility(View.GONE);
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString( "message"), Toast.LENGTH_SHORT).show();
                                JSONObject userJson= obj.getJSONObject("user");
                                //creating a new user object
                                User user = new User(
                                userJson.getInt("id"),
                                userJson.getString("username"),
                                userJson.getString( "email"),
                                userJson.getString( "gender"),
                                userJson.getString( "images")
);

                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                finish();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString( "message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response. ErrorListener() {
                    @Override
                    public void onErrorResponse (VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        VolleySingle.getInstance(this).addToRequestQueue (stringRequest);
    }

}