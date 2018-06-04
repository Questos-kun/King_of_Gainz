package year2.heiafr.ch.king_of_gainz;

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
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_set_up_profile);

        //Get all attributes
        age = findViewById(R.id.txtAge);
        spinnerSex = findViewById(R.id.spinnerSex);
        height = findViewById(R.id.txtHeight);
        weight = findViewById(R.id.txtWeight);
        activitySpinner = findViewById(R.id.spinnerActivity);

        setUpSpinners();
    }

    private void setUpSpinners() {
        String[] itemsSex = new String[]{"Male", "Female"};
        ArrayAdapter<String> adapterSex = new ArrayAdapter<String>(this, R.layout.custom_spinner_dropdown_item, itemsSex);
        spinnerSex.setAdapter(adapterSex);

        String[] itemsActivity = new String[]{"Lightly Active (moderate exercise and sedentary job)",
                "Moderately Active (intense exercise and sedentary job)",
                "Very Active (moderate exercise and active job)",
                "Extra Active (intense exercise and active job)"
        };
        ArrayAdapter<String> adapterActivity = new ArrayAdapter<String>(this, R.layout.custom_spinner_dropdown_item, itemsActivity);
        activitySpinner.setAdapter(adapterActivity);
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
