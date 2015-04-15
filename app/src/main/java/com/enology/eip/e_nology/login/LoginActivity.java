package com.enology.eip.e_nology.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.enology.eip.e_nology.MainActivity;
import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.EnologyService;
import com.enology.eip.e_nology.api.RestClient;
import com.enology.eip.e_nology.api.json.LoginResponse;
import com.jpardogo.android.googleprogressbar.library.NexusRotationCrossDrawable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/*
 * do_f e-nology
 */

public class LoginActivity extends Activity
{

    public final static String DEBUG_TAG = "Network.debug";
    public final static String WEBSERVICE_HOST = "http://62-210-36-42.rev.poneytelecom.eu/";
    //public final static String WEBSERVICE_HOST = "http://epitech-api.herokuapp.com/login";
    public final static String WEBSERVICE_ROUTES = "eip/client/login/";
    //private final String USER_AGENT = "Mozilla/5.0";
    private EditText email;
    private EditText password;
    private ImageView logo;
    private ProgressBar loading;

    private Animation fadeInAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.edittext_email_address);
        password = (EditText)findViewById(R.id.edittext_password);
        loading = (ProgressBar)findViewById(R.id.google_progress);
        logo = (ImageView)findViewById(R.id.logo);
        loading.setIndeterminateDrawable(new NexusRotationCrossDrawable.Builder(this).build());
        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        ImageButton signin = (ImageButton) findViewById(R.id.button_signin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {/*
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);*/
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected())
                {
                    Log.d(DEBUG_TAG, "Network Info");
                    String login = email.getText().toString();
                    String pwd = toSha1(password.getText().toString());

                    RestClient.get().login(login, pwd, new Callback<LoginResponse>() {
                        @Override
                        public void success(LoginResponse loginResponse, Response response) {
                            // success!
                            Log.d("App", loginResponse.getLogin());

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            // you get the point...
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.d("App", error.getMessage()+" | "+error.getUrl());
                        }
                    });
                    //new LoginTask().execute();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No network connection available.", Toast.LENGTH_SHORT).show(); //
                }
            }
        });
    }

    private class LoginTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings)
        {
            try {
                return downloadUrl();
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            logo.setVisibility(View.INVISIBLE);
            loading.setVisibility(View.VISIBLE);
            //loading.setAnimation(fadeInAnimation);
            //Toast.makeText(getApplicationContext(), "Début du traitement asynchrone", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(String result)
        {
            if (result != null)
            {
                Log.d(DEBUG_TAG, "Result : "+result);
                String login = parseJson(result);
                Log.d(DEBUG_TAG, "LOGIN STRING :"+login+"EQUAL : "+login.equals("true"));
                loading.setVisibility(View.INVISIBLE);
                logo.setVisibility(View.VISIBLE);
                if (login.equals("true"))
                {
                    Log.d(DEBUG_TAG, "NEW ACTIVITY");
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "Login ou Mot de passe invalide", Toast.LENGTH_SHORT).show();
            }
            else {
                loading.setVisibility(View.INVISIBLE);
                logo.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Login ou Mot de passe invalide", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String parseJson(String response)
    {
        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString("login");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Given a URL, establishes an HttpUrlConnection and retrieves
    // the web page content as a InputStream, which it returns as
    // a string.
    private String downloadUrl() throws IOException
    {
        InputStream is = null;
        String sha1 = toSha1(password.getText().toString());
        Log.d(DEBUG_TAG, sha1);
        String urlString = WEBSERVICE_HOST+WEBSERVICE_ROUTES+email.getText().toString()+"/"+sha1;
        //String urlString = WEBSERVICE_HOST;

        try
        {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is);
            Log.d(DEBUG_TAG, "Result : "+contentAsString);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream) throws IOException
    {
        String buffer = "";
        String line = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        while ((line = reader.readLine()) != null)
            buffer += line;

        return new String(buffer);
    }

    public static String toSha1(String string)
    {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            return byteArrayToHexString(md.digest(string.getBytes()));
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String byteArrayToHexString(byte[] b)
    {
        String result = "";
        for (int i=0; i < b.length; i++) {
            result +=
                    Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
    }
}