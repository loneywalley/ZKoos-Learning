package data;

public class User {
    private Integer id;
    private String name;
    private String gender;
    private String birthday;
    private String country;

    public User(Integer id, String name, String gender, String birthday, String country) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
