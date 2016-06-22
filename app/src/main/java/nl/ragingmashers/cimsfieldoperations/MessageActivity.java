package nl.ragingmashers.cimsfieldoperations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import nl.ragingmashers.cimsfieldoperations.fiop.Message;

/**
 * Created by matth on 6/15/2016.
 */
public class MessageActivity extends AppCompatActivity{
    TextView lblMessageTitle,lblMessageContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showmessage);

        lblMessageTitle = (TextView)findViewById(R.id.lblMessageTitle);
        lblMessageContent = (TextView)findViewById(R.id.lblMessageContent);

        Message message = (Message)getIntent().getSerializableExtra("Message");
        lblMessageTitle.setText(message.getTitle());
        lblMessageContent.setText(message.getMessage());
    }
}
