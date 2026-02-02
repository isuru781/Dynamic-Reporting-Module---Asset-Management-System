package lk.isuru781.reportmodule.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    /**
     * Test endpoint to verify the API is working
     */
    @GetMapping("/test")
    public String test() {
        return "Dynamic Report Module API is working perfectly! 🎉";
    }
}

