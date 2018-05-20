package github.mobile.com.mobilegithubtest.mvp.presenters;

import java.util.List;

import github.mobile.com.mobilegithubtest.application.GithubUserApplication;
import github.mobile.com.mobilegithubtest.mvp.models.GithubUser;
import github.mobile.com.mobilegithubtest.mvp.view.GitHubUserFragmentView;
import github.mobile.com.mobilegithubtest.service.retrofit.GithubService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubUserPresenter {

    private GitHubUserFragmentView view;
    private GithubService service;
    private int countPage;
    private int currentPage;

    public GithubUserPresenter(GitHubUserFragmentView view) {
        this.view = view;
        service = new GithubService();
        countPage = 10;
        currentPage = 1;
    }

    public void loadData() {
        if (GithubUserApplication.getApplicationInstance().getGithubUserData() != null) {
            view.renderGithubUserData(GithubUserApplication.getApplicationInstance().getGithubUserData());
        } else {
            getGithubUserData();
        }
    }

    public void loadMoreData() {
        currentPage++;
        getGithubUserData();
    }

    private void getGithubUserData() {
        service.getAPI()
                .getGithubUsers(currentPage, countPage)
                .enqueue(new Callback<List<GithubUser>>() {
                    @Override
                    public void onResponse(Call<List<GithubUser>> call, Response<List<GithubUser>> response) {
                        GithubUserApplication.getApplicationInstance().setGithubUserData(response.body());
                        view.renderGithubUserData(GithubUserApplication.getApplicationInstance().getGithubUserData());
                        //TODO: evaluate once no more data is retrieved
                    }

                    @Override
                    public void onFailure(Call<List<GithubUser>> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
