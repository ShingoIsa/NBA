package jp.co.ambitious.sample.nba.commons;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class CommonAdvice {
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {

        // ブランクをnullとして扱う
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
