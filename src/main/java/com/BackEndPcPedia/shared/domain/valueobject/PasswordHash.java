package com.BackEndPcPedia.shared.domain.valueobject;

import jakarta.persistence.Column;

import java.util.Objects;

public class PasswordHash {

    @Column(name="password",nullable=false,length=100)
    private String value;

    protected PasswordHash() {}
    public PasswordHash(String _value) {
        if (_value == null) {
            throw new IllegalArgumentException("password required");
        }
        String v = _value.trim();
        if (v.length() < 20) {
            throw new IllegalArgumentException("password to short");
        }
        this.value = v;
    }

    public static PasswordHash of(String _value) {
        return new PasswordHash(_value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PasswordHash other)) return false;
        return Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return value!=null? value.hashCode():0;
    }

    @Override
    public String toString() {
        return "****";
    }
}
