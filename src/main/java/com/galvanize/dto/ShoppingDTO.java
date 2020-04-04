package com.galvanize.dto;

import java.util.Objects;

public class ShoppingDTO {
    String name;
    String description;
    long price;

    public ShoppingDTO() {
    }

    public ShoppingDTO(String name, String description, long price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingDTO shoppingDTO = (ShoppingDTO) o;
        return Objects.equals(getName(), shoppingDTO.getName()) &&
                Objects.equals(getDescription(), shoppingDTO.getDescription()) &&
                Objects.equals(getPrice(), shoppingDTO.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price);
    }

    @Override
    public String toString() {
        return "ShoppingDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
