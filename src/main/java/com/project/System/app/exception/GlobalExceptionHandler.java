package com.project.System.app.exception;

import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ModelAndView handleUnsupportedMediaType(HttpMediaTypeNotSupportedException ex) {
        ModelAndView modelAndView = new ModelAndView("upload");
        modelAndView.addObject("errormsg", "Nieobsługiwany format pliku! Tylko .json i .xml");
        return modelAndView;
    }
}
