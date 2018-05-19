package github.mobile.com.mobilegithubtest.application;

import android.app.Application;

import java.util.ArrayList;

import github.mobile.com.mobilegithubtest.mvp.models.GithubUser;

public class GithubUserApplication extends Application {

    private ArrayList<GithubUser> GithubUserData;
    private static GithubUserApplication ourInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
    }

    public static GithubUserApplication getApplicationInstance() {
        return ourInstance;
    }

    public ArrayList<GithubUser> getGithubUserData() {
        return GithubUserData;
    }

    public void setGithubUserData(ArrayList<GithubUser> githubUserData) {
        GithubUserData = githubUserData;
    }

}
