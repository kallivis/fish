package ca.acadiau.cs.comp4583.fish;

import java.util.ArrayList;

import ca.acadiau.cs.comp4583.fish.data.FishingSession;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;

public class NewSessionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_session);
		 final Button new_session_button = (Button) findViewById(R.id.submit_new_session_data_button);
		 final Spinner location_spinner = (Spinner) findViewById(R.id.location_text_spinner);
		 
      	 ArrayList<String> location_options = new ArrayList<String>();
    	 location_options.add("Bridgewater");
		 location_options.add("Wolfville");	  
		 
		 ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                 android.R.layout.simple_spinner_item, location_options);
                 dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                 location_spinner.setAdapter(dataAdapter);
                 
         new_session_button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
      
            //	 EditText locationText = (EditText)findViewById(R.id.location_text_edit);

            //	 String location = locationText.getText().toString();
            	
            	 EditText anglersText = (EditText) findViewById(R.id.num_anglers_text_edit);
            	 int anglers = Integer.parseInt(anglersText.getText().toString());
            	 EditText linesText = (EditText) findViewById(R.id.num_rods_text_edit);
            	 int lines = Integer.parseInt(linesText.getText().toString());
            	 double longitude = Double.NaN;
            	 double latitude = Double.NaN;
            	 //Long does not have NaN, or Null using MIN_VALUE as that, as it can easily be checked and won't be used.
            	 long start_time = Long.MIN_VALUE;
            	 long end_time = Long.MIN_VALUE;
            	 FishingSession session = new FishingSession("", latitude, longitude, start_time,end_time, anglers, lines);
            	 CheckBox anglerEstimateChbx = (CheckBox) findViewById(R.id.checkBox1);
            	 boolean anglerEstimate = anglerEstimateChbx.isChecked();
            	 session.setExactAnglers(!anglerEstimate);
            	 session.setLocationName(location_spinner.getSelectedItem().toString());
            	 Intent i = new Intent(getApplicationContext(),SubmitFishActivity.class);
            	 i.putExtra("Session", session);
            	 startActivity(i);
             }
         });
      
     }
	

	
}
