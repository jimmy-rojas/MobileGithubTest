package github.mobile.com.mobilegithubtest;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import github.mobile.com.mobilegithubtest.core.interfaces.IOnBackPressedListener;
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
            navigate(GitHubUserFragment.get_instance(), "userList", StackFlag.NONE);
        }
    }

    @Override
    public void onBackPressed() {
        if (this.currentFragment instanceof IOnBackPressedListener) {
            ((IOnBackPressedListener)this.currentFragment).onBackPressed();
        }
    }

    private void cleanFragmentStack() {
        int count = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < count; ++i) {
            fragmentManager.popBackStackImmediate();
        }
    }

    public void closeCurrent() {
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.remove(currentFragment);
        trans.commit();
        fragmentManager.popBackStack();
    }

    public void navigate(Fragment fragment, String tag, StackFlag flag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        currentFragment = fragment;
        transaction.replace(R.id.body_container, currentFragment, tag);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        switch (flag) {
            case STACK:
                transaction.addToBackStack(null);
                break;
            case CLEAN_STACK:
                cleanFragmentStack();
                break;
            default:
        }
        transaction.commit();
    }

    public void openUserReposScreen() {
        navigate(GitHubUserReposFragment.get_instance(), "userRepoList", StackFlag.STACK);
    }

    public void setCurrentFragment(GitHubUserReposFragment currentFragment) {
        this.currentFragment = currentFragment;
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    public void setCurrentFragment(Fragment currentFragment) {
        this.currentFragment = currentFragment;
    }

    public void setScreeTitle(@NonNull String title) {
        setTitle(title);
    }

    public enum StackFlag {
        STACK,
        CLEAN_STACK,
        NONE
    }
}
