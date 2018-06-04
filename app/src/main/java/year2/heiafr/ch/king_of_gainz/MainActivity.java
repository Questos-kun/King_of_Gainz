package year2.heiafr.ch.king_of_gainz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    private boolean isProfileSetUp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //TODO : check if profile has been set up


        if(isProfileSetUp) {
            setContentView(R.layout.activity_main);
        } else {
            //CHANGE ACTIVITY TO SET UP PROFILE
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        }

    }
}
