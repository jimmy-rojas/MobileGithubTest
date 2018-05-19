package github.mobile.com.mobilegithubtest.mvp.presenters;

import java.util.ArrayList;

import github.mobile.com.mobilegithubtest.application.GithubUserApplication;
import github.mobile.com.mobilegithubtest.mvp.models.GithubUser;
import github.mobile.com.mobilegithubtest.mvp.view.GitHubUserFragmentView;

public class MainActivityPresenter {

    GitHubUserFragmentView view;

    public MainActivityPresenter(GitHubUserFragmentView view) {
        this.view = view;
    }

    public void loadData() {
        if (GithubUserApplication.getApplicationInstance().getGithubUserData() == null) {
            //TODO: load from server
        } else {
            this.view.renderGithubUserData(new ArrayList<GithubUser>());
        }
    }
}
