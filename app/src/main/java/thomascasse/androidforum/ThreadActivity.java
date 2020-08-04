package thomascasse.androidforum;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ThreadActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        String threadKey = getIntent().getStringExtra("thomascasse.THREAD_KEY");

        TextView threadTitle = (TextView)findViewById(R.id.threadTitle);
        threadTitle.setText(threadKey);
    }
}