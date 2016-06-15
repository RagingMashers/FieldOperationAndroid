package nl.ragingmashers.cimsfieldoperations.fiop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import nl.ragingmashers.cimsfieldoperations.R;

/**
 * Created by matth on 6/15/2016.
 */
public class InboxAdapter extends BaseAdapter {
    private Context context;
    private Message[] messages;

    private static LayoutInflater inflater = null;

    public InboxAdapter(Context context, Message[] messages) {
        // TODO Auto-generated constructor stub
        this.messages = messages;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return messages.length;
    }

    @Override
    public Object getItem(int position) {
        return messages[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.inbox_item, null);
        TextView text = (TextView) vi.findViewById(R.id.text);
        text.setText(messages[position].getTitle());
        return vi;
    }
}
