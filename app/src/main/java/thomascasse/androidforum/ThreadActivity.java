package thomascasse.androidforum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ThreadActivity extends AppCompatActivity
{
    private String key;
    private RecyclerView recyclerView;
    private FloatingActionButton newPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        key = getIntent().getStringExtra("thomascasse.THREAD_KEY");
        recyclerView = (RecyclerView) findViewById(R.id.postView);

        TextView threadTitle = (TextView)findViewById(R.id.threadTitle);
        threadTitle.setText(getIntent().getStringExtra("thomascasse.THREAD_TITLE"));
        TextView threadAuthor = (TextView)findViewById(R.id.threadAuthor);
        threadAuthor.setText(getIntent().getStringExtra("thomascasse.THREAD_NAME"));

        new DatabaseManager(key).loadData(new DatabaseManager.DataStatus()
        {
            @Override
            public void DataIsLoaded(List<Post> posts, List<String> keys)
            {
                findViewById(R.id.loading_icon).setVisibility(View.GONE);

                new RecyclerManager().setConfig(recyclerView,
                        ThreadActivity.this, posts, keys, false, R.layout.post_list_item);
            }

            @Override
            public void DataIsInserted()
            {

            }
        });

        newPostButton = (FloatingActionButton)findViewById(R.id.newPostBtn);
        newPostButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                NewPostBottomSheet bottomSheet = new NewPostBottomSheet(key);
                bottomSheet.show(getSupportFragmentManager(), "Test");
            }
        });
    }
}