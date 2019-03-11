package com.example.androidvksample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.androidvksample.adapter.UsersAdapter;
import com.example.androidvksample.utils.VKQueryTask;

import org.json.JSONArray;

import static com.example.androidvksample.utils.NetworkUtils.getURL;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button_search;
    private TextView errorTextView;
    private ProgressBar loadProgressBar;
    private RecyclerView recyclerView;
    private UsersAdapter usersAdapter;
    private LinearLayoutManager linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.et_search_field);
        button_search = findViewById(R.id.btn_search);
        errorTextView = findViewById(R.id.tv_error_message);
        loadProgressBar = findViewById(R.id.pb_loading_indicator);
        recyclerView = findViewById(R.id.rv_users_by_ids);
        linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setHasFixedSize(true);
        usersAdapter = new UsersAdapter();

        recyclerView.setAdapter(usersAdapter);

        button_search.setOnClickListener(v -> new VKQueryTask(getThis()).execute(getURL(editText.getText().toString())));
    }

    public void showResultTextView(){
        recyclerView.setVisibility(View.VISIBLE);
        errorTextView.setVisibility(View.INVISIBLE);
    }

    public void showErrorTextView(){
        recyclerView.setVisibility(View.INVISIBLE);
        errorTextView.setVisibility(View.VISIBLE);
    }

    public ProgressBar getLoadProgressBar(){
        return loadProgressBar;
    }

    public void setAdapter(JSONArray array){
        usersAdapter.setArray(array);
        recyclerView.setAdapter(usersAdapter);
    }

    private MainActivity getThis(){
        return this;
    }
}
