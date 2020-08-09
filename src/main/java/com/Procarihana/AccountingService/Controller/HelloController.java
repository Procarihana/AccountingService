package com.Procarihana.AccountingService.Controller;

import com.Procarihana.AccountingService.Moudle.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {

    private AtomicLong counter = new AtomicLong();

    @GetMapping("greeting/")
    public Greeting sayHello(@RequestParam("name") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format("Hello,%s", name));
    }

}
