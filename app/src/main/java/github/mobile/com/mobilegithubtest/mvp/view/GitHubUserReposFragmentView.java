package github.mobile.com.mobilegithubtest.mvp.view;

import java.util.List;

import github.mobile.com.mobilegithubtest.mvp.models.GithubUserRepository;

public interface GitHubUserReposFragmentView {
    void renderGithubUserRepoData(List<GithubUserRepository> userRepoDataList, String page);
}
