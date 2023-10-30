package org.phuongnq.service.parent.service;

import lombok.RequiredArgsConstructor;
import org.phuongnq.service.parent.entity.SampleData;
import org.phuongnq.service.parent.entity.SubSampleData;
import org.phuongnq.service.parent.repository.SampleDataRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class InitialSampleDataService {
    private final SampleDataRepository sampleDataRepository;

    @Transactional
    public void saveSubSampleData() {
        Slice<SampleData> slice = sampleDataRepository.findAllByNameIsNotNull(PageRequest.of(0, 20));

        List<SampleData> batch = slice.getContent();
        processObjects(batch);

        while (slice.hasNext()) {
            slice = sampleDataRepository.findAllByNameIsNotNull(slice.nextPageable());
            processObjects(slice.getContent());
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processObjects(List<SampleData> sampleDatas) {
        // Process and save to DB
        sampleDatas.stream().map(sampleData -> {
                    IntStream.rangeClosed(1, 10).forEach(i -> sampleData.addSubSampleData(SubSampleData.builder()
                            .name(sampleData.getName() + i)
                            .build()));
                    return sampleData;
                })
                .collect(Collectors.toList());

        sampleDataRepository.saveAllAndFlush(sampleDatas);
    }
}
