package vukan.com.fftd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

//import kotlin.Suppress;


public class Organizer extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signupEmail, signupPassword;
    private Button signupButton;
    private TextView loginRedirectText,accessCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer);

        auth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.signup_Oemail);
        signupPassword = findViewById(R.id.signup_Opassword);
        signupButton = findViewById(R.id.signup_Obutton);
        loginRedirectText = findViewById(R.id.loginRedirectOText);
        accessCode = findViewById(R.id.access_code);
        Random rand = new Random();
        int val = rand.nextInt(1000000);
        accessCode.setText("Access Code = "+Integer.toString(val));


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = signupEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();



                if (user.isEmpty())
                {
                    signupEmail.setError("Email cannot be empty");
                }
                if (pass.isEmpty()){
                    signupPassword.setError("Password cannot be empty");
                } else{
                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Organizer.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Organizer.this, LoginActivity.class));
                            } else {
                                Toast.makeText(Organizer.this, "SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Organizer.this, LoginActivity.class));
            }
        });

    }
}