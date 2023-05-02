package vukan.com.fftd;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FriendHistoryActivity extends AppCompatActivity {

    private Button friendsButton;
    private TextView welcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendhistory);

        friendsButton = findViewById(R.id.friendsButton);
        welcomeMessage = findViewById(R.id.welcome_message);
    }

    public void onFriendsButtonClick(View view) {
        welcomeMessage.setText("Welcome to the Friends History page");
    }
}
