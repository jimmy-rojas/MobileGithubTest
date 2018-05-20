package github.mobile.com.mobilegithubtest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import github.mobile.com.mobilegithubtest.fragments.GitHubUserFragment;
import github.mobile.com.mobilegithubtest.fragments.GitHubUserReposFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        currentFragment = fragmentManager.findFragmentById(R.id.body_container);
        if (currentFragment == null) {
            navigate(new GitHubUserFragment(), "userList");
        }
    }

    public void navigate(Fragment fragment, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        currentFragment = fragment;
        transaction.replace(R.id.body_container, currentFragment, tag);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }


    public void openUserReposScreen() {
        navigate(new GitHubUserReposFragment(), "userRepoList");
    }
}
