package assessmentpractice.ass.Controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import assessmentpractice.ass.Model.Article;
import assessmentpractice.ass.Model.Data;
import assessmentpractice.ass.Service.NewsService;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
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

    //add the consumes and produces stuff
    @PostMapping(path="/articles", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_HTML_VALUE)

    //requestbody stores values of form inputs from html form
    //best use to store json
    public String saveArticle(Model m, @RequestBody MultiValueMap <String, String> reqbody) throws IOException {

        //bracket in linked list can contain a collection to be added into the linked list
        List<Article> toSave = new LinkedList<>();

        toSave = reqbody
        .toSingleValueMap()
        .values()
        .stream()
        .map(v -> Article.fromJSON(v)).toList();

        if (toSave.isEmpty()) {

            m.addAttribute("empty", "You have tried to save nothing you dickhead");
        }

        List<Article> articleList = service.getArticles();

        service.saveArticles(toSave);
        m.addAttribute("success", "Articles successfully saved");
        
        m.addAttribute("articleList", articleList);
        return "homepage";
    }

    @GetMapping("/saved")
    public String showSavedArticles(Model m, HttpSession session) {

        List<Article> toRetrieve = service.getAllArticles();

        if(toRetrieve.isEmpty()) {

            m.addAttribute("empty", "you saved nothing dipshit");
        }

        m.addAttribute("savedArticles", toRetrieve);
        return "saved";
    }
}
