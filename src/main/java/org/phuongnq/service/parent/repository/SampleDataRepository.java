package org.phuongnq.service.parent.repository;

import org.phuongnq.service.parent.entity.SampleData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SampleDataRepository extends JpaRepository<SampleData, UUID>, JpaSpecificationExecutor<SampleData> {

    Slice<SampleData> findAllByNameIsNotNull(Pageable page);
}
