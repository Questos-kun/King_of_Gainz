package year2.heiafr.ch.king_of_gainz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;
import okhttp3.*;
/**
 * Created by samue on 07.06.2018.
 */

public class AddMealWorkoutActivity extends AppCompatActivity {

    private EditText txtMealWorkout;
    private ListView listMealsWorkouts;
    private Switch switchWorkout;
    private Button btnSubmit;
    private TextView txtCurrentDate;

    private MySQLiteOpenHelper mySQLiteHelper;

    private static final String APPLICATION_ID = "a5be8181";
    private static final String APPLICATION_KEY = "fb9daaf6fa835748bb99630791583dde";

    public static final String API_MEAL_ENDPOINT = "https://trackapi.nutritionix.com/v2/natural/nutrients";
    public static final String API_WORKOUT_ENDPOINT = "https://trackapi.nutritionix.com/v2/natural/exercise";
    public static final String QUERY_PARAMETER = "query";
    public static final String WORKOUT_GENDER_PARAMETER = "gender";
    public static final String WORKOUT_WEIGHT_PARAMETER = "weight_kg";
    public static final String WORKOUT_HEIGHT_PARAMETER = "height_cm";
    public static final String WORKOUT_AGE_PARAMETER = "age";

    public static final String DATE_PATTERN = "dd.MM.yyyy";

    private String gender;
    private int weight;
    private int height;
    private int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_meal_workout);

        txtMealWorkout = findViewById(R.id.txtMealWorkout);
        listMealsWorkouts = findViewById(R.id.listMealsWorkouts);
        switchWorkout = findViewById(R.id.switchWorkout);
        btnSubmit = findViewById(R.id.btnSubmitActivity);
        txtCurrentDate = findViewById(R.id.txtCurrentDate);

        Intent intent = getIntent();
        gender = intent.getStringExtra("gender");
        weight = intent.getIntExtra("weight", 80);
        height = intent.getIntExtra("height", 180);
        age = intent.getIntExtra("age", 21);

        mySQLiteHelper = new MySQLiteOpenHelper(this,
                MySQLiteOpenHelper.DATABASE_NAME, null,1);

        Date currentDay = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN);
        String formattedDate = df.format(currentDay);
        txtCurrentDate.setText(formattedDate);
        ArrayList<Activity> activityList = mySQLiteHelper.getActivityForDate(formattedDate);
        ActivityAdapter adapter = new ActivityAdapter(this, R.layout.activity_layout, activityList);
        listMealsWorkouts.setAdapter(adapter);
        listMealsWorkouts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO : open small pop-up showing every information (fat, prots, carbs...
            }
        });

    }

    public void sendRequestToApi(View view) {
        final boolean isWorkout = switchWorkout.isChecked();
        String apiEndPoint = API_MEAL_ENDPOINT;
        if(switchWorkout.isChecked()) {
            apiEndPoint = API_WORKOUT_ENDPOINT;
        }

        //SET REQUEST PARAMETERS
        final String request = txtMealWorkout.getText().toString();
        Map<String, Object> jsonValues = new HashMap<>();
        JSONObject postParams;
       // try {
            jsonValues.put(QUERY_PARAMETER, request);
            if(isWorkout) {
                jsonValues.put(WORKOUT_GENDER_PARAMETER, gender);
                jsonValues.put(WORKOUT_WEIGHT_PARAMETER, weight);
                jsonValues.put(WORKOUT_HEIGHT_PARAMETER, height);
                jsonValues.put(WORKOUT_AGE_PARAMETER, age);
            }
            postParams = new JSONObject(jsonValues);



        try {
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            OkHttpClient client = new OkHttpClient();

            okhttp3.RequestBody body = RequestBody.create(JSON, postParams.toString());
            okhttp3.Request query = new okhttp3.Request.Builder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-app-id", APPLICATION_ID)
                    .addHeader("x-app-key", APPLICATION_KEY)
                    .url(apiEndPoint)
                    .post(body)
                    .build();
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("SENDING REQUEST : "+postParams.toString());
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------------");
            client.newCall(query).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, final okhttp3.Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }
                    try {
                        JSONObject res = new JSONObject(response.body().string());
                        System.out.println(res.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
