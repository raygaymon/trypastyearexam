package assessmentpractice.ass.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import assessmentpractice.ass.Model.Article;
import assessmentpractice.ass.Service.NewsService;

@Controller
@RequestMapping(path="/")
public class NewsController {
    
    @Autowired
    private NewsService service;

    @GetMapping(path="/")
    public String homePage (Model m) throws IOException {
        
        List<Article> articleList = service.getArticles();

        m.addAttribute("articleList", articleList);
        return "homepage";
        
    }
}
