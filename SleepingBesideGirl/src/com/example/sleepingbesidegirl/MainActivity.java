package com.example.sleepingbesidegirl;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String SETTING_ALARM_WORDS = "アラームを設定しました";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView currentTimeLabel = (TextView) findViewById(R.id.current_time_label);
		currentTimeLabel.setText(Constants.CURRENT_TIME_LABEL);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void setAlarmTime(View v) {

		TimePicker timePicker = (TimePicker) findViewById(R.id.time_picker);

		setAlarm(timePicker);

		displaySetAlarmText(timePicker);

		playGoodNightVoice();
	}

	private void setAlarm(TimePicker timePicker){
		AlarmManager alarmManager = (AlarmManager) getApplicationContext()
				.getSystemService(Context.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, setStartTime(timePicker),
				setPendingIntent());		
	}
	private PendingIntent setPendingIntent() {
		return PendingIntent.getBroadcast(getApplicationContext(), 0,
				new Intent(Constants.ALARM), PendingIntent.FLAG_UPDATE_CURRENT);
	}

	private void displaySetAlarmText(TimePicker timePicker) {
		StringBuffer sb = new StringBuffer();
		sb = getTimeFromTimePicker(sb, timePicker);
		sb.append(SETTING_ALARM_WORDS);
		Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
	}

	private long setStartTime(TimePicker timePicker) {
		Calendar startTime = Calendar.getInstance();
		startTime.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
		startTime.set(Calendar.MINUTE, timePicker.getCurrentMinute());
		startTime.set(Calendar.SECOND, 0);
		return startTime.getTimeInMillis();
	}

	private StringBuffer getTimeFromTimePicker(StringBuffer sb, TimePicker tp) {
		sb.append(Integer.toString(tp.getCurrentHour()));
		sb.append(Constants.SEMI_COLON);
		sb.append(Integer.toString(tp.getCurrentMinute()));
		return sb;
	}

	private void playGoodNightVoice() {
		MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),
				R.raw.good_night);
		mediaPlayer.start();
	}

}