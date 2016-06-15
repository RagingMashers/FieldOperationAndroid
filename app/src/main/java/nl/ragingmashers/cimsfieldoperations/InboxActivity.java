package nl.ragingmashers.cimsfieldoperations;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

import nl.ragingmashers.cimsfieldoperations.fiop.ApiManager;
import nl.ragingmashers.cimsfieldoperations.fiop.InboxAdapter;
import nl.ragingmashers.cimsfieldoperations.fiop.Message;

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
        listview.setAdapter(new InboxAdapter(this,new LinkedList<Message>()));

        ReceiveMessageTask task = new ReceiveMessageTask(this);
        task.execute();
    }

    public class ReceiveMessageTask extends AsyncTask<Void, Void, List<Message>> {
        AppCompatActivity appCompatActivity;

        public ReceiveMessageTask(AppCompatActivity appCompatActivity){
            this.appCompatActivity = appCompatActivity;
        }

        @Override
        protected List<Message> doInBackground(Void... params) {
            if(!ApiManager.getInstance().isLoggedIn()){
                ApiManager.getInstance().login("testUser", "testPass");
            }
            return ApiManager.getInstance().getMessages();
        }

        @Override
        protected void onPostExecute(final List<Message> messages){
            listview.setAdapter(new InboxAdapter(appCompatActivity,messages));
        }

        @Override
        protected void onCancelled(){

        }
    }
}
