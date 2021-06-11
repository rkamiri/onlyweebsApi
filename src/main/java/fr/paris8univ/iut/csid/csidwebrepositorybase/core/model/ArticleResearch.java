package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

public class ArticleResearch {
    public String title;
    public long categoryId;

    public ArticleResearch(String title, long categoryId) {
        this.title = title;
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
