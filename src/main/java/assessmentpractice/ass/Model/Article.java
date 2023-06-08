package assessmentpractice.ass.Model;

import java.io.Serializable;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

public class Article implements Serializable {
    
    private String id;
    private long published_on;
    private String title;
    private String url;
    private String imageurl;
    private String body;
    private String tags;
    private String categories;

    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public long getPublished_on() {
        return published_on;
    }
    public void setPublished_on(long published_on) {
        this.published_on = published_on;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImageurl() {
        return imageurl;
    }
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getCategories() {
        return categories;
    }
    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Article() {
    }



    public JsonObject toJSON (){
        return Json.createObjectBuilder()
            .add("id", this.id)
            .add("published_on", this.published_on)
            .add("title", this.title)
            .add("url", this.url)
            .add("imageurl", this.imageurl)
            .add("body", this.body)
            .add("tags", this.tags)
            .add("categories", this.categories)
            .build();

    }

    public static Article fromJSON (String json) {

        JsonReader jr = (JsonReader) Json.createReader(new StringReader(json));
        JsonObject jo = jr.readObject();
        Article a = new Article();
        a.setId(jo.getString("id"));
        a.setPublished_on(jo.getJsonNumber("published_on").longValue());
        a.setTitle(jo.getString("title"));
        a.setUrl(jo.getString("url"));
        a.setImageurl(jo.getString("imageurl"));
        a.setBody(jo.getString("body"));
        a.setTags(jo.getString("tags"));
        a.setCategories(jo.getString("categories"));
        return a;
    }

    public static Article fromJSON (JsonObject json) {

        Article a = new Article();
        a.setId(json.getString("id"));
        a.setPublished_on(json.getJsonNumber("published_on").longValue());
        a.setTitle(json.getString("title"));
        a.setUrl(json.getString("url"));
        a.setImageurl(json.getString("imageurl"));
        a.setBody(json.getString("body"));
        a.setTags(json.getString("tags"));
        a.setCategories(json.getString("categories"));
        return a;
    }

    public void addToList(List<Article> toAdd) {

        toAdd.add(this);
    }

}
