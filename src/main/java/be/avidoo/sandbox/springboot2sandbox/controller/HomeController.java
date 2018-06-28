package be.avidoo.sandbox.springboot2sandbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController extends BaseController {

    @GetMapping
    public String home() {
        return "home";
    }


}
