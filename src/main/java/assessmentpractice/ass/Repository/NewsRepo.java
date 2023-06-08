package assessmentpractice.ass.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import assessmentpractice.ass.Model.Article;

@Repository
public class NewsRepo {
    
    @Autowired @Qualifier("Article")
    private RedisTemplate<String, String> template;

    public void saveArticle(Article a){

        template.opsForValue().set(a.getId(), a.toJSON().toString());

    }

    public Article getArticleById(String Id){

        return Article.fromJSON(template.opsForValue().get(Id));
    }

    public List<String> getAllArticles (){
        Set <String> keys = template.keys("*");
        System.out.println(template.opsForValue().multiGet(keys));
        return template.opsForValue().multiGet(keys);
    }

}
