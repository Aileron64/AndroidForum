package thomascasse.androidforum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class OldThreadAdapter extends BaseAdapter
{
    LayoutInflater inflater;
    Thread[] threads;

    public OldThreadAdapter(Context context, Thread[] t)
    {
        threads = t;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return threads.length;
    }

    @Override
    public Object getItem(int i)
    {
        return threads[i];
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View v = inflater.inflate(R.layout.thread_list_item, null);

        TextView titleTextView = (TextView) v.findViewById(R.id.titleTextView);
        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);

        titleTextView.setText(threads[i].getTitle());
        nameTextView.setText(threads[i].getName());

        return v;
    }
}









