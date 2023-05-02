package vukan.com.fftd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



        import androidx.annotation.NonNull;

import android.content.Intent;

import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;

import com.google.firebase.firestore.FirebaseFirestore;



import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Member extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signupEmail, signupPassword, accessCode, fullName, user_Name;
    private static final String TAG = "MyApp/";
    private Button signupButton;
    private TextView loginRedirectText;
    String organizerID;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        accessCode = findViewById(R.id.access_code_mem);
        fullName = findViewById(R.id.full_name);
        user_Name = findViewById(R.id.phone_num);
        fstore = FirebaseFirestore.getInstance();


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signupEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();
                String full_name_mem = fullName.getText().toString().trim();
                String user_name_member = user_Name.getText().toString().trim();
                String access_code = accessCode.getText().toString().trim();

                if (email.isEmpty()) {
                    signupEmail.setError("Email cannot be empty");
                }
                if (pass.isEmpty()) {
                    signupPassword.setError("Password cannot be empty");
                }
                if (full_name_mem.isEmpty()) {
                    fullName.setError("Name cannot be empty");
                }
                if (user_name_member.isEmpty()) {
                    user_Name.setError("Username cannot be empty");
                }

                else {
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                organizerID = auth.getCurrentUser().getUid();
                                DocumentReference documentReference = fstore.collection("MemberData").document(organizerID);
                                Map<String,Object> user = new HashMap<>();
                                user.put("fName",full_name_mem);
                                user.put("email",email);
                                user.put("access",access_code);
                                user.put("uName",user_name_member);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        //Log.d(TAG,"onSuccess: user profile is created for"+userID);
                                    }
                                });
                                Toast.makeText(Member.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Member.this, LoginActivity.class));
                            } else {
                                Toast.makeText(Member.this, "SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Member.this, LoginActivity.class));
            }
        });
    }
}



