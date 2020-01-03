package nl.alirezaa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductModel {
    private int id;
    private String name;
    private String description;
    private double price;
    private int amount;
    private String image_link;

    public ProductModel(){}

    public ProductModel(int id, String name, String description, double price, int amount, String image_link) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.image_link = image_link;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("_name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("_description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("price")
    public double getPrice() {
        return price;
    }

    @JsonProperty("_price")
    public void setPrice(double price) {
        this.price = price;
    }

    @JsonProperty("amount")
    public int getAmount() {
        return amount;
    }

    @JsonProperty("_amount")
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @JsonProperty("image_link")
    public String getImage_link() {
        return image_link;
    }

    @JsonProperty("_image_link")
    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }
}
