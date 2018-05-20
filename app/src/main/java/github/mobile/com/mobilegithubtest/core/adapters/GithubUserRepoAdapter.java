package github.mobile.com.mobilegithubtest.core.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import github.mobile.com.mobilegithubtest.R;
import github.mobile.com.mobilegithubtest.databinding.GithubUserRepoLayoutBinding;
import github.mobile.com.mobilegithubtest.mvp.models.GithubUserRepository;

public class GithubUserRepoAdapter extends RecyclerView.Adapter<GithubUserRepoAdapter.ViewHolder> {

    private List<GithubUserRepository> userRepoDataList;
    private IOnUserEventListener callback;
    private Context context;

    public GithubUserRepoAdapter(List<GithubUserRepository> userRepoDataList, IOnUserEventListener callback) {
        this.userRepoDataList = userRepoDataList;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new GithubUserRepoAdapter.ViewHolder((GithubUserRepoLayoutBinding) DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.github_user_repo_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GithubUserRepository githubUserRepository = userRepoDataList.get(position);
        holder.bind(position, githubUserRepository);
    }

    @Override
    public int getItemCount() {
        return userRepoDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private int position;
        private GithubUserRepoLayoutBinding binding;

        public ViewHolder(GithubUserRepoLayoutBinding binding) {
            super(binding.getRoot());
            mView = binding.getRoot();
            this.binding = binding;
            this.binding.setHolder(this);
        }

        public void onRepositoryDataLink() {
            callback.onUserRepoDataLink(position, binding.getGithubUserRepo());
        }

        public void bind(int position, GithubUserRepository githubUserRepository) {
            this.position = position;
            binding.setGithubUserRepo(githubUserRepository);
        }
    }

    public interface IOnUserEventListener {
        void onUserRepoDataLink(int position, GithubUserRepository item);
    }
}
