package github.mobile.com.mobilegithubtest.core.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import github.mobile.com.mobilegithubtest.R;
import github.mobile.com.mobilegithubtest.databinding.GithubUserLayoutBinding;
import github.mobile.com.mobilegithubtest.mvp.models.GithubUser;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.ViewHolder> {

    private List<GithubUser> githubUserData;
    private IOnUserEventListener callback;
    private Context context;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    public GithubUserAdapter(List<GithubUser> githubUserData, IOnUserEventListener callback) {
        this.githubUserData = githubUserData;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new ViewHolder((GithubUserLayoutBinding) DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.github_user_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GithubUser githubUser = githubUserData.get(position);
        imageLoader.displayImage(githubUser.getAvatar_url(), holder.imgUser);
        holder.bind(position, githubUser);
    }

    @Override
    public int getItemCount() {
        return this.githubUserData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final AppCompatImageView imgUser;
        private int position;
        private GithubUserLayoutBinding binding;

        public ViewHolder(GithubUserLayoutBinding binding) {
            super(binding.getRoot());
            mView = binding.getRoot();
            imgUser = binding.getRoot().findViewById(R.id.imgUser);
            this.binding = binding;
            this.binding.setHolder(this);
        }

        public void onUserDataLink() {
            callback.onUserDataLink(position, binding.getGithubUser());
        }

        public void onRepositoryDataLink() {
            callback.onRepositoryDataLink(position, binding.getGithubUser());
        }

        public void bind(int position, GithubUser githubUser) {
            this.position = position;
            binding.setGithubUser(githubUser);
        }
    }

    public interface IOnUserEventListener {
        void onUserDataLink(int position, GithubUser item);
        void onRepositoryDataLink(int position, GithubUser item);
    }

}
