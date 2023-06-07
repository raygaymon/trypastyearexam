package assessmentpractice.ass.Controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import assessmentpractice.ass.Model.Article;
import assessmentpractice.ass.Model.Data;
import assessmentpractice.ass.Service.NewsService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path="/")
public class NewsController {
    
    @Autowired
    private NewsService service;

    @GetMapping(path="/")
    public String homePage (Model m, HttpSession session) throws IOException {
        
        List<Article> articleList = service.getArticles();
        List<Article> toSave = new LinkedList<>();

        if (articleList.isEmpty()){
            System.out.println("you suck");
            m.addAttribute("error", "you lousy dog");
        }


        m.addAttribute("toSave", toSave);
        
        m.addAttribute("articleList", articleList);
        
        return "homepage";
    }

    @PostMapping(path="/articles")
    public String saveArticle(Model m) throws IOException {

        List<Article> toSave = new LinkedList<>();
        List<Article> articleList = service.getArticles();

        for (Article a : articleList){
            System.out.println(!a.isSave());
            if(!a.isSave()){
                toSave.add(a);
            }
        }
        service.saveArticles(toSave);
        toSave.clear();
        m.addAttribute("success", "Articles successfully saved");

        
        m.addAttribute("articleList", articleList);
        return "homepage";
    }
}
