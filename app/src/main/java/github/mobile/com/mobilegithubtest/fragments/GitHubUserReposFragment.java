package github.mobile.com.mobilegithubtest.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import github.mobile.com.mobilegithubtest.MainActivity;
import github.mobile.com.mobilegithubtest.R;
import github.mobile.com.mobilegithubtest.application.GithubUserApplication;
import github.mobile.com.mobilegithubtest.core.interfaces.IOnBackPressedListener;
import github.mobile.com.mobilegithubtest.mvp.models.GithubUser;

public class GitHubUserReposFragment extends Fragment implements IOnBackPressedListener {

    private ViewDataBinding binding;
    private MainActivity activity;
    private static GitHubUserReposFragment _instance;
    private GithubUser githubUser;

    public GitHubUserReposFragment() {
        // Required empty public constructor
    }

    public static GitHubUserReposFragment get_instance() {
        if (_instance == null) {
            _instance = new GitHubUserReposFragment();
        }
        return _instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_git_hub_user_repos, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        activity.setCurrentFragment(this);
        githubUser = GithubUserApplication.getApplicationInstance().getGithubUserAsCurrent();
        activity.setScreeTitle(githubUser.getLogin());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public boolean onBackPressed() {
        activity.closeCurrent();
        return true;
    }

}
