package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

public class ArticleResearch {
    public String title;
    public Integer categoryId;

    public ArticleResearch(String title, Integer categoryId) {
        this.title = title;
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
