package com.example.ghada.note;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    FirebaseUser user;
    TextView txt;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.textjson);
         database = FirebaseDatabase.getInstance();
         myRef = database.getReference("FirstName");

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            startActivity(new Intent(MainActivity.this, Signin.class));
        }

        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) //to became Data
            {
                String value = dataSnapshot.getValue(String.class);
                txt.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) //To became error
            {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException());
            }
        });


    }


    public void Signout(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(MainActivity.this, Signin.class));
    }

}