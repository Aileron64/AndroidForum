package thomascasse.androidforum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private FloatingActionButton addThreadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.threadView);

        new DatabaseManager().loadData(new DatabaseManager.DataStatus()
        {
            @Override
            public void DataIsLoaded(List<Post> posts, List<String> keys)
            {
                findViewById(R.id.loading_icon).setVisibility(View.GONE);

                new RecyclerManager().setConfig(recyclerView,
                        MainActivity.this, posts, keys, true);
            }

            @Override
            public void DataIsInserted()
            {

            }
        });

        addThreadButton = (FloatingActionButton)findViewById(R.id.addThreadBtn);
        addThreadButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                NewThreadBottomSheet bottomSheet = new NewThreadBottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "Test");
            }
        });
    }
}
