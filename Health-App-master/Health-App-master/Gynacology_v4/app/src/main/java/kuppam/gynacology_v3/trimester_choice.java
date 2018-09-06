package kuppam.gynacology_v3;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import static kuppam.gynacology_v3.updateChoice.selected;

public class trimester_choice extends AppCompatActivity {

    Button bt1,bt2,bt3;
    public static int trimester;
    JSONObject ob;
    String pid = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trimester_choice);
        bt1=(Button)findViewById(R.id.button_t1);
        bt2=(Button)findViewById(R.id.button_t2);
        bt3=(Button)findViewById(R.id.button_t3);
        onBtnt1();
        onBtnt2();
        onBtnt3();
    }

    public void onBtnt1()
    {
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trimester = 1;
                if (selected == "server"){
                    try {
                        Bundle b = getIntent().getExtras();
                        ob = new JSONObject(b.getString("JObj2"));
                        Intent intent = new Intent(getApplicationContext(), tri1_investigation.class);
                        intent.putExtra("type", "obs");
                        intent.putExtra("JObj2", ob.toString());
                        startActivity(intent);
                    }

                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
                else {
                    Bundle b = getIntent().getExtras();
                    pid = b.getString("pid");
                    Intent intent = new Intent(getApplicationContext(), tri1_investigation.class);
                    intent.putExtra("pid",pid);
                    startActivity(intent);
                }
            }
        });
    }

    public void onBtnt2()
    {
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trimester = 2;
                if (selected == "server"){
                    try {
                        Bundle b = getIntent().getExtras();
                        ob = new JSONObject(b.getString("JObj2"));
                        Intent intent = new Intent(getApplicationContext(), tri2_investigation.class);
                        intent.putExtra("type", "obs");
                        intent.putExtra("JObj2", ob.toString());
                        startActivity(intent);
                    }

                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Bundle b = getIntent().getExtras();
                    pid = b.getString("pid");
                    Intent intent = new Intent(getApplicationContext(), tri2_investigation.class);
                    intent.putExtra("pid",pid);
                    startActivity(intent);
                }
            }});
    }

    public void onBtnt3()
    {
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trimester = 3;
                if (selected == "server"){
                    try {
                        Bundle b = getIntent().getExtras();
                        ob = new JSONObject(b.getString("JObj2"));
                        Intent intent = new Intent(getApplicationContext(), tri3_investigation.class);
                        intent.putExtra("type", "obs");
                        intent.putExtra("JObj2", ob.toString());
                        startActivity(intent);
                    }

                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Bundle b = getIntent().getExtras();
                    pid = b.getString("pid");
                    Intent intent = new Intent(getApplicationContext(), tri3_investigation.class);
                    intent.putExtra("pid",pid);
                    startActivity(intent);
                }
            }});
    }
}
