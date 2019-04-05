package org.secondKill.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/oom")
public class OutOfMemoryController {

    @RequestMapping("/1")
    public void generate() {
        List<Object> list = new ArrayList<>();
        for(;;) {
            list.add(new Object());
        }
    }
}
