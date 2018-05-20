package github.mobile.com.mobilegithubtest.mvp.presenters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import github.mobile.com.mobilegithubtest.application.GithubUserApplication;
import github.mobile.com.mobilegithubtest.mvp.models.GithubUser;
import github.mobile.com.mobilegithubtest.mvp.models.GithubUserRepository;
import github.mobile.com.mobilegithubtest.mvp.view.GitHubUserReposFragmentView;
import github.mobile.com.mobilegithubtest.service.retrofit.GithubService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubUserReposPresenter {

    private final GithubUser githubUser;
    private GitHubUserReposFragmentView view;
    private GithubService service;
    private int countPage;
    private int currentPage;
    private Map<Integer, List<GithubUserRepository>> repoDataMap;
    private static final String URL_REPO_REQUEST_TEMPLATE = "%s?page=%s&per_page=%s";

    public GitHubUserReposPresenter(GitHubUserReposFragmentView view) {
        this.view = view;
        service = new GithubService();
        countPage = 10;
        currentPage = 1;
        githubUser = GithubUserApplication.getApplicationInstance().getGithubUserAsCurrent();
    }

    public void loadData() {
        if (repoDataMap != null && repoDataMap.containsKey(currentPage)) {
            renderUI();
        } else {
            getGithubUserRepoData();
        }
    }

    private void renderUI() {
        view.renderGithubUserRepoData(repoDataMap.get(currentPage), String.valueOf(currentPage));
    }

    private void getGithubUserRepoData() {
        String url = String.format(URL_REPO_REQUEST_TEMPLATE, githubUser.getRepos_url(), currentPage, countPage);
        service.getAPI()
                .getGithubUserRepositories(url)
                .enqueue(new Callback<List<GithubUserRepository>>() {
                    @Override
                    public void onResponse(Call<List<GithubUserRepository>> call, Response<List<GithubUserRepository>> response) {
                        if (repoDataMap == null) {
                            repoDataMap = new HashMap<>();
                        }
                        repoDataMap.put(currentPage, response.body());
                        renderUI();
                        //TODO: evaluate once no more data is retrieved
                    }

                    @Override
                    public void onFailure(Call<List<GithubUserRepository>> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void loadNext() {
        currentPage ++;
        loadData();
    }

    public void loadPrevious() {
        if (currentPage > 1) {
            currentPage--;
            loadData();
        }
    }
}
