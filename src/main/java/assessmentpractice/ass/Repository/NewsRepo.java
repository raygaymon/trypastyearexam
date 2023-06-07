package assessmentpractice.ass.Repository;

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

        template.opsForValue().set(a.getId(), a.getTitle());

    }
}
