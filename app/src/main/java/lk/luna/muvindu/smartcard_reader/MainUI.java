package lk.luna.muvindu.smartcard_reader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import be.appfoundry.nfclibrary.activities.NfcActivity;

public class MainUI extends NfcActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        for (String message : getNfcMessages()){
            Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
        }
    }


}
