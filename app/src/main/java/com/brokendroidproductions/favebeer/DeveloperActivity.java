package com.brokendroidproductions.favebeer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


/**
 * Development Activity - Loaded when the Development Activity is called
 * This method looks after the Development pop up box called on a menu button press
 * No pop up menu required here. This screen shows the developers details.
 * There is no user interaction here.
 *
 */
public class DeveloperActivity extends Activity {

    /*
 * Used When the Activity is loaded
 * (non-Javadoc)
 * @see android.app.Activity#onCreate(android.os.Bundle)
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
    }
}
