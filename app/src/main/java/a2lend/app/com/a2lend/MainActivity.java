package a2lend.app.com.a2lend;

import android.content.Intent;
import android.print.pdf.PrintedPdfDocument;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText emailEditText,passwordEditText;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        progressBar = (ProgressBar)findViewById(R.id.progressBarlogin);

/*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World! Igbarea ");
      */
        mAuth = FirebaseAuth.getInstance();
    }
    public void register(View view){
        startActivity(new Intent(this,Register.class));
    }

    public void signin(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if(email.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return;
        }
        if(password.isEmpty()) {
            passwordEditText.setError("password is required");
            passwordEditText.requestFocus();
            return;
        }
        if(password.isEmpty()) {
            passwordEditText.setError("password is required");
            passwordEditText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Please enter a valid Email");
            emailEditText.requestFocus();
            return;
        }
        if(password.length()<6){
            passwordEditText.setError("Please enter a long Password ");
            passwordEditText.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // TODO Intent
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "SinginUser:success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this ,Main.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else{
                    if(task.getException() instanceof FirebaseAuthInvalidUserException)
                         Toast.makeText(getApplicationContext(), "SinginUser:failure\n"
                                        + "Invalid Email Exception", Toast.LENGTH_LONG).show();
                    else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                         Toast.makeText(getApplicationContext(), "SinginUser:failure\n"
                                        + "Invalid Credentials Exception", Toast.LENGTH_LONG).show();
                    else Toast.makeText(getApplicationContext(), "SinginUser:failure\n"
                                        + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}