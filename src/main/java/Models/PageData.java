package Models;

import java.util.List;

/**
 * @author Sergey Dubovyk
 * @version 1.0
 */
public class PageData {
    private String url, title, description;
    private List<String> tags;
    private List<SingleSlide> slides;

    public PageData(){}

    public void addTag(String tag){
        tags.add(tag);
    }

    public void addSlide(SingleSlide slide){
        slides.add(slide);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<SingleSlide> getSlides() {
        return slides;
    }

    public void setSlides(List<SingleSlide> slides) {
        this.slides = slides;
    }

    public String toXml(){
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<video>\n");
        builder.append("<title>").append(getTitle()).append("</title>\n");
        builder.append("<description>").append(getDescription()).append("</description>\n");
        builder.append("<postUrl>").append(getUrl()).append("</postUrl>\n");
        builder.append("<tags>\n");
        for(String tag: getTags()){
            builder.append("<tag>").append(tag).append("</tag>\n");
        }
        builder.append("</tags>\n");
        builder.append("<slides>\n");
        for(SingleSlide slide: getSlides()){
            builder.append(slide.toXml());
        }
        builder.append("</slides>\n");
        builder.append("</video>");
        String res = builder.toString().replace("&", "&amp;");
        return res;
    }
}
