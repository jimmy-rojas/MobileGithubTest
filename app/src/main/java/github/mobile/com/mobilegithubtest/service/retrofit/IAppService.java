package github.mobile.com.mobilegithubtest.service.retrofit;

import java.util.List;

import github.mobile.com.mobilegithubtest.mvp.models.GithubUser;
import github.mobile.com.mobilegithubtest.mvp.models.GithubUserRepository;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface IAppService {

    @GET("users")
    Call<List<GithubUser>> getGithubUsers(@Query("page") int page, @Query("countPerPage") int countPerPage);

    @GET
    Call<List<GithubUserRepository>> getGithubUserRepositories(@Url String url);
}
