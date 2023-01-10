package stream;

import myClass.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyListClass {
    private String name;
    private int age;
    private String bz;
    private boolean flag;
    private Company company;

    public List<MyListClass> getTestLists(){
        List<MyListClass> lists = new ArrayList<>();
        Company c1 = new Company().setName("song1_c").setAge(1).setBz("song1_cbz");
        Company c2 = new Company().setName("song2_c").setAge(2).setBz("song2_cbz");
        Company c3 = new Company().setName("song3_c").setAge(3).setBz("song3_cbz");
        Company c4 = new Company().setName("song4_c").setAge(4).setBz("song4_cbz");
        Company c5 = new Company().setName("song5_c").setAge(5).setBz("song5_cbz");
        MyListClass m1 = new MyListClass().setName("song1").setAge(1).setBz("bz_song1").setFlag(true).setCompany(c1);
        MyListClass m2 = new MyListClass().setName("song2").setAge(2).setBz("bz_song2").setFlag(false).setCompany(c2);
        MyListClass m3 = new MyListClass().setName("song3").setAge(3).setBz("bz_song3").setFlag(true).setCompany(c3);
        MyListClass m4 = new MyListClass().setName("song4").setAge(4).setBz("bz_song4").setFlag(false).setCompany(c4);
        MyListClass m5 = new MyListClass().setName("song5").setAge(5).setBz("bz_song5").setFlag(true).setCompany(c5);
        lists.add(m1);
        lists.add(m2);
        lists.add(m3);
        lists.add(m4);
        lists.add(m5);
        return lists;
    }

    public MyListClass() {
    }

    public MyListClass(String name, int age, String bz, boolean flag, Company company) {
        this.name = name;
        this.age = age;
        this.bz = bz;
        this.flag = flag;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public MyListClass setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public MyListClass setAge(int age) {
        this.age = age;
        return this;
    }

    public String getBz() {
        return bz;
    }

    public MyListClass setBz(String bz) {
        this.bz = bz;
        return this;
    }

    public boolean isFlag() {
        return flag;
    }

    public MyListClass setFlag(boolean flag) {
        this.flag = flag;
        return this;
    }

    public Company getCompany() {
        return company;
    }

    public MyListClass setCompany(Company company) {
        this.company = company;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyListClass that = (MyListClass) o;
        return age == that.age &&
                flag == that.flag &&
                Objects.equals(name, that.name) &&
                Objects.equals(bz, that.bz) &&
                Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, bz, flag, company);
    }

    @Override
    public String toString() {
        return "MyListClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", bz='" + bz + '\'' +
                ", flag=" + flag +
                ", company=" + company +
                '}';
    }
}
