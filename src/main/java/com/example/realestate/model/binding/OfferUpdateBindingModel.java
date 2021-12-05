package com.example.realestate.model.binding;


import com.example.realestate.model.entity.enums.DealType;
import com.example.realestate.model.entity.enums.RegionEnum;
import javax.validation.constraints.*;
import java.math.BigDecimal;

public class OfferUpdateBindingModel {

  @NotNull
  private Long Id;
  @NotNull
  private String name;
  @NotNull
  private Integer rooms;
  @NotNull
  @DecimalMin("1000")
  private BigDecimal price;
  @NotNull
  private Integer floor;
  @NotNull
  private Double m2;
  @NotNull
  @Min(1800)
  private Integer year;
  @NotNull
  private RegionEnum region;
  @NotNull
  private DealType dealType;
  @NotNull
  private String town;
  @NotEmpty
  private String description;
  private String imageUrl;

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
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

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public DealType getDealType() {
    return dealType;
  }

  public void setDealType(DealType dealType) {
    this.dealType = dealType;
  }
}
