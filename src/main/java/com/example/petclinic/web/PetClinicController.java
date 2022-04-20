package com.example.petclinic.web;

import com.example.petclinic.service.PetClinicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller 
public class PetClinicController {

    @Autowired
    private PetClinicService petClinicService;

    @RequestMapping(path ="/owners", method=RequestMethod.GET)
    @ResponseBody
    public ModelAndView getOwners(){
       
        ModelAndView mav= new ModelAndView();
        mav.addObject("owners",petClinicService.findOwners() );
        mav.setViewName("owners");
        return mav;

    }
    

    @RequestMapping("/pcs")
    @ResponseBody
    public String welcome(){
        return "Welcome to PetClinic World!";
    }
   

}
