package org.phuongnq.service.parent.controller;

import lombok.RequiredArgsConstructor;
import org.phuongnq.service.parent.entity.SampleData;
import org.phuongnq.service.parent.service.SampleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SampleController {

    private final SampleService service;

    @GetMapping
    public List<SampleData> getAll() {
        return service.getAll();
    }

    @PostMapping
    public void create(@RequestBody String name) {
        service.create(name);
    }
}
