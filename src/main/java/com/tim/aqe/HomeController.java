package com.tim.aqe;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by thodkins on 31/08/2017.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }
}
