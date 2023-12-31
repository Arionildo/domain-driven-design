package com.ari.domaindrivendesign.domain.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity<T extends Serializable> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
