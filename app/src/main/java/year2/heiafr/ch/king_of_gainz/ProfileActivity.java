package year2.heiafr.ch.king_of_gainz;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by samue on 04.06.2018.
 */

public class ProfileActivity extends AppCompatActivity {

    private EditText age;
    private Spinner spinnerSex;
    private EditText height;
    private EditText weight;
    private Spinner activitySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get all attributes
        age = findViewById(R.id.txtAge);
        spinnerSex = findViewById(R.id.spinnerSex);
        height = findViewById(R.id.txtHeight);
        weight = findViewById(R.id.txtWeight);
        activitySpinner = findViewById(R.id.spinnerActivity);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Set up spinners
        ArrayAdapter<CharSequence> adapterSex = ArrayAdapter.createFromResource(this,
                R.array.sex, android.R.layout.simple_spinner_item);
        adapterSex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSex.setAdapter(adapterSex);

        ArrayAdapter<CharSequence> adapterActivity = ArrayAdapter.createFromResource(this,
                R.array.activity, android.R.layout.simple_spinner_item);
        adapterActivity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSex.setAdapter(adapterActivity);

        setContentView(R.layout.activity_set_up_profile);
    }

    public void savePressed(View target){
        /*String title = ti.getText().toString();
        String description = de.getText().toString();
        String date = da.getText().toString();
        String priority = prioritySpinner.getSelectedItem().toString();

        if(mode == 1) {
            mySQLiteHelper.addNewTask(title, description, date, priority);
        } else {
            int id = mySQLiteHelper.getTaskId(title, description, date, priority);
            mySQLiteHelper.modifyTask(id, title, description, date, priority);
        }
        onBackPressed();*/
    }
}
