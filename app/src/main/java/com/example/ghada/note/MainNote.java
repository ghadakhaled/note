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

public class MainNote extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    EditText mail, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_note);
        mAuth = FirebaseAuth.getInstance();
        mail = findViewById(R.id.editemail);
        pass = findViewById(R.id.editpassword);
    }
    public void Creat(View view)
    {
        String email = mail.getText().toString();
        String password= pass.getText().toString();
        if (email.length()>0&&password.length()>0)
        {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                // Sign in success, update UI with the signed-in user's information
                                Log.e("Create", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(new Intent(MainNote.this,MainActivity.class));
                            }
                            else
                                {
                                // If sign in fails, display a message to the user.
                                Log.e("Create", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(MainNote.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                }
                        }
                    });
        }
        else
        {
           Toast.makeText(MainNote.this, "Enter Email and password",Toast.LENGTH_SHORT).show();
        }
    }
}