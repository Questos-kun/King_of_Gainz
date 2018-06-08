package year2.heiafr.ch.king_of_gainz;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by samue on 08.06.2018.
 */

public class ActivityAdapter extends ArrayAdapter<Activity> {

    private ArrayList<Activity> activityList;
    private Context context;
    private int viewRes;
    private Resources res;

    public ActivityAdapter(Context context, int textViewResourceId,
                           ArrayList<Activity> activities) {
        super(context, textViewResourceId, activities);
        this.activityList = activities; // data source
        this.context = context; // activity
        this.viewRes = textViewResourceId; // cell
        this.res = context.getResources(); // res.values.string
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRes, parent, false);
        }
        final Activity activity = activityList.get(position);
        if (activity != null) {
            final TextView activityType = view.findViewById(R.id.activity_type);
            final TextView activityName = view.findViewById(R.id.activity_name);
            final TextView activityQuantity = view.findViewById(R.id.activity_quantity);
            final TextView activityCalories = view.findViewById(R.id.activity_calories);
            activityType.setText(activity.getType());
            activityName.setText(activity.getName());
            activityQuantity.setText(activity.getQuantity());
            activityCalories.setText(activity.getCalories());
        }
        return view;
    }

    public void updateTaskList(ArrayList<Activity> taskList){
        this.activityList = taskList;
    }
}
