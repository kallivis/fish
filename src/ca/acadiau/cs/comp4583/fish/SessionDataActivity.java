package ca.acadiau.cs.comp4583.fish;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import ca.acadiau.cs.comp4583.fish.data.FishingSession;

public class SessionDataActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.session_data);
		final Button save_session_button = (Button) findViewById(R.id.save_changes_buttons);
		final Button back_session_button = (Button) findViewById(R.id.submit_new_session_data_button);

		final Spinner location_spinner = (Spinner) findViewById(R.id.location_text_spinner);

		ArrayList<String> location_options = new ArrayList<String>();
		location_options.add("Bridgewater");
		location_options.add("Wolfville");

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, location_options);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		location_spinner.setAdapter(dataAdapter);

		Intent thisIntent = getIntent();
		final FishingSession session = (FishingSession) thisIntent
				.getSerializableExtra("Session");
		// location_spinner.set
		int position = dataAdapter.getPosition(session.getLocationName());
		location_spinner.setSelection(position);

		final EditText anglersText = (EditText) findViewById(R.id.num_anglers_text_edit);
		anglersText.setText(Integer.toString(session.getAnglers()));

		final CheckBox anglersEstimatedChbx = (CheckBox) findViewById(R.id.checkBox1);
		anglersEstimatedChbx.setChecked(!session.isExactAnglers());

		int number_of_fish = session.getFish().size();
		final EditText num_catches_Text = (EditText) findViewById(R.id.num_catches_text_edit);
		final EditText num_fish_Text = (EditText) findViewById(R.id.num_fishes_text_edit);
		final CheckBox estimated_catches_chbx = (CheckBox) findViewById(R.id.CheckBox02);
		estimated_catches_chbx.setChecked(!session.isExactCatches());

		if (session.getCatches() == 0) {
			num_catches_Text.setText(Integer.toString(number_of_fish));
			estimated_catches_chbx.setChecked(false);
		} else
			num_catches_Text.setText(Integer.toString(session.getCatches()));
		num_fish_Text.setText(Integer.toString(number_of_fish));

		// System.out.println("CATCHES: " +
		// Integer.toString(session.getCatches()));
		final EditText linesText = (EditText) findViewById(R.id.num_rods_text_edit);
		linesText.setText(Integer.toString(session.getLines()));

		final CheckBox fish_num_edited = (CheckBox) findViewById(R.id.CheckBox01);
		save_session_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						SubmitFishActivity.class);
				session.setLocationName(location_spinner.getSelectedItem()
						.toString());
				// No setAnglers method
				// session.setAnglers(anglersText.getText());
				// No setLines method
				// session.setLines(linesText.getText());
				session.setCatches(Integer.parseInt(num_catches_Text.getText()
						.toString()));
				i.putExtra("Session", session);
				startActivity(i);

			}
		});
		back_session_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						SubmitFishActivity.class);
				i.putExtra("Session", session);
				startActivity(i);

			}
		});
		num_fish_Text.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				fish_num_edited.setChecked(true);

			}
		});

	}

}
