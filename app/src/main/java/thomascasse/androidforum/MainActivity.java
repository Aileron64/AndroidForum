package thomascasse.androidforum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends Activity
{
    //ListView threadListView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.threadView);

        new DatabaseManager().loadThreads(new DatabaseManager.DataStatus()
        {
            @Override
            public void DataIsLoaded(List<Thread> threads, List<String> keys)
            {
                findViewById(R.id.loading_icon).setVisibility(View.GONE);

                new RecyclerManager().setConfig(recyclerView,
                        MainActivity.this, threads, keys);
            }

            @Override
            public void DataIsInserted()
            {

            }

            @Override
            public void DataIsUpdated()
            {

            }

            @Override
            public void DataIsDeleted()
            {

            }
        });



        //recyclerView.On
    }
}

//        threadListView = (ListView)findViewById(R.id.threadList);
//
//        Thread[] testValues = new Thread[125];
//        for(int i = 0; i < testValues.length; i++)
//        {
//            testValues[i] = new Thread("Anonymous", "Value: " + i, "");
//        }
//
//        OldThreadAdapter adapter = new OldThreadAdapter(this, testValues);
//        threadListView.setAdapter(adapter);
//
//        threadListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
//            {
//                Intent showThread = new Intent(getApplicationContext(), ThreadActivity.class);
//                showThread.putExtra("thomascasse.THREAD_ID", i);
//                startActivity(showThread);
//            }
//        });