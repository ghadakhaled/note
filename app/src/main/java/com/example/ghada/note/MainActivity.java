package com.example.ghada.note;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null)
        {
            startActivity(new Intent(MainActivity.this,Signin.class));
        }
    }


    public void Signout(View view)
    {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(MainActivity.this,Signin.class));
    }
}
