package thomascasse.androidforum;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerManager
{
    private Context context;
    private ThreadAdapter threadAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Thread> threads, List<String> keys)
    {
        this.context = context;
        this.threadAdapter = new ThreadAdapter(threads, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(threadAdapter);
    }

    class ThreadItemView extends RecyclerView.ViewHolder
    {
        private TextView title;
        private TextView name;

        private String key;

        public ThreadItemView(ViewGroup parent)
        {
            super(LayoutInflater.from(context).
                    inflate(R.layout.thread_list_item, parent, false));

            title = (TextView) itemView.findViewById(R.id.titleTextView);
            name = (TextView) itemView.findViewById(R.id.nameTextView);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent showThread = new Intent(context, ThreadActivity.class);
                    showThread.putExtra("thomascasse.THREAD_KEY", key);
                    //showThread.putExtra("thomascasse.THREAD_TITLE", key);
                    //showThread.putExtra("thomascasse.THREAD_NAME", key);
                    //showThread.putExtra("thomascasse.THREAD_DESC", key);
                    context.startActivity(showThread);
                }
            });
        }

        public void bind(Thread thread, String key)
        {
            title.setText(thread.getTitle());
            //name.setText(thread.getName());
            name.setText(key);
            this.key = key;
        }
    }

    class ThreadAdapter extends RecyclerView.Adapter<ThreadItemView>
    {
        private List<Thread> threads;
        private List<String> keys;

        public ThreadAdapter(List<Thread> threads, List<String> keys)
        {
            this.threads = threads;
            this.keys = keys;
        }

        @NonNull
        @Override
        public ThreadItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            return new ThreadItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ThreadItemView holder, int position)
        {
            holder.bind(threads.get(position), keys.get(position));
        }

        @Override
        public int getItemCount()
        {
            return threads.size();
        }
    }
}
