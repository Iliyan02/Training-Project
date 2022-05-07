package example.web;


import example.model.binding.SingerAddBindingModel;
import example.model.entity.SingerEntity;
import example.model.service.SingerServiceModel;
import example.service.SingerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/singers")
public class SingersController {
    private final SingerService singerService;
    private final ModelMapper modelMapper;

    public SingersController(SingerService singerService, ModelMapper modelMapper) {
        this.singerService = singerService;
        this.modelMapper = modelMapper;
    }


    @ModelAttribute("allSingers")
    public List<SingerEntity> allSingers(){
        return singerService.findAll()
                .stream()
                .map(s -> modelMapper.map(s, SingerEntity.class))
                .collect(Collectors.toList());
    }

    @ModelAttribute("singerAddBindingModel")
    public SingerAddBindingModel attribute(){return new SingerAddBindingModel();}

    @GetMapping("/add")
    public String add(){
        return "add-singer";
    }

    @PostMapping("/add")
    public String addSinger(@Valid SingerAddBindingModel singerAddBindingModel,
                            RedirectAttributes redirectAttributes,
                            BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("singerAddBindingModel", singerAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.singerAddBindingModel", bindingResult);
            return "redirect:/singers/add";
        }

        if (singerService.containsSinger(singerAddBindingModel.getName())){
            redirectAttributes.addFlashAttribute("singerAddBindingModel", singerAddBindingModel);
            redirectAttributes.addFlashAttribute("exists", true);
            return "redirect:/singers/add";
        }

        SingerServiceModel singerServiceModel = modelMapper.map
                (singerAddBindingModel, SingerServiceModel.class);

        singerService.addSinger(singerServiceModel);

        return "redirect:/home";

    }

    @GetMapping("/delete")
    public String delete(){
        return "singer-delete";
    }

    @GetMapping("/delete/{id}")
    public String deleteSinger(@PathVariable Long id){
        singerService.deleteSinger(id);
        return "redirect:/singers/delete";
    }


}
