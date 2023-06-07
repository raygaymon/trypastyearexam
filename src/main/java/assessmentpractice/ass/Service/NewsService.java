package assessmentpractice.ass.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import assessmentpractice.ass.Model.Article;

@Service
public class NewsService {
    
    @Value("${ass.open.crypto.url}")
    private String cryptoUrl;

    @Value("${ass.open.crypto.api.key}")
    private String cryptoAPI;

    public List<Article> getArticles() throws IOException{
        
        System.out.println("URL is " + cryptoUrl);
        String URIBuilder = UriComponentsBuilder
                                .fromUriString(cryptoUrl)
                                .queryParam("apiKey", cryptoAPI)
                                .toUriString();
        
        RestTemplate rt = new RestTemplate();
        ResponseEntity<Article[]> re = rt.getForEntity(URIBuilder, Article[].class);
        
        System.out.println(re.getBody());
        return Arrays.asList(re.getBody());
        
    }
}
