package org.egdeveloper.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainWebController {

    @RequestMapping("/showHelpPage")
    public String showHelpPage(@RequestParam Double lat, @RequestParam Double lng, @RequestParam Integer heartRate, ModelMap modelMap){
        modelMap.put("latitude", lat);
        modelMap.put("longitude", lng);
        modelMap.put("heartRate", heartRate);
        return "alarmpage";
    }
}
