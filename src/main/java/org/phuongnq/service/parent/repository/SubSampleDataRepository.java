package org.phuongnq.service.parent.repository;

import org.phuongnq.service.parent.entity.SubSampleData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubSampleDataRepository extends JpaRepository<SubSampleData, UUID> {
}
