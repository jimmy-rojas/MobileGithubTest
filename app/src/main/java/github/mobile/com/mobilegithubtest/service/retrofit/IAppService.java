package github.mobile.com.mobilegithubtest.service.retrofit;

import java.util.List;

import github.mobile.com.mobilegithubtest.mvp.models.GithubUser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IAppService {

    @GET("users")
    Call<List<GithubUser>> getGithubUsers(@Query("page") int page, @Query("countPerPage") int countPerPage);
}
