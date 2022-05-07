package example.web;

import example.model.entity.SongEntity;
import example.service.SongsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {
    private final SongsService songsService;

    public HomeController(SongsService songsService) {
        this.songsService = songsService;
    }

    @ModelAttribute("allSongs")
    public List<SongEntity> allSongs() { return songsService.findAll();}

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
