package nl.ragingmashers.cimsfieldoperations.fiop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nl.ragingmashers.cimsfieldoperations.InboxActivity;
import nl.ragingmashers.cimsfieldoperations.MessageActivity;
import nl.ragingmashers.cimsfieldoperations.R;

/**
 * Created by matth on 6/15/2016.
 */
public class InboxAdapter extends BaseAdapter {
    private Context context;
    private List<Message> messages;

    private static LayoutInflater inflater = null;

    public InboxAdapter(Context context, List<Message> messages) {
        this.messages = messages;
        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.inbox_item, null);
        TextView text = (TextView) vi.findViewById(R.id.text);
        text.setText(messages.get(position).getTitle());
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MessageActivity.class);
                intent.putExtra("Message",messages.get(position));
                context.startActivity(intent);
            }
        });
        return vi;
    }
}
