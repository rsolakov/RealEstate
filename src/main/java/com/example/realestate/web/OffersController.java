package com.example.realestate.web;

import com.example.realestate.exception.OfferNotFoundException;
import com.example.realestate.model.binding.OfferAddBindingModel;
import com.example.realestate.model.binding.OfferUpdateBindingModel;
import com.example.realestate.model.entity.enums.DealType;
import com.example.realestate.model.entity.enums.RegionEnum;
import com.example.realestate.model.service.OfferAddServiceModel;
import com.example.realestate.model.service.OfferUpdateServiceModel;
import com.example.realestate.model.view.DetailsView;
import com.example.realestate.service.EstateModelService;
import com.example.realestate.service.OfferService;
import com.example.realestate.service.impl.EstateUser;
import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class OffersController {
    private final OfferService offerService;
    private final ModelMapper modelMapper;
    private final EstateModelService estateModelService;


    public OffersController(OfferService offerService,
                            ModelMapper modelMapper, EstateModelService estateModelService) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
        this.estateModelService = estateModelService;
    }

    @GetMapping("/offers/all")
    public String allOffers(Model model) {
        model.addAttribute("offers",
                offerService.getAllOffers());
        return "offers";
    }


    @GetMapping("/offers/{id}/details")
    public String showOffer(
            @PathVariable Long id, Model model,
            Principal principal) {
        model.addAttribute("offer", this.offerService.findById(id, principal.getName()));
        return "details";
    }


    @PreAuthorize("isOwner(#id)")
    @DeleteMapping("/offers/{id}")
    public String deleteOffer(@PathVariable Long id,
                              Principal principal) {

        offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }


    @GetMapping("/offers/{id}/edit")
    public String editOffer(@PathVariable Long id, Model model,
                            @AuthenticationPrincipal EstateUser currentUser) {

        DetailsView detailsView = offerService.findById(id, currentUser.getUserIdentifier());
        OfferUpdateBindingModel offerModel = modelMapper.map(
                detailsView,
                OfferUpdateBindingModel.class
        );
        model.addAttribute("offerModel", offerModel);
        model.addAttribute("dealTypes", DealType.values());
        model.addAttribute("regions", RegionEnum.values());
        return "update";
    }

    @GetMapping("/offers/{id}/edit/errors")
    public String editOfferErrors(@PathVariable Long id, Model model) {
        model.addAttribute("dealType", DealType.values());
        model.addAttribute("region", RegionEnum.values());
        return "update";
    }

    @PostMapping("/offers/{id}/edit")
    public String editOffer(
            @PathVariable Long id,
            @Valid OfferUpdateBindingModel offerModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) throws ObjectNotFoundException {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

            return "redirect:/offers/" + id + "/edit/errors";
        }

        OfferUpdateServiceModel serviceModel = modelMapper.map(offerModel,
                OfferUpdateServiceModel.class);
        serviceModel.setId(id);


        offerService.updateOffer(serviceModel);

        return "redirect:/offers/" + id + "/details";
    }

    @GetMapping("/offers/add")
    public String getAddOfferPage(Model model) {

        if (!model.containsAttribute("offerAddBindingModel")) {
            model.
                    addAttribute("offerAddBindingModel", new OfferAddBindingModel())
            .addAttribute("estateModels", estateModelService.getAllModels());
        }
        return "offer-add";
    }

    @PostMapping("/offers/add")
    public String addOffer(@Valid OfferAddBindingModel offerAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                         @AuthenticationPrincipal EstateUser user
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerAddBindingModel", offerAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerAddBindingModel", bindingResult);
            return "redirect:/offers/add";
        }
OfferAddServiceModel savedOfferAddServiceModel = offerService.addOffer(offerAddBindingModel, user.getUserIdentifier());
return "redirect:/offers/" + savedOfferAddServiceModel.getId() + "/details";

    }

    @ExceptionHandler({OfferNotFoundException.class})
    public ModelAndView handleOfferNotFound(OfferNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("statusCode", e.getStatusCode());

        return modelAndView;
    }
}


