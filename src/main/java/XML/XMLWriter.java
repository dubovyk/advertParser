package XML;

import Models.PageData;

import java.io.IOException;

/**
 * @author Sergey Dubovyk
 * @version 1.0
 */
public class XMLWriter {
    private String path;
    private PageData pageData;

    public XMLWriter(String filePath, PageData pageData){
        this.path = filePath;
        this.pageData = pageData;
    }

    public void write() throws IOException{

    }
}
