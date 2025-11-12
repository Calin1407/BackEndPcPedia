package com.BackEndPcPedia.pcpedia.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="orders")
@EntityListeners(AuditingEntityListener.class)
public class Orders {

}
