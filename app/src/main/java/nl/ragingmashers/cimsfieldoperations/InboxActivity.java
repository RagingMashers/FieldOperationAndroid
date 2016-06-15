package nl.ragingmashers.cimsfieldoperations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by matth on 6/15/2016.
 */
public class InboxActivity extends AppCompatActivity {
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        listview = (ListView)findViewById(R.id.listview);
    }
}
