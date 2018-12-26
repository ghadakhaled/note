package com.example.ghada.note;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText mail, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mAuth = FirebaseAuth.getInstance();
        mail = findViewById(R.id.editTextName);
        pass = findViewById(R.id.editText2password);
    }
    public void SginIn(View view)
    {
        String email = mail.getText().toString();
        String password = pass.getText().toString();
        if (email.length() > 0 && password.length() > 0)
        {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                // Sign in success, update UI with the signed-in user's information
                                Log.e("Signin", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }
                            else
                                {
                                // If sign in fails, display a message to the user.
                                Log.e("Signin", "signInWithEmail:failure", task.getException());
                                Toast.makeText(Signin.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else
            {
            Toast.makeText(Signin.this, "Enter Email and password", Toast.LENGTH_SHORT).show();
            }
    }
    public void Acount_Creat(View view)
    {
        startActivity(new Intent(Signin.this,MainNote.class));
    }
}
