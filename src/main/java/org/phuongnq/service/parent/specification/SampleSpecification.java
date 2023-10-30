package org.phuongnq.service.parent.specification;

import jakarta.persistence.criteria.JoinType;
import org.phuongnq.service.parent.entity.SampleData;
import org.springframework.data.jpa.domain.Specification;

public class SampleSpecification {

    public static Specification<SampleData> joinAll() {
        return (root, query, criteriaBuilder) -> {
            root.fetch("subSampleDataList", JoinType.LEFT);
            return null;
        };
    }
}
