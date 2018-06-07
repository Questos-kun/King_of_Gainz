package year2.heiafr.ch.king_of_gainz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    private MySQLiteOpenHelper mySQLiteHelper;
    private boolean isProfileSetUp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //TODO : check if profile has been set up
        mySQLiteHelper = new MySQLiteOpenHelper(this,
                MySQLiteOpenHelper.DATABASE_NAME, null,1);

        isProfileSetUp = mySQLiteHelper.isProfileSetUp();

        if(isProfileSetUp) {
            setContentView(R.layout.activity_main);
        } else {
            //CHANGE ACTIVITY TO SET UP PROFILE
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("MODE", 1);
            startActivity(intent);
        }

    }

    public void addMealOrActivity(View view) {
        Intent intent = new Intent(MainActivity.this, AddMealWorkoutActivity.class);
        startActivity(intent);
    }

    public void openStats(View view) {
    }

    public void showAbout(View view) {
    }

    public void editProfile(View view) {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        intent.putExtra("MODE", 2);
        startActivity(intent);
    }
}
