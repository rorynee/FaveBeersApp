package com.brokendroidproductions.favebeer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {

    Button byes, bno;
    ImageView displayImage;
    TextView imageText, countBeer;
    int beerCounter = 0;
    final ArrayList<String> finalResults = new ArrayList<String>();
    ListView resultList;
    public static final String ENDPOINT = "http://api.brewerydb.com/";
    public static final String NO = "No";
    public static final String YES = "YES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Declarations
        displayImage = (ImageView)findViewById(R.id.beer_pic);
        imageText = (TextView)findViewById(R.id.imageText_id);
        countBeer = (TextView)findViewById(R.id.count_id);
        byes = (Button)findViewById(R.id.by_id);
        bno = (Button)findViewById(R.id.bn_id);

        // Yes/like Button Click Listener
        byes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "Yes Pressed", Toast.LENGTH_LONG).show();
                requestData(YES);
                //Log.i("Fave Beer Logger", "After requestDate call - Yes Button");
            }
        });

        // No/Dislike Button Click Listener
        bno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "No Pressed", Toast.LENGTH_LONG).show();
                requestData(NO);
                //Log.i("Fave Beer Logger", "After requestDate call - No Button");
            }
        });

        //Toast.makeText(MainActivity.this, "Started", Toast.LENGTH_LONG).show();
        requestData(NO);
        countBeer.setText("Beer Count: " + beerCounter);

    }

    /**
     * This method call the database and gets back data and show in on screen.
     * This uses update display to change the view on the app
     *
     */
    private void requestData(final String option){

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(ENDPOINT).build();

        BeerApi api = adapter.create(BeerApi.class);

        api.getRandomBeer(new Callback<Beer>() {
            @Override
            public void success(Beer beer, Response response) {

                // Check which button was pressed
                if(option.equals(YES)){
                    beerCounter++;
                    finalResults.add(beer.getData().getName());
                    countBeer.setText("Beer Count: "+beerCounter);
                }

                // If the user has liked 10 beers show them the results
                if(finalResults.size() == 10){

                    // create a ListView and add the adapter to show the results
                    resultList = new ListView(MainActivity.this);
                    resultList.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, finalResults));
                    // Show the Alert Dialog box
                    new AlertDialog.Builder(MainActivity.this, AlertDialog.THEME_HOLO_DARK)
                            .setTitle("Fave Beer - Results")
                            .setView(resultList)
                            .setPositiveButton("Go Again", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Crear the results
                                    finalResults.clear();
                                    resultList.invalidateViews();
                                    beerCounter = 0;
                                    countBeer.setText("Beer Count: "+beerCounter);

                                }

                            })
                            .setNegativeButton("End Game",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Shut down the app
                                    finish();

                                }

                            })
                            .create()
                            .show();

                }
                //Log.i("Fave Beer Logger", "Before updateDisplay call - in requestDate - name - " + beer.getData().getName());
                //Log.i("Fave Beer Logger", "Before updateDisplay call - in requestDate - url - " + beer.getData().getLabels().image_url);
                updateDisplay(beer);
                //Log.i("Fave Beer Logger", "After updateDisplay call - in requestDate");

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    /**
     * This method updateds the display
     * @param currentbeer - This is a Beer object
     */
    protected void updateDisplay(Beer currentbeer){

        // Set the text under the image
        imageText.setText(currentbeer.getData().getName());

        // Set the image
        Picasso.with(this).load(currentbeer.getData().getLabels().image_url)
                .placeholder(R.drawable.imageloading)
                .error(R.drawable.noimage)
                .into(displayImage);

    }


    /*
	 * This method describes what happens when the back button is pressed.
	 * This opens a dialog box check to make sure the user wants to exit.
	 * (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    /*
     * Creates the Menu
     * (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*
     * On Options Item Select for the menu.
     * (non-Javadoc)
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_about) {
            Intent intent_about = new Intent(this, AboutActivity .class);
            startActivity(intent_about);
            return true;
        } else if (itemId == R.id.menu_dev) {
            Intent intent_dev = new Intent(this, DeveloperActivity .class);
            startActivity(intent_dev);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }




}
