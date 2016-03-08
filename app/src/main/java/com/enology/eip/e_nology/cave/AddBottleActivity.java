package com.enology.eip.e_nology.cave;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.RestClient;
import com.enology.eip.e_nology.api.json.addBottleToCaveResponse;
import com.enology.eip.e_nology.api.json.getBottleByIdResponse;
import com.enology.eip.e_nology.api.json.object.addBottleToCave.addBottleToCaveBody;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AddBottleActivity extends Activity {

    private static final String ARG_TOKEN = "token";
    private static final String ARG_CAVE = "cave";

    private String  token;
    private String  cave;

    private EditText    name;
    private EditText    cru;
    private EditText    grade;
    private EditText    annee;
    private EditText    prix;
    private EditText    note;
    private DatePicker  date;
    private Button      button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cave_activity_add_bottle);

        Intent intent = getIntent();
        token = intent.getExtras().getString(ARG_TOKEN);
        cave = intent.getExtras().getString(ARG_CAVE);

        name = (EditText) findViewById(R.id.cave_add_name);
        cru = (EditText) findViewById(R.id.cave_add_cru);
        grade = (EditText) findViewById(R.id.cave_add_grade);
        annee = (EditText) findViewById(R.id.cave_add_annee);
        prix = (EditText) findViewById(R.id.cave_add_prix);
        note = (EditText) findViewById(R.id.cave_add_note);
        date = (DatePicker) findViewById(R.id.cave_add_date);
        button = (Button) findViewById(R.id.cave_add_submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBottleToCave(null);
            }
        });
    }

    private void addBottleToCave(getBottleByIdResponse bottle)
    {
        String tmp = (Integer.parseInt(grade.getText().toString()) > 5) ? "5" : grade.getText().toString();
        addBottleToCaveBody body = new addBottleToCaveBody("5563a76f53c13d0b00f54eac",
                tmp,
                prix.getText().toString(),
                "",
                cru.getText().toString(),
                annee.getText().toString(),
                note.getText().toString(),
                name.getText().toString());
        RestClient.getToken(token).addBottleToCave(cave, body, new Callback<addBottleToCaveResponse>() {
            @Override
            public void success(addBottleToCaveResponse addBottleToCaveResponse, Response response) {
                Log.d("ADD BOTTLE ACTIVITY", "addBottleToCave");
                Toast.makeText(getApplicationContext(), "Bouteille Ajoutée", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
