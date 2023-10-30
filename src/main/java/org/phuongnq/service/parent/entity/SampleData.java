package org.phuongnq.service.parent.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@jakarta.persistence.Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SampleData implements Serializable {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @GeneratedValue
    @EqualsAndHashCode.Include
    private UUID id;
    private String name;

    @OneToMany(mappedBy = "sampleData", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<SubSampleData> subSampleDataList = new ArrayList<>();

    public void addSubSampleData(SubSampleData subSampleData) {
        subSampleDataList.add(subSampleData);
        subSampleData.setSampleData(this);
    }
}
