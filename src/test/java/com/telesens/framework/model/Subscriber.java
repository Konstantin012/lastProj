package com.telesens.framework.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Objects;

@XStreamAlias("subscriber")
public class Subscriber {
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;

    public Subscriber() {
    }

    private Subscriber(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.gender = builder.gender;
    }

    public static Builder newSubscriber() {
        return new Builder();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscriber that = (Subscriber) o;
        return id == that.id &&
                age == that.age &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                gender == that.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, gender);
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }

    public static final class Builder {
        private long id;
        private String firstName;
        private String lastName;
        private int age;
        private Gender gender;

        private Builder() {
        }

        public Subscriber build() {
            return new Subscriber(this);
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }
    }
}
