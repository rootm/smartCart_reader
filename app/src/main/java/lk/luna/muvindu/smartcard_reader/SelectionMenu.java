package lk.luna.muvindu.smartcard_reader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SelectionMenu extends AppCompatActivity {
    private Spinner routeSelect;
    private Spinner busSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_menu);

        routeSelect = (Spinner) findViewById(R.id.spinner);
        busSelect = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter  = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item,z);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> arg0,
                                       View arg1, int arg2, long arg3)
            {
                int index = s1.getSelectedItemPosition();
                Toast.makeText(getBaseContext(),
                        "You have selected item : " + Items[index],
                        Toast.LENGTH_SHORT).show();

                if (index==12)
                {
                    EditText edit = (EditText) findViewById(R.id.edittext);
                    Button add=(Button) findViewById(R.id.add);
                    edit.setVisibility(View.VISIBLE);
                    add.setVisibility(View.VISIBLE);


                }
                else
                {
                    EditText edit = (EditText) findViewById(R.id.edittext);
                    Button add=(Button) findViewById(R.id.add);
                    edit.setVisibility(View.GONE);
                    add.setVisibility(View.GONE);
                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {}
        });

    }
}
