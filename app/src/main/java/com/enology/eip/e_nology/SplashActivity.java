package com.enology.eip.e_nology;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.activeandroid.ActiveAndroid;
import com.enology.eip.e_nology.orm.model.Domain;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActiveAndroid.initialize(this);

        new PrefetchData().execute();
    }

    private class PrefetchData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //Domain domain = new Domain();
            //domain.

            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);

            // close this activity
            finish();
        }

    }
}
