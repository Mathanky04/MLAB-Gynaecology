package kuppam.gynacology_v3;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static kuppam.gynacology_v3.choice.checker1;

public class updateChoice extends AppCompatActivity {

    // private ProgressDialog prDialog;
    ProgressDialog prgDialog;
    SQLiteDatabase database;
    Button newRecord,updateRetrieve,updateRetrieve2;
    EditText addPid;
    public static String selected="";
    public static boolean retrieve = false;
    public static final String[] tables = {"","general_information","doctor_table","obstetric_information",
            "investigations","obstetric_score","past_obstetric_history","history_table",
            "personal_history","general_physical_examination","systemic_examination","first_trimester","second_trimester","third_trimester",
            "gynaec_complaints","gynaec_obstetric_history", "gynaec_past_history","gynaec_family_history","gynaec_personal_history",
            "gynaec_physical_examination","gynaec_systemic_examination", "gynaec_investigations","gynaec_care"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_choice);
        newRecord = (Button)findViewById(R.id.button_record);
        updateRetrieve = (Button)findViewById(R.id.button_update);
        updateRetrieve2 = (Button)findViewById(R.id.button_update2);
        addPid = (EditText)findViewById(R.id.addPid);
        updateRetrieve2.setVisibility(View.GONE);
        addPid.setVisibility(View.GONE);
        prgDialog = new ProgressDialog(this);
        prgDialog.setMessage("Syncing SQLite Data with Remote MySQL DB. Please wait...");
        prgDialog.setCancelable(false);
        //opening db
        database = openOrCreateDatabase("gynaecology", Context.MODE_PRIVATE, null);
        Toast.makeText(getApplicationContext(), "Enter Patient_ID", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {

    }

    public void onNewRecord(View view)
    {
        System.out.println("Here it comes::");
        Intent intent=new Intent(getApplicationContext(),generalinfo.class);
        retrieve = false;
        startActivity(intent);
    }

    public void onUpdateRetreive(View view)
    {
        updateRetrieve.setVisibility(View.GONE);
        addPid.setVisibility(View.VISIBLE);
        updateRetrieve2.setVisibility(View.VISIBLE);
    }
    public void onUpdateRetreive2(View view)
    {
        retrieve = true;
        String selectQuery = "SELECT name FROM sqlite_master WHERE type='table' AND name='general_information';";
        Cursor c = database.rawQuery(selectQuery, null); //check for table existence
        if(c.moveToFirst()) {
            String selectQuery1 = "SELECT * FROM general_information WHERE patient_id ='" + addPid.getText() + "';"; //check for id existence
            Cursor cursor = database.rawQuery(selectQuery1, null);
            System.out.println(cursor.getCount());
            System.out.println(cursor.moveToFirst());
            if (cursor.moveToFirst()) {//cursor.moveToNext();
                selected = "";
                Intent intent = new Intent(getApplicationContext(), generalinfo.class);
                intent.putExtra("pid", addPid.getText().toString());
                startActivity(intent);
            }
            else {
                updateSQLiteMySQLDB();
            }
        }
        else {
            updateSQLiteMySQLDB();
        }
    }

    public void updateSQLiteMySQLDB() {
        //Create AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        if (addPid.getText() != null) {
            prgDialog.show();
            params.put("ID", addPid.getText());
            Log.d("** JSON SENT **", params.toString());
            client.post("http://192.168.43.13/gyno/retreiveUser.php", params, new AsyncHttpResponseHandler() {
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    // System.out.println("hello"+response);
                    prgDialog.hide();
                    System.out.print("Here: ");
                    System.out.println(new String(responseBody));
                    if (responseBody != null && responseBody.length > 0) {
                        try {
                            System.out.println("Entered");
                            //JSONArray arr = new JSONArray(new String(responseBody));
                            JSONObject ob = new JSONObject(new String(responseBody));
                            System.out.println(ob.length());
                            System.out.println(ob.toString());
                            System.out.println("Retreived::" + "Here");
                            /*for (int i = 1; i < ob.length(); i++) {
                                JSONObject obj = (JSONObject) ob.get(tables[i+13]);
                                for (int j = 0; j < obj.length(); j++) {
                                    //System.out.print(" "+obj.get("C"+j));
                                }
                            }*/
                            Toast.makeText(getApplicationContext(), "Retreived!", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplicationContext(),generalinfo.class);
                            selected = "server"; //obstetric
                            //intent.putExtra("type","obs");
                            intent.putExtra("JObj",new String(responseBody));
                            startActivity(intent);
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error Occurred [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    // TODO Auto-generated method stub
                    prgDialog.hide();
                    if (statusCode == 404) {
                        Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                    } else if (statusCode == 500) {
                        Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Unexpected Error occurred! [Most common Error: Device might not be connected to Internet]", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(), "No such Patient_ID found!", Toast.LENGTH_LONG).show();
        }
    }
}
