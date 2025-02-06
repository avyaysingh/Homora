package com.avyay.homora.projection;

import com.avyay.homora.enums.PropertyTypeEnum;

public interface PropertyProjection {
    Long getId();

    String getTitle();

    Double getPrice();

    String getLocation();

    PropertyTypeEnum getType();
}
