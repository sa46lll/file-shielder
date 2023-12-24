package com.flow.sa46lll.fileshield.api;

import com.flow.sa46lll.fileshield.dto.GetExtensionsResponse;
import com.flow.sa46lll.fileshield.port.in.GetExtensionQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExtensionController {

    private final GetExtensionQuery getExtensionQuery;

    public ExtensionController(final GetExtensionQuery getExtensionQuery) {
        this.getExtensionQuery = getExtensionQuery;
    }

    @GetMapping("/home")
    public String getExtensionList(Model model) {
        model.addAttribute("extensions", getExtensionQuery.findAll());
        return "index";
    }
}
