package github.mobile.com.mobilegithubtest.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.impl.LimitedAgeDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import github.mobile.com.mobilegithubtest.R;
import github.mobile.com.mobilegithubtest.mvp.models.GithubUser;

public class GithubUserApplication extends Application {

    private static final String TAG = GithubUserApplication.class.getName();
    private List<GithubUser> GithubUserData;
    private static GithubUserApplication ourInstance;
    public static final int IMAGE_CACHE_SIZE_IN_MB = 25;
    private GithubUser githubUserAsCurrent;

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        initImageLoader(getApplicationContext());
    }

    private void initImageLoader(Context context) {
        try {
            DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                    .showImageOnFail(R.drawable.no_image)
                    .showImageForEmptyUri(R.drawable.no_image)
                    .cacheOnDisk(true)
                    .build();
            File cacheDir = StorageUtils.getCacheDirectory(context);
            if (!cacheDir.exists() && !cacheDir.mkdir()) {
                Log.e(TAG, "mkdirs failure, the directory could not be created.");
                return;
            }
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                    .defaultDisplayImageOptions(defaultOptions)
                    .diskCache(new LimitedAgeDiskCache(cacheDir, IMAGE_CACHE_SIZE_IN_MB * 1024 * 1024))
                    .build();
            ImageLoader.getInstance().init(config);
        } catch (Exception e) {
            Log.e(TAG, "exception", e);
        }
    }

    public static GithubUserApplication getApplicationInstance() {
        return ourInstance;
    }

    public List<GithubUser> getGithubUserData() {
        return GithubUserData;
    }

    public void setGithubUserData(List<GithubUser> githubUserData) {
        if (GithubUserData == null) {
            GithubUserData = new ArrayList<>();
        }
        GithubUserData.addAll(githubUserData);
    }

    public void setGithubUserAsCurrent(GithubUser githubUserAsCurrent) {
        this.githubUserAsCurrent = githubUserAsCurrent;
    }

    public GithubUser getGithubUserAsCurrent() {
        return githubUserAsCurrent;
    }
}
