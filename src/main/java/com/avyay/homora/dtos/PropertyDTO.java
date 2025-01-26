package com.avyay.homora.dtos;

import com.avyay.homora.enums.PropertyTypeEnum;

public class PropertyDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String location;
    private PropertyTypeEnum type;
    private String imageUrl;
    private UserDTO owner;

    public PropertyDTO() {

    }

    public PropertyDTO(Long id, String title, String description, Double price, String location, PropertyTypeEnum type,
            String imageUrl, UserDTO owner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.location = location;
        this.type = type;
        this.imageUrl = imageUrl;
        this.owner = owner;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public PropertyTypeEnum getType() {
        return this.type;
    }

    public void setType(PropertyTypeEnum type) {
        this.type = type;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UserDTO getOwner() {
        return this.owner;
    }

    public void setOwner(UserDTO userDTO) {
        this.owner = userDTO;
    }

}
