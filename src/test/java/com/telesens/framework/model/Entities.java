package com.telesens.framework.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Entities<T> extends ForwardingSet<T> {

    private Set<T> delegate;

    public Entities() {
        this.delegate = new HashSet<>();
    }

    public Entities(Entities<T> entities) {
        this.delegate = new HashSet<>(entities.delegate);
    }

    @Override
    protected Set<T> delegate() {
        return delegate;
    }

    public Entities<T> withAdded(T entity) {
        Entities<T> entities = new Entities(this);
        entities.add(entity);
        return entities;
    }

    public Entities<T> without(T entity) {
        Entities<T> entities = new Entities(this);
        entities.remove(entity);
        return entities;
    }

    public Entities<T> withModified(T entityBefore, T entityAfter) {
        Entities<T> entities = new Entities(this);
        entities.remove(entityBefore);
        entities.add(entityAfter);
        return entities;
    }

}

