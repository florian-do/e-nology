package com.enology.eip.e_nology.cave.nfc;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.RestClient;
import com.enology.eip.e_nology.api.json.addBottleToCaveResponse;
import com.enology.eip.e_nology.api.json.getBottleByIdResponse;
import com.enology.eip.e_nology.api.json.object.addBottleToCave.addBottleToCaveBody;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class NfcActivity extends Activity
{
    private static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1NjI2MWU5YzhiNDJmMTBiMDBjZGJiYWQiLCJzYWx0IjoiJDJhJDEwJGdnL0gvTVQvMGNOeUlWUXNIMnJZdXUiLCJkaXNwbGF5TmFtZSI6ImZsb3JpYW4gZG8iLCJwcm92aWRlciI6ImxvY2FsIiwicGFzc3dvcmQiOiIkMmEkMTAkZ2cvSC9NVC8wY055SVZRc0gycll1dWRKSkhLdFpDMEQ3ekJvcWNhb3haaE9scmNtQmFqdksiLCJ1c2VybmFtZSI6ImNoYXQiLCJfX3YiOjAsImNyZWF0ZWQiOiIyMDE1LTEwLTIwVDEwOjU5OjQwLjQzN1oiLCJyb2xlcyI6WyJhZG1pbiJdLCJlbWFpbCI6ImZsb3JpYW4uZG9AZXBpdGVjaC5ldSIsImxhc3ROYW1lIjoiZG8iLCJmaXJzdE5hbWUiOiJmbG9yaWFuIn0.xomNcLk3GwWAef7g9IxFCA7-zWPEgOFuTzlh0LtgLcE";
    private static final String CAVE = "562794442b86df0b0020eaf7"; //"55eaf0eab79fae0b00eee97f";
    public static final String TAG = "NfcDemo";

    private final String[][] techList = new String[][] {
            new String[] {
                    NfcA.class.getName(),
                    NfcB.class.getName(),
                    NfcF.class.getName(),
                    NfcV.class.getName(),
                    IsoDep.class.getName(),
                    MifareClassic.class.getName(),
                    MifareUltralight.class.getName(), Ndef.class.getName()
            }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cave_activity_nfc);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // creating pending intent:
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        // creating intent receiver for NFC events:
        IntentFilter filter = new IntentFilter();
        filter.addAction(NfcAdapter.ACTION_TAG_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_TECH_DISCOVERED);
        // enabling foreground dispatch for getting intent from NFC event:
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, new IntentFilter[]{filter}, this.techList);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // disabling foreground dispatch:
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED))
        {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            new NdefReaderTask().execute(tag);
        }
    }

    private class NdefReaderTask extends AsyncTask<Tag, Void, String>
    {
        @Override
        protected String doInBackground(Tag... params) {
            Tag tag = params[0];

            Ndef ndef = Ndef.get(tag);
            if (ndef == null) {
                // NDEF is not supported by this Tag.
                return null;
            }

            NdefMessage ndefMessage = ndef.getCachedNdefMessage();

            if (ndefMessage == null)
                return null;
            NdefRecord[] records = ndefMessage.getRecords();
            for (NdefRecord ndefRecord : records) {
                if (ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
                    try {
                        return readText(ndefRecord);
                    } catch (UnsupportedEncodingException e) {
                        Log.e(TAG, "Unsupported Encoding", e);
                    }
                }
            }

            return null;
        }

        private String readText(NdefRecord record) throws UnsupportedEncodingException {
        /*
         * See NFC forum specification for "Text Record Type Definition" at 3.2.1
         *
         * http://www.nfc-forum.org/specs/
         *
         * bit_7 defines encoding
         * bit_6 reserved for future use, must be 0
         * bit_5..0 length of IANA language code
         */

            byte[] payload = record.getPayload();

            // Get the Text Encoding
            String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";

            // Get the Language Code
            int languageCodeLength = payload[0] & 0063;

            // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");
            // e.g. "en"

            // Get the Text
            return new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                addBottleFromNfc(result);
            }
            else
                Toast.makeText(getApplicationContext(), "Bouteille Invalide", Toast.LENGTH_SHORT).show();
        }
    }

    private void addBottleFromNfc(String content)
    {
        RestClient.getToken(TOKEN).getBottleById(content, new Callback<getBottleByIdResponse>() {
            @Override
            public void success(getBottleByIdResponse getBottleByIdResponse, Response response) {
                Log.d(TAG, "getBottleByIdResponse");
                addBottleToCave(getBottleByIdResponse);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void addBottleToCave(getBottleByIdResponse bottle)
    {
        addBottleToCaveBody body = new addBottleToCaveBody(bottle.getDomain().getId(),
                bottle.getGrade(),
                bottle.getPrice(),
                bottle.getGrapetype(),
                bottle.getCru(),
                bottle.getYear(),
                bottle.getDesc(),
                bottle.getName());
        RestClient.getToken(TOKEN).addBottleToCave(CAVE, body, new Callback<addBottleToCaveResponse>() {
            @Override
            public void success(addBottleToCaveResponse addBottleToCaveResponse, Response response) {
                Log.d(TAG, "addBottleToCave");
                Toast.makeText(getApplicationContext(), "Bouteille Ajoutée", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


}
