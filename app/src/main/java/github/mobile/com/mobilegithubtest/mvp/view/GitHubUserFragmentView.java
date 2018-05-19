package github.mobile.com.mobilegithubtest.mvp.view;

import java.util.List;

import github.mobile.com.mobilegithubtest.mvp.models.GithubUser;

public interface GitHubUserFragmentView {
    void renderGithubUserData(List<GithubUser> userDataList);
}
