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
    private boolean clickable;

    public void setConfig(RecyclerView recyclerView, Context context, List<Post> posts, List<String> keys,
                          boolean clickable)
    {
        this.context = context;
        this.threadAdapter = new ThreadAdapter(posts, keys);
        this.clickable = clickable;
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

            if(clickable)
                itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Intent intent = new Intent(context, ThreadActivity.class);
                        intent.putExtra("thomascasse.THREAD_KEY", key);
                        intent.putExtra("thomascasse.THREAD_TITLE", title.getText().toString());
                        intent.putExtra("thomascasse.THREAD_NAME", name.getText().toString());
                        context.startActivity(intent);
                    }
                });
        }

        public void bind(Post thread, String key)
        {
            title.setText(thread.getMessage());
            name.setText(thread.getName());
            //name.setText(key);
            this.key = key;
        }
    }

    class ThreadAdapter extends RecyclerView.Adapter<ThreadItemView>
    {
        private List<Post> threads;
        private List<String> keys;

        public ThreadAdapter(List<Post> threads, List<String> keys)
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
