package assessmentpractice.ass.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import assessmentpractice.ass.Model.Article;
import assessmentpractice.ass.Service.NewsService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping("/news")
public class NewsRESTController {
    
    @Autowired
    private NewsService service;

    @GetMapping(path = "/{articleId}")
    public ResponseEntity<String> getArticleAsJson (@PathVariable String articleId){

        Article a = service.getArticleById(articleId);

        if(a == null) {
            JsonObject error = Json.createObjectBuilder()
                                    .add("error", "Cannot find news article %s".formatted(articleId))
                                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.toString());
        }

        return ResponseEntity.ok(a.toJSON().toString());
    }
}
