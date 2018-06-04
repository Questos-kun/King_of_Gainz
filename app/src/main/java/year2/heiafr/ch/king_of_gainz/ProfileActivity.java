package year2.heiafr.ch.king_of_gainz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by samue on 04.06.2018.
 */

public class ProfileActivity extends AppCompatActivity {

    private EditText etAge;
    private Spinner spinnerSex;
    private EditText etHeight;
    private EditText etWeight;
    private Spinner spinnerActivity;

    private int profileId;

    public static final String SEX_MALE = "Male";
    public static final String SEX_FEMALE = "Female";

    public static final String ACTIVITY_LIGHT = "Lightly Active (moderate exercise and sedentary job)";
    public static final String ACTIVITY_MODERATE = "Moderately Active (intense exercise and sedentary job)";
    public static final String ACTIVITY_VERY = "Very Active (moderate exercise and active job)";
    public static final String ACTIVITY_EXTRA = "Extra Active (intense exercise and active job)";

    private int mode; //1 = add, 2 = modify

    private MySQLiteOpenHelper mySQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_set_up_profile);

        mySQLiteHelper = new MySQLiteOpenHelper(this,
                MySQLiteOpenHelper.DATABASE_NAME, null,1);

        //Get all attributes
        etAge = findViewById(R.id.txtAge);
        spinnerSex = findViewById(R.id.spinnerSex);
        etHeight = findViewById(R.id.txtHeight);
        etWeight = findViewById(R.id.txtWeight);
        spinnerActivity = findViewById(R.id.spinnerActivity);

        setUpSpinners();

        Intent intent = getIntent();
        mode = intent.getIntExtra("MODE",1);
        if(mode == 2) {
            showCurrentProfile();
        }
    }

    private void setUpSpinners() {
        //Set up sex spinner
        String[] itemsSex = new String[]{SEX_MALE, SEX_FEMALE};
        ArrayAdapter<String> adapterSex = new ArrayAdapter<String>(this, R.layout.custom_spinner_dropdown_item, itemsSex);
        spinnerSex.setAdapter(adapterSex);

        //Set up activity spinner
        String[] itemsActivity = new String[]{ACTIVITY_LIGHT, ACTIVITY_MODERATE, ACTIVITY_VERY, ACTIVITY_EXTRA};
        ArrayAdapter<String> adapterActivity = new ArrayAdapter<String>(this, R.layout.custom_spinner_dropdown_item, itemsActivity);
        spinnerActivity.setAdapter(adapterActivity);
    }

    public void savePressed(View target){
        try {
            int age = Integer.parseInt(etAge.getText().toString());
            String sex = spinnerSex.getSelectedItem().toString();
            int height = Integer.parseInt(etHeight.getText().toString());
            int weight = Integer.parseInt(etWeight.getText().toString());
            String activity = spinnerActivity.getSelectedItem().toString();
            if(age > 5 && age < 120 && height > 50 && height < 230 && weight > 20 && weight < 300) {
                if(mode == 1) {
                    mySQLiteHelper.addProfile(age, sex, height, weight, activity);
                } else {
                    mySQLiteHelper.modifyProfile(profileId, age, sex, height, weight, activity);
                }
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            } else {
                throw new Exception("impossible values");
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Could not save profile, please check your values", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void showCurrentProfile() {
        Profile currentProfile = mySQLiteHelper.getProfile();
        profileId = currentProfile.getId();
        etAge.setText(Integer.toString(currentProfile.getAge()));
        int sexSpinnerPosition = 0;
        switch(currentProfile.getSex()) {
            case SEX_MALE :
                sexSpinnerPosition = 0;
                break;
            case SEX_FEMALE :
                sexSpinnerPosition = 1;
                break;
        }
        spinnerSex.setSelection(sexSpinnerPosition);
        etHeight.setText(Integer.toString(currentProfile.getHeight()));
        etWeight.setText(Integer.toString(currentProfile.getWeight()));
        int activitySpinnerPosition = 0;
        switch(currentProfile.getActivity()) {
            case ACTIVITY_LIGHT :
                activitySpinnerPosition = 0;
                break;
            case ACTIVITY_MODERATE :
                activitySpinnerPosition = 1;
                break;
            case ACTIVITY_VERY :
                activitySpinnerPosition = 2;
                break;
            case ACTIVITY_EXTRA :
                activitySpinnerPosition = 3;
                break;
        }
        spinnerActivity.setSelection(activitySpinnerPosition);
    }
}
