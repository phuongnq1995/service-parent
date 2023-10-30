package org.phuongnq.service.parent.service;

import lombok.RequiredArgsConstructor;
import org.phuongnq.service.parent.entity.SampleData;
import org.phuongnq.service.parent.repository.SampleDataRepository;
import org.phuongnq.service.parent.specification.SampleSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SampleService {
    private final SampleDataRepository repository;

    @Transactional(readOnly = true)
    public Page<SampleData> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public void create(String name) {
        var sampleData = SampleData.builder()
                .name(name)
                .build();

        repository.save(sampleData);
    }
}
