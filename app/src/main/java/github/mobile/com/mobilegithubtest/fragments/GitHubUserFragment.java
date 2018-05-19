package github.mobile.com.mobilegithubtest.fragments;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import github.mobile.com.mobilegithubtest.R;
import github.mobile.com.mobilegithubtest.mvp.models.GithubUser;
import github.mobile.com.mobilegithubtest.mvp.presenters.GithubUserPresenter;
import github.mobile.com.mobilegithubtest.mvp.view.GitHubUserFragmentView;

public class GitHubUserFragment extends Fragment implements GitHubUserFragmentView {

    private ViewDataBinding binding;
    private RecyclerView recyclerView;
    private GithubUserPresenter presenter;

    public GitHubUserFragment() {
        // Required empty public constructor
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

    private void initUiComponents(View view){
        recyclerView = (RecyclerView)view.findViewById(R.id.rv_userList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        presenter = new GithubUserPresenter(this);
        presenter.loadData();
    }

    @Override
    public void renderGithubUserData(List<GithubUser> userDataList) {
//        data = userDataList;
        //TODO: render data in UI
    }

    public void onLoadMore(){
        ////TODO: load more data on demand
    }
}
