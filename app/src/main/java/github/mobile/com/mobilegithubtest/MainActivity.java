package github.mobile.com.mobilegithubtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import github.mobile.com.mobilegithubtest.mvp.models.GithubUser;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<GithubUser> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUiComponents();
    }

    private void initUiComponents(){
        recyclerView = (RecyclerView)findViewById(R.id.rv_userList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }
}
