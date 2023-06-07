package assessmentpractice.ass.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import assessmentpractice.ass.Model.Article;
import assessmentpractice.ass.Model.Data;
import assessmentpractice.ass.Repository.NewsRepo;

@Service
public class NewsService {

    @Autowired
    private NewsRepo repo;
    
    @Value("${ass.open.crypto.url}")
    private String cryptoUrl;

    @Value("${ass.open.crypto.api.key}")
    private String cryptoAPI;

    public List<Article> getArticles() throws IOException{
        
        System.out.println("URL is " + cryptoUrl);
        String URIBuilder = UriComponentsBuilder
                                .fromUriString(cryptoUrl)
                                .queryParam("api_key", cryptoAPI)
                                .toUriString();
        RequestEntity req = RequestEntity.get(URIBuilder).build();
        
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> re = rt.exchange(req, String.class);

        // for (String article : re.getBody()){

        //     articleList.add(Article.fromJSON(article));
        // }
        
        Data d = Data.createFromString(re.getBody());
        return d.getArticles();
        
    }

    public void saveArticles(List<Article> toSave) {


        for (Article a : toSave) {
            this.repo.saveArticle(a);
        }
        
    }

}
