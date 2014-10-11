package com.example.sleepingbesidegirl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

public class AlarmRinging extends BroadcastReceiver {
	private static final String TAG = AlarmRinging.class.getSimpleName();


	@Override
	public void onReceive(Context context, Intent intent) {
		ringAlarm(context);
	}

	public void ringAlarm(Context context) {
		
		MediaPlayer mediaPlayer = MediaPlayer.create(context,
				R.raw.good_morning);
		mediaPlayer.start();

	}

}
