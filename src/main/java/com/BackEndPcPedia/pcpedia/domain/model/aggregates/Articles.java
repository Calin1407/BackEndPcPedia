package com.BackEndPcPedia.pcpedia.domain.model.aggregates;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name= "articles")
public class Articles {

    /**
     * Attributes of Articles
     */
    public enum Status {
        AVAILABLE,
        LEASED,
        MAINTAINED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_articles")
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 60)
    private String category;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Status status = Status.AVAILABLE;

    /**
     * Constructor Articles
     */
    protected Articles() {}
    private Articles(String _name, String _description,
                     String _category, BigDecimal _price,
                     Status _status) {
        this.name = _name;
        this.description = _description;
        this.category = _category;
        this.price = _price;
        this.status = _status;

        if (price.signum() < 0){
            throw new IllegalArgumentException("price cannot be negative");
        }
    }

    /**
     * method to create a new article
     * @param _name article's name
     * @param _description article's description
     * @param _category article's category
     * @param _price article's price
     * @param _status article's status
     * @return a new article in database
     */
    public static Articles create(String _name, String _description, String _category,
                                  BigDecimal _price, Status _status) {
        return new Articles(_name, _description, _category, _price, _status);
    }

    /**
     * Method to update information about articles and rules
     * @param _name new article's name
     * @param _description  new article's description
     * @param _category new article's category
     * @param _price new article's price
     */
    public void update(String _name, String _description, String _category,
                       BigDecimal _price) {
        if (status == Status.LEASED) {
            throw new IllegalArgumentException("status cannot be update when status is LEASED");
        }
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("name cannot be null or blank");
        }
        if (description == null || description.isBlank()){
            throw new IllegalArgumentException("description cannot be null or blank");
        }
        if (category == null || category.isBlank()){
            throw new IllegalArgumentException("category cannot be null or blank");
        }
        if (price.signum() < 0) {
            throw new IllegalArgumentException("price cannot be negative");
        }

        this.name = _name;
        this.description = _description;
        this.category = _category;
        this.price = _price;
    }

    /**
     * update article's status
     * @param _status new status
     */
    public void changeStatus(Status _status) {
        this.status = Objects.requireNonNull(_status);
    }

    /**
     * Getters
     */
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Status getStatus() {
        return status;
    }


}