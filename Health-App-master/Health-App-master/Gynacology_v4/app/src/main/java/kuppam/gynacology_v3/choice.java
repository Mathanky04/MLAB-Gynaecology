package kuppam.gynacology_v3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class choice extends AppCompatActivity {

    private static Button btno, btng;
    public static boolean checker1 = false, checker2 = false;
    public static String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        btno=(Button)findViewById(R.id.button_o);
        btng=(Button)findViewById(R.id.button_g);
        onBtnO();
        onBtnG();
    }

    @Override
    public void onBackPressed() { }

    public void onBtnO()
    {
        btno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checker1 = true;
                type = "obs";
                System.out.println("Checker::"+checker1+" "+type);
                Intent intent = new Intent(getApplicationContext(),updateChoice.class);
                startActivity(intent);
            }
        });
    }

    public void onBtnG()
    {
        btng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checker2 = true;
                type="gyn";
                System.out.println("Checker::"+checker2+" "+type);
                Intent intent = new Intent(getApplicationContext(),updateChoice.class);
                startActivity(intent);
            }
        });
    }
}
