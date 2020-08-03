package thomascasse.androidforum;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewThreadActivity extends Activity
{
    EditText enterName, enterTitle, enterDesc;
    DatabaseReference ref;
    Button postBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterName = (EditText)findViewById(R.id.enterName);
        enterTitle = (EditText)findViewById(R.id.enterTitle);
        enterDesc = (EditText)findViewById(R.id.enterDesc);
        postBtn = (Button)findViewById(R.id.postBtn);

        ref = FirebaseDatabase.getInstance().getReference().child("Thread");

        // Post Button
        postBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Thread thread = new Thread(
                        enterName.getText().toString().trim(),
                        enterTitle.getText().toString().trim(),
                        enterDesc.getText().toString().trim());

                ref.push().setValue(thread);

                Toast.makeText(NewThreadActivity.this, thread.getName() + " added",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}