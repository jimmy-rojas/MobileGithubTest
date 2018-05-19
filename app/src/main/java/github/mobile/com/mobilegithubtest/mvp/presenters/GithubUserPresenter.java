package github.mobile.com.mobilegithubtest.mvp.presenters;

import java.util.ArrayList;
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

    public GithubUserPresenter(GitHubUserFragmentView view) {
        this.view = view;
        service = new GithubService();
    }

    public void loadData() {
        if (GithubUserApplication.getApplicationInstance().getGithubUserData() != null) {
            //TODO: load from server
        } else {
            this.view.renderGithubUserData(new ArrayList<GithubUser>());

            service.getAPI()
                    .getGithubUsers(1, 20)
                    .enqueue(new Callback<List<GithubUser>>() {
                        @Override
                        public void onResponse(Call<List<GithubUser>> call, Response<List<GithubUser>> response) {
                            view.renderGithubUserData(response.body());
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
}
