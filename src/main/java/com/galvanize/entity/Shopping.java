package com.galvanize.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "shopping")
public class Shopping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shooperId")
    private long shopperId;
    @Enumerated(EnumType.STRING)
    private Expense expense;
    @Column(name = "description")
    private String description;
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    private Activity activity;
    @Column(name = "price")
    private long price;

    public Shopping(Expense expense, String description, String name, Activity activity, long price){
        this.shopperId = shopperId;
        this.expense = expense;
        this.description = description;
        this.name = name;
        this.activity = activity;
        this.price = price;
    }

    public Shopping(long shopperId, Expense expense, String description, String name, Activity activity, long price){
        this.expense = expense;
        this.description = description;
        this.name = name;
        this.activity = activity;
        this.price = price;
    }

    public Shopping(){
    }

    @Override
    public String toString() {
        return "Shopping{" +
                "id=" + shopperId +
                ", expense=" + expense +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", activity=" + activity +
                ", price=" + price +
                '}';
    }

    public long getShopperId() {
        return shopperId;
    }

    public void setShopperId(long id) {
        this.shopperId = id;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shopping)) return false;
        Shopping shopping = (Shopping) o;
        return Objects.equals(shopperId, shopping.shopperId) &&
                expense == shopping.expense &&
                Objects.equals(description, shopping.description) &&
                Objects.equals(name, shopping.name) &&
                activity == shopping.activity &&
                Objects.equals(price, shopping.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopperId, expense, description, name, activity, price);
    }
}
