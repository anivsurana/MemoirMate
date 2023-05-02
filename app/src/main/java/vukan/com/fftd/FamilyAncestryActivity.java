package vukan.com.fftd;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FamilyAncestryActivity extends AppCompatActivity {

    private Button familyButton;
    private TextView welcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familyancestry);

        familyButton = findViewById(R.id.familyButton);
        welcomeMessage = findViewById(R.id.welcome_message);
    }

    public void onFamilyButtonClick(View view) {
        welcomeMessage.setText("Welcome to the Family Ancestry page");
    }
}
