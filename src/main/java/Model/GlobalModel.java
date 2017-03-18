package Model;

import Models.PageData;

/**
 * @author Sergey Dubovyk
 * @version 1.0
 */
public class GlobalModel {
    private String url;
    private PageData pageData;
    private String filePath;


    private static GlobalModel ourInstance = new GlobalModel();
    public static GlobalModel getInstance() {
        return ourInstance;
    }

    private GlobalModel() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PageData getPageData() {
        return pageData;
    }

    public void setPageData(PageData pageData) {
        this.pageData = pageData;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
