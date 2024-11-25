package fyi.shycat.site.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("health")
public class HealthController {

    @GetMapping("")
    public boolean checkHealth() {
        return true;
    }
}
