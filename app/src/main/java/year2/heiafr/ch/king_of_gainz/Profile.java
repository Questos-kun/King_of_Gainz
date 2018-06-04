package year2.heiafr.ch.king_of_gainz;

/**
 * Created by samue on 04.06.2018.
 */

public class Profile {

    private int id;
    private int age;
    private String sex;
    private int height;
    private int weight;
    private String activity;

    public Profile(int id, int age, String sex, int height, int weight, String activity) {
        this.id = id;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.activity = activity;
    }

    public int getId() { return id; }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
