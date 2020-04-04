package com.galvanize.entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "shopping")
public class Shopping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Enumerated(EnumType.STRING)
    Expense expense;
    @Column
    String description;
    @Column
    String name;
    @Enumerated(EnumType.STRING)
    Activity activity;
    @Column
    long price;

    public Shopping(long l, Expense expensive, String brown_cat_watch, String cat_watch, Activity active, int i) {
    }

    public Shopping(Expense expense, String description, String name, Activity activity, long price){
        this.expense = expense;
        this.description = description;
        this.name = name;
        this.activity = activity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Shopping{" +
                "id=" + id +
                ", expense=" + expense +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", activity=" + activity +
                ", price=" + price +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
