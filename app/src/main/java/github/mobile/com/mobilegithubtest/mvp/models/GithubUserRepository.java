package github.mobile.com.mobilegithubtest.mvp.models;

public class GithubUserRepository {

    private String name;
    private String html_url;
    private String description;
    private boolean has_issues;
    private Integer forks_count;
    private Integer open_issues_count;
    private Integer open_issues;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHas_issues() {
        return has_issues;
    }

    public void setHas_issues(boolean has_issues) {
        this.has_issues = has_issues;
    }

    public Integer getForks_count() {
        return forks_count;
    }

    public void setForks_count(Integer forks_count) {
        this.forks_count = forks_count;
    }

    public Integer getOpen_issues_count() {
        return open_issues_count;
    }

    public void setOpen_issues_count(Integer open_issues_count) {
        this.open_issues_count = open_issues_count;
    }

    public Integer getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(Integer open_issues) {
        this.open_issues = open_issues;
    }
}
