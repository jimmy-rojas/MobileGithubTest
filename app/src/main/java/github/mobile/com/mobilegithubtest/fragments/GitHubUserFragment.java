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
import github.mobile.com.mobilegithubtest.core.adapters.GithubUserAdapter;
import github.mobile.com.mobilegithubtest.mvp.models.GithubUser;
import github.mobile.com.mobilegithubtest.mvp.presenters.GithubUserPresenter;
import github.mobile.com.mobilegithubtest.mvp.view.GitHubUserFragmentView;

public class GitHubUserFragment extends Fragment implements GitHubUserFragmentView {

    private static final String TAG = GitHubUserFragment.class.getName();
    private ViewDataBinding binding;
    private RecyclerView recyclerView;
    private GithubUserPresenter presenter;
    private GithubUserAdapter adapter;
    private List<GithubUser> githubUserData;
    private MainActivity activity;
    private static GitHubUserFragment _instance;

    public GitHubUserFragment() {
        // Required empty public constructor
    }

    public static GitHubUserFragment get_instance() {
        if (_instance == null) {
            _instance = new GitHubUserFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_git_hub_user, container, false);
        View view = binding.getRoot();
        initUiComponents(view);
        return view;
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
    public void onResume() {
        super.onResume();
        presenter.loadData();
    }

    private void initUiComponents(View view){
        recyclerView = (RecyclerView)view.findViewById(R.id.rv_userList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        presenter = new GithubUserPresenter(this);
    }

    @Override
    public void renderGithubUserData(List<GithubUser> userDataList) {
        githubUserData = userDataList;
        if (adapter == null) {
            adapter = new GithubUserAdapter(githubUserData, new GithubUserAdapter.IOnUserEventListener() {
                @Override
                public void onUserDataLink(int position, GithubUser item) {
                    Intent i = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(item.getHtml_url()));
                    startActivity(i);
                }

                @Override
                public void onRepositoryDataLink(int position, GithubUser item) {
                    GithubUserApplication.getApplicationInstance().setGithubUserAsCurrent(item);
                    activity.openUserReposScreen();
                }
            });
        }
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void onLoadMore(){
        presenter.loadMoreData();
    }
}
