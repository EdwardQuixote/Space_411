package com.projects.NASAInternationalSpaceApps2014.space411;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.transform.Result;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Space411 extends Activity {
	
	ImageView imgSpace411;
	Button btnStartLoading;
	
	String imgUrl;
	
	ConnectivityManager conmgrSpace411;
	NetworkInfo netinfSpace411;
	HttpURLConnection conSpace411; 
	
	URL space411URL;
	
	InputStream isSpace411;
	
	Bitmap bmpSpace411;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.space411_activity_main);
    }
    
    /**
     * Here\'s where I initialise my Variables and UI Objects:
     */
    public void initialiseVariablesAndObjects() {
    	
    	imgSpace411 = (ImageView)findViewById(R.id.imgSpace411);
    	btnStartLoading = (Button)findViewById(R.id.btnStartLoading);
    	
    }
    
    /**
     * Here\'s what happens when user click btnStartLoading:
     * An AsyncTask is called to do Network Connections:
     */
    public void onBtnStartLoadingClick(View view) {
    	
    	imgUrl = "http://www.data.nasa.gov/api_info";
    	
    	conmgrSpace411 = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    	netinfSpace411 = conmgrSpace411.getActiveNetworkInfo();
    	
    	if (netinfSpace411 != null && netinfSpace411.isConnected()) {
    		
            new getNASASpace411Images().execute(imgUrl);
            
        } else {
        	
        	Toast.makeText(Space411.this, R.string.tstConError2, Toast.LENGTH_LONG).show();
        	
        }

    }
    
    /**
     * This is the AsyncTask being called above in method onBtnStartLoadingClick()
     */
    private class getNASASpace411Images extends AsyncTask<String, Void, Bitmap> {

    	@Override
		protected Bitmap doInBackground(String... arg0) {
			
			try {
				
		        space411URL = new URL(imgUrl);
		        conSpace411 = (HttpURLConnection) space411URL.openConnection();
		        conSpace411.setRequestMethod("GET");
		        conSpace411.setDoInput(true);
		        conSpace411.connect();
		        
		        bmpSpace411 = BitmapFactory.decodeStream(isSpace411);
			
			} catch (Exception e) {
				
				e.getMessage();
				Toast.makeText(Space411.this, R.string.tstConError1, Toast.LENGTH_LONG).show();
				
			} finally {
			
				if (isSpace411 != null) {
					
		            try {
		            	// CLose connection
						isSpace411.close();
						
					} catch (IOException e) {
						
						e.getMessage();
						Toast.makeText(Space411.this, R.string.tstConError1, Toast.LENGTH_LONG).show();
						
					}
		            
				}
			}
			
			return null;
		}
		
		protected Bitmap onPostExecute(Result bitmap) {
			
			// Here we need to set The Bitmap to the ImageView
			try {
			
				imgSpace411.setImageBitmap(bmpSpace411);
				
			} catch (Exception e) {
				
				e.getMessage();
				Toast.makeText(Space411.this, R.string.tstBitmapError, Toast.LENGTH_LONG).show();
				
			}
			
			return bmpSpace411;
		}
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.space411, menu);
        return true;
    }

}
