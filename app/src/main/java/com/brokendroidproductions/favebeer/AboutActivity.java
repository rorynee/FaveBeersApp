package com.brokendroidproductions.favebeer;

import android.app.Activity;
import android.os.Bundle;


/**
 * About Activity - Loaded when the About Activity is called
 * This method looks after the about pop up box called on a menu button press
 * No pop up menu required here. There is no user interaction here.
 *
 *
 */
public class AboutActivity extends Activity {

    /*
	 * Used When the Activity is loaded
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }


}
