package com.example.test;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener{
	private Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener((OnClickListener) this);
		
	}
	
	public void onClick(View v) {
		if (v.getId() == R.id.button1 ) {
			Intent buttonIntent = new Intent(getApplicationContext(),TestActivity.class);  
			buttonIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
			startActivity(buttonIntent);
			finish();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
