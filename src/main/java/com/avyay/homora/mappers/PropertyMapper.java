package com.avyay.homora.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.avyay.homora.dtos.PropertyDTO;
import com.avyay.homora.entities.PropertyEntity;
import com.avyay.homora.projection.PropertyProjection;
import com.avyay.homora.requests.CreatePropertyRequest;
import com.avyay.homora.responses.PropertyProjectionResponse;
import com.avyay.homora.responses.PropertyResponse;

@Mapper
public interface PropertyMapper {
    PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);

    PropertyEntity toEntity(PropertyDTO propertyDTO);

    PropertyDTO toPropertyDTO(PropertyEntity propertyEntity);

    PropertyDTO toPropertyDTO(CreatePropertyRequest createPropertyRequest);

    PropertyResponse toPropertyResponse(PropertyDTO propertyDTO);

    default Page<PropertyResponse> toPagePropertyResponse(Page<PropertyEntity> propertyEntityPage) {
        return propertyEntityPage.map(this::toResponse);
    }

    PropertyResponse toResponse(PropertyEntity entity);

    List<PropertyDTO> toPropertyDTOList(List<PropertyEntity> propertyList);

    List<PropertyResponse> toPropertyResponseList(List<PropertyDTO> properties);

    // projection

    PropertyProjectionResponse toResponse(PropertyProjection projection);

    default Page<PropertyProjectionResponse> toPagePropertyProjectionResponse(
            Page<PropertyProjection> propertyProjection) {
        return propertyProjection.map(this::toResponse);
    }
}
