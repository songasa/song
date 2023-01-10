package lianshikaifa;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

//@Accessors(chain = true)  lombok 提供注解
@JsonPOJOBuilder
public class lianShi {
    private String name;
    private int age;
    public lianShi() {
    }

    public lianShi(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public lianShi setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public lianShi setAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        lianShi lianShi = (lianShi) o;
        return age == lianShi.age &&
                Objects.equals(name, lianShi.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "lianShi{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
