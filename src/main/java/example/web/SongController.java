package example.web;


import example.model.binding.AddSongBindingModel;
import example.model.entity.SongEntity;
import example.model.service.SongServiceModel;
import example.service.SingerService;
import example.service.SongsService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final ModelMapper modelMapper;
    private final SongsService songsService;
    private final SingerService singerService;


    public SongController(ModelMapper modelMapper, SongsService songService, SingerService singerService) {
        this.modelMapper = modelMapper;
        this.songsService= songService;
        this.singerService = singerService;
    }

    @ModelAttribute("allSongs")
    public List<SongEntity> allSongs(){ return songsService.findAll();}

    @ModelAttribute("addSongBindingModel")
    public AddSongBindingModel createBindingModel(){return new AddSongBindingModel();}


    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addSong(Model model){
        model.addAttribute("singers", singerService.findAllSingers());
        return "add-song";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addSongConfirm(@Valid AddSongBindingModel addSongBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 @AuthenticationPrincipal UserDetails principal){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addSongBindingModel", addSongBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addSongBindingModel", bindingResult);
            return "redirect:/songs/add";
        }

        if (songsService.containsSong(addSongBindingModel.getName())){
            redirectAttributes.addFlashAttribute("addSongBindingModel", addSongBindingModel);
            redirectAttributes.addFlashAttribute("exists", true);
            return "redirect:/songs/add";
        }


        SongServiceModel songServiceModel = modelMapper
                .map(addSongBindingModel, SongServiceModel.class);

        songServiceModel.setUser(principal.getUsername());

        songsService.createSong(songServiceModel);
        return "redirect:/home";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(){
        return "delete-song";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id){
        songsService.deleteSong(id);
        return "redirect:/songs/delete";
    }


}
