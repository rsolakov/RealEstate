package com.example.realestate.web;

import com.example.realestate.service.EstateModelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class EstateModelController {

    private final EstateModelService estateModelService;
    private final ModelMapper modelMapper;

    public EstateModelController(EstateModelService estateModelService, ModelMapper modelMapper) {
        this.estateModelService = estateModelService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/models/all")
    public String allModels(Model model) {
        model.addAttribute("models",
                estateModelService.getAllModels());
        return "models";
    }


}
