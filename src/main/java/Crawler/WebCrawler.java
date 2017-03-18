package Crawler;

import Models.PageData;
import Models.SingleSlide;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Dubovyk
 * @version 1.0
 */
public class WebCrawler {
    public WebCrawler(){

    }

    public PageData parseURL(String url){
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException ex){
            ex.printStackTrace();
            return null;
        }

        PageData result = new PageData();

        try {
            result.setUrl(url);
            result.setTitle(getTitle(doc));
            result.setDescription(getDescription(doc));
            result.setTags(getTags(doc));
            result.setSlides(getSlides(doc));
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

    private String getTitle(Document doc) throws IOException{
        org.jsoup.nodes.Element title = doc.getElementsByTag("title").get(0);
        return title.text();
    }

    private String getDescription(Document document){
        StringBuilder result = new StringBuilder();
        result.append("CLICK FOR GUIDE AND LINKS ►► ");
        result.append(document.baseUri());
        result.append("\n\n");

        org.jsoup.nodes.Element desc = document.select("meta[name=description]").get(0);
        result.append(desc.attr("content"));
        result.append("\n\n");
        result.append("Products included in this guide:\n");
        int i = 1;
        for(SingleSlide slide: getSlides(document)){
            result.append(i).append(". ").append(slide.getName()).append("\n");
            i++;
        }
        result.append("\n");
        result.append("Please note: Our picks for this guide might " +
                "have changed since we uploaded this review video. " +
                "Please check the official guide above to view the " +
                "latest picks and the best products.\n");
        result.append("\n");
        result.append("Latest picks: ").append(document.baseUri());
        return result.toString();
    }


    private List<String> getTags(Document document){
        List<String> result = new ArrayList<String>();
        Elements tags = document.select("meta[property=article:tag]");
        for(org.jsoup.nodes.Element element: tags){
            result.add(element.attr("content"));
        }
        return result;
    }

    private List<SingleSlide> getSlides(Document document){
        List<SingleSlide> result = new ArrayList<SingleSlide>();
        Elements slides = document.select("div[class=thrv_wrapper thrv_contentbox_shortcode productrw]");
        System.out.println(slides.size());
        for(int i = 1; i <= slides.size(); i++){
            org.jsoup.nodes.Element slide = slides.get(i-1);
            List<String> pros = new ArrayList<String>();
            List<String> cons = new ArrayList<String>();

            org.jsoup.nodes.Element productName = slide.select("h2[class]").get(0);
            Elements prosItems = slide.select("ul[class=tve_ul tve_ul1 tve_green]").select("li");
            Elements consItems = slide.select("ul[class=tve_ul tve_ul4 tve_red]").select("li");
            Element link = slide.select("span[class=tve_image_frame]").select("noscript").select("img").get(0);

            for(org.jsoup.nodes.Element pro: prosItems){
                pros.add(pro.text());
            }

            for(org.jsoup.nodes.Element con: consItems){
                cons.add(con.text());
            }
            String name = productName.text();

            SingleSlide newSlide = new SingleSlide();

            newSlide.setId(((Integer) i).toString());
            newSlide.setName(name);
            newSlide.setUrl("https://" + link.attr("src").replace("//", ""));
            newSlide.setPros(pros);
            newSlide.setCons(cons);

            result.add(newSlide);

        }
        return result;
    }
}
