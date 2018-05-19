package github.mobile.com.mobilegithubtest.application;

import android.app.Application;

import java.util.List;

import github.mobile.com.mobilegithubtest.mvp.models.GithubUser;

public class GithubUserApplication extends Application {

    private List<GithubUser> GithubUserData;
    private static GithubUserApplication ourInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
    }

    public static GithubUserApplication getApplicationInstance() {
        return ourInstance;
    }

    public List<GithubUser> getGithubUserData() {
        return GithubUserData;
    }

    public void setGithubUserData(List<GithubUser> githubUserData) {
        GithubUserData = githubUserData;
    }

}
