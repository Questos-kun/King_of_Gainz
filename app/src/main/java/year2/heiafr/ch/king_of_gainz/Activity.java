package year2.heiafr.ch.king_of_gainz;

/**
 * Created by samue on 07.06.2018.
 */

public class Activity {

    private int id;
    private String date;
    private String type;
    private String name;
    private String quantity;
    private int calories;
    private double fat;
    private double protein;
    private double carboHydrate;

    public Activity(int id, String date, String type, String name, String quantity, int calories, double fat, double protein, double carboHydrate) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.name = name;
        this.quantity = quantity;
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.carboHydrate = carboHydrate;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarboHydrate() {
        return carboHydrate;
    }

    public void setCarboHydrate(double carboHydrate) {
        this.carboHydrate = carboHydrate;
    }
}
