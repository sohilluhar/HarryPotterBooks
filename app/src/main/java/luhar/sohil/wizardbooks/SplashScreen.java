package luhar.sohil.wizardbooks;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent in= new Intent(SplashScreen.this,MainActivity.class);
                startActivity(in);

            }
        },1000);
    }
}
