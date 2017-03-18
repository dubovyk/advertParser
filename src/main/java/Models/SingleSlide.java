package Models;

import java.util.List;

/**
 * @author Sergey Dubovyk
 * @version 1.0
 */
public class SingleSlide {
    private String id;
    private String name;
    private String description;
    private List<String> pros;
    private List<String> cons;
    private String url;
    private String filePath;

    public SingleSlide(){

    }

    public SingleSlide(String id, String name, String url, List<String> pros, List<String> cons) {
        this.id = id;
        this.name = name;
        this.pros = pros;
        this.cons = cons;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPros() {
        return pros;
    }

    public void setPros(List<String> pros) {
        this.pros = pros;
    }

    public List<String> getCons() {
        return cons;
    }

    public void setCons(List<String> cons) {
        this.cons = cons;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName(){
        return name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String toXml(){
        StringBuilder result = new StringBuilder();
        result.append("<slide>\n").
                append("<id>").append(id).append("</id>\n").
                append("<name>").append(name).append("</name>\n").
                append("<link>").append(url).append("</link>\n").
                append("<advantages>\n");
        for(String pro : pros){
            result.append("<item>- ").append(pro).append("</item>\n");
        }
        result.append("</advantages>\n").
                append("<disadvantages>\n");
        for(String con: cons){
            result.append("<item>- ").append(con).append("</item>\n");
        }
        result.append("</disadvantages>\n").
                append("</slide>\n");
        return result.toString();
    }
}
