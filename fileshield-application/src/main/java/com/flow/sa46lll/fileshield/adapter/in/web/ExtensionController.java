package com.flow.sa46lll.fileshield.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExtensionController {

    @GetMapping("/home")
    public String getExtensionList() {
        return "index";
    }
}
