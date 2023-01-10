package myClass;

import java.util.Objects;

public class Company {
    private String name;
    private int age;
    private String bz;

    public Company(String name, int age, String bz) {
        this.name = name;
        this.age = age;
        this.bz = bz;
    }

    public interface abc{
        public abstract int show(int a);
    };

    public Company() {
    }

    @Override
    public String toString() {
        return "company{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", bz='" + bz + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return age == company.age &&
                Objects.equals(name, company.name) &&
                Objects.equals(bz, company.bz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, bz);
    }

    public String getName() {
        return name;
    }

    public Company setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Company setAge(int age) {
        this.age = age;
        return this;
    }

    public String getBz() {
        return bz;
    }

    public Company setBz(String bz) {
        this.bz = bz;return this;
    }
}
