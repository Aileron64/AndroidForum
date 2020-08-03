package thomascasse.androidforum;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity
{
    ListView threadListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        threadListView = (ListView)findViewById(R.id.threadList);

        String[] testValues = new String[25];
        for(int i = 0; i < testValues.length; i++)
        {
            testValues[i] = "Value: " + i;
        }



    }
}