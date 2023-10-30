package org.phuongnq.service.parent.controller;

import lombok.RequiredArgsConstructor;
import org.phuongnq.service.parent.entity.SampleData;
import org.phuongnq.service.parent.service.SampleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SampleController {

    private final SampleService service;

    @GetMapping
    public Page<SampleData> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @PostMapping
    public void create(@RequestBody String name) {
        service.create(name);
    }
}
