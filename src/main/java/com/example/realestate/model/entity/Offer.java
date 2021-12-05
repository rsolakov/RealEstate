package com.example.realestate.model.entity;

import com.example.realestate.model.entity.enums.CategoryEstateEnum;
import com.example.realestate.model.entity.enums.DealType;
import com.example.realestate.model.entity.enums.RegionEnum;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private CategoryEstateEnum categoryEstateEnum;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DealType type;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer rooms;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer floor;
    @Column(nullable = false)
    private Double m2;
    @Column
    private Integer year;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RegionEnum region;
    @Column(nullable = false)
    private String town;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column
    private String imageUrl;
    @ManyToOne
    private User seller;

    public Offer() {
    }

    public DealType getType() {
        return type;
    }

    public void setType(DealType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Double getM2() {
        return m2;
    }

    public void setM2(Double m2) {
        this.m2 = m2;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public RegionEnum getRegion() {
        return region;
    }

    public void setRegion(RegionEnum region) {
        this.region = region;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public CategoryEstateEnum getCategoryEstateEnum() {
        return categoryEstateEnum;
    }

    public void setCategoryEstateEnum(CategoryEstateEnum categoryEstateEnum) {
        this.categoryEstateEnum = categoryEstateEnum;
    }
}
