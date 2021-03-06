package thomascasse.androidforum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class NewThreadBottomSheet extends BottomSheetDialogFragment
{
    EditText name;
    EditText response;
    Button postBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.new_thread, container, false);

        name = (EditText)view.findViewById(R.id.enterName);
        response = (EditText)view.findViewById(R.id.enterTitle);

        postBtn = (Button)view.findViewById(R.id.postBtn);
        postBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String _name;

                if (name.getText().toString().isEmpty())
                    _name = "Anonymous";
                else
                    _name = name.getText().toString().trim();

                if(response.getText().toString().isEmpty())
                {
                    response.setError("REQUIRED!");
                    return;
                }

                Post post = new Post(_name, response.getText().toString());

                new DatabaseManager().addPost(post, new DatabaseManager.DataStatus()
                {
                    @Override
                    public void DataIsLoaded(List<Post> posts, List<String> keys) { }

                    @Override
                    public void DataIsInserted()
                    {
                        dismiss();
                        return;
                    }
                });
            }
        });

        return view;
    }
}
