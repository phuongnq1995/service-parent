package org.phuongnq.service.parent.repository;

import org.phuongnq.service.parent.entity.SampleData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SampleDataRepository extends JpaRepository<SampleData, UUID> {
}
