package org.phuongnq.service.parent.unit.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.phuongnq.service.parent.entity.SampleData;
import org.phuongnq.service.parent.repository.SampleDataRepository;
import org.phuongnq.service.parent.service.SampleService;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SampleServiceTest {

    @Mock
    SampleDataRepository repository;

    @InjectMocks
    SampleService service;

    @Test
    public void givenCountMethodMocked_WhenCountInvoked_ThenMockValueReturned() {
        when(repository.findAll()).thenReturn(List.of(
                SampleData.builder()
                        .id(UUID.fromString("e7b8ac10-e4b0-466d-b9a8-f48a23e22b92"))
                        .name("Sample1")
                        .build(),
                SampleData.builder()
                        .id(UUID.fromString("8bde912f-e912-4e94-b95c-7cc5d5847ece"))
                        .name("Sample2")
                        .build()
        ));

        var sampleDataList = service.getAll();

        assertThat(sampleDataList.size()).isEqualTo(2);

        verify(repository).findAll();
    }
}
