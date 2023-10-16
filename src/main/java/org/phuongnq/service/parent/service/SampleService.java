package org.phuongnq.service.parent.service;

import lombok.RequiredArgsConstructor;
import org.phuongnq.service.parent.entity.SampleData;
import org.phuongnq.service.parent.repository.SampleDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleService {
    private final SampleDataRepository repository;

    @Transactional(readOnly = true)
    public List<SampleData> getAll() {
        return repository.findAll();
    }

    @Transactional
    public void create(String name) {
        var sampleData = SampleData.builder()
            .name(name)
            .build();

        repository.save(sampleData);
    }
}
