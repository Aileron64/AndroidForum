package thomascasse.androidforum;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager
{
    private FirebaseDatabase database;
    private DatabaseReference threadsRef;

    private List<Thread> threads = new ArrayList<>();

    public interface DataStatus
    {
        void DataIsLoaded(List<Thread> threads, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public DatabaseManager()
    {
        database = FirebaseDatabase.getInstance();
        threadsRef = database.getReference("Thread");
    }

    public void loadThreads(final DataStatus dataStatus)
    {
        threadsRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                threads.clear();
                List<String> keys = new ArrayList<>();

                for(DataSnapshot keyNode : dataSnapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    Thread thread = keyNode.getValue(Thread.class);
                    threads.add(thread);
                }

                dataStatus.DataIsLoaded(threads, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}
