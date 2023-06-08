package assessmentpractice.ass.Model;

import java.io.Serializable;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

public class Data implements Serializable{
    
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
    
    public static Data createFromJson (JsonObject o) {

        Data d = new Data();
        List<Article> articles = new LinkedList<>();
        for (int i =0; i< o.getJsonArray("Data").size(); i++) {
            Article a = Article.fromJSON(o.getJsonArray("Data").getJsonObject(i));
        }

        d.setArticles(articles);
        return d;
    }

    public static Data createFromString (String s) {

        JsonReader jr = (JsonReader) Json.createReader(new StringReader (s));
        JsonObject jo = jr.readObject();
        Data d = new Data();
        List<Article> articles = new LinkedList<>();
        for (int i =0; i< jo.getJsonArray("Data").size(); i++) {

            Article a = Article.fromJSON(jo.getJsonArray("Data").getJsonObject(i));
            articles.add(a);
        }
        d.setArticles(articles);
        return d;
    }


}
