package com.example.talk2;



import org.droidsoapclient.client.SoapClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Thread networkThread = new Thread() {
			@Override
			public void run() {
				
				 String respons = "no";
				try {
					/*********** DROIDSOAPCLIENT CALL - START **********/
					SoapClient client = new SoapClient(
							"",
							"getAccess",
							"http://rs.kezen/",
							"http://192.168.1.200:25535/RestServices/contactus",
							false); // create
									// cliente
					client.addParameter("login", "too"); // set parameters
					client.addParameter("password", "ttit");
					try {
						 respons = client.executeCallResponse().toString();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // get response
					/*********** DROIDSOAPCLIENT CALL - FINISH **********/
					
					final String retour = respons;
					runOnUiThread(new Runnable() {
						public void run() {
							EditText edit = (EditText) MainActivity.this
									.findViewById(R.id.editText1);
							edit.setText(retour);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		networkThread.start();

		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
