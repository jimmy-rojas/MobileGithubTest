package github.mobile.com.mobilegithubtest.fragments;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import github.mobile.com.mobilegithubtest.MainActivity;
import github.mobile.com.mobilegithubtest.R;
import github.mobile.com.mobilegithubtest.application.GithubUserApplication;
import github.mobile.com.mobilegithubtest.core.adapters.GithubUserRepoAdapter;
import github.mobile.com.mobilegithubtest.core.interfaces.IOnBackPressedListener;
import github.mobile.com.mobilegithubtest.databinding.FragmentGitHubUserReposBinding;
import github.mobile.com.mobilegithubtest.mvp.models.GithubUser;
import github.mobile.com.mobilegithubtest.mvp.models.GithubUserRepository;
import github.mobile.com.mobilegithubtest.mvp.presenters.GitHubUserReposPresenter;
import github.mobile.com.mobilegithubtest.mvp.view.GitHubUserReposFragmentView;

public class GitHubUserReposFragment extends Fragment implements GitHubUserReposFragmentView, IOnBackPressedListener {

    private FragmentGitHubUserReposBinding binding;
    private MainActivity activity;
    private static GitHubUserReposFragment _instance;
    private GithubUser githubUser;
    private RecyclerView recyclerView;
    private GitHubUserReposPresenter presenter;
    private GithubUserRepoAdapter adapter;

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
        initUiComponents(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        activity.setCurrentFragment(this);
        githubUser = GithubUserApplication.getApplicationInstance().getGithubUserAsCurrent();
        activity.setScreeTitle(githubUser.getLogin());
        presenter.loadData();
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

    private void initUiComponents(View view){
        recyclerView = (RecyclerView)view.findViewById(R.id.rv_userRepoList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        presenter = new GitHubUserReposPresenter(this);
    }

    @Override
    public void renderGithubUserRepoData(List<GithubUserRepository> userRepoDataList, String page) {
        adapter = new GithubUserRepoAdapter(userRepoDataList, new GithubUserRepoAdapter.IOnUserEventListener() {
            @Override
            public void onUserRepoDataLink(int position, GithubUserRepository item) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(item.getHtml_url()));
                startActivity(i);
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        binding.setCurrentPage(page);
    }

    public void onLoadPrevious() {
        presenter.loadPrevious();
    }

    public void onLoadNext() {
        presenter.loadNext();
    }
}
