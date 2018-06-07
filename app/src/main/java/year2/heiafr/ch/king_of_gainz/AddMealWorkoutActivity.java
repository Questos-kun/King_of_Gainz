package year2.heiafr.ch.king_of_gainz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

/**
 * Created by samue on 07.06.2018.
 */

public class AddMealWorkoutActivity extends AppCompatActivity {

    private EditText txtMealWorkout;
    private ListView listMealsWorkouts;
    private Switch switchWorkout;

    private static final String APPLICATION_ID = "a5be8181";
    private static final String APPLICATION_KEY = "fb9daaf6fa835748bb99630791583dde";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_add_meal_workout);

        txtMealWorkout = findViewById(R.id.txtMealWorkout);
        listMealsWorkouts = findViewById(R.id.listMealsWorkouts);
        switchWorkout = findViewById(R.id.switchWorkout);

        //TODO : GET ALL THE MEALS / WORKOUTS FOR THE CURRENT DAY

    }
}
