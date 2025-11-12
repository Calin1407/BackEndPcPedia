package com.BackEndPcPedia.shared.domain.valueobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

/**
 * Value Object to register Email
 */
@Embeddable
public class Email {
    @Column(name="email",nullable=false,length=100,unique=true)
    private String value;

    protected Email() {}
    public Email(String _value) {

        System.out.println("Email recibido: [" + _value + "]"); // <-- línea temporal

        if (_value == null || _value.isBlank())
            throw new IllegalArgumentException("Email cannot be null or blank");

        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!_value.matches(regex))
            throw new IllegalArgumentException("Invalid email address");

        this.value = _value.trim().toLowerCase();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email other)) return false;

        return Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return value!=null? value.hashCode(): 0;
    }

    @Override
    public String toString(){
        return value;
    }
}
