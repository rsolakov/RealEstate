package com.example.realestate.model.view;

import com.example.realestate.model.entity.enums.CategoryEstateEnum;
import com.example.realestate.model.entity.enums.DealType;
import com.example.realestate.model.entity.enums.RegionEnum;

import java.math.BigDecimal;
import java.time.Instant;

public class DetailsView {

    private Long id;
    private DealType type;
    private String name;
    private Integer rooms;
    private BigDecimal price;
    private Integer floor;
    private Double m2;
    private Integer year;
    private RegionEnum region;
    private String town;
    private String description;
    private String imageUrl;
   private CategoryEstateEnum categoryEstateEnum;
    private String sellerUsername;
    private boolean canDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }




    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    public CategoryEstateEnum getCategoryEstateEnum() {
        return categoryEstateEnum;
    }

    public void setCategoryEstateEnum(CategoryEstateEnum categoryEstateEnum) {
        this.categoryEstateEnum = categoryEstateEnum;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
