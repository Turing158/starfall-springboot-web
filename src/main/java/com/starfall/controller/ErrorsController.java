package com.starfall.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ErrorsController implements ErrorController{
    @RequestMapping("/error")
    public String error(
            @RequestParam(value = "code",required = false,defaultValue = "404") String code
    ){
        return "error";
    }
    public String getErrorPath() {
        return "/error";
    }
}
