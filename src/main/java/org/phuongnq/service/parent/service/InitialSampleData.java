package org.phuongnq.service.parent.service;

import lombok.RequiredArgsConstructor;
import org.phuongnq.service.parent.repository.SubSampleDataRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InitialSampleData implements ApplicationRunner {
    private final SubSampleDataRepository subSampleDataRepository;
    private final InitialSampleDataService initialSampleDataService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (subSampleDataRepository.count() > 0) return;
        initialSampleDataService.saveSubSampleData();
    }
}
