package com.avyay.homora.managers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.avyay.homora.dtos.PropertyDTO;
import com.avyay.homora.entities.PropertyEntity;
import com.avyay.homora.enums.PropertyTypeEnum;
import com.avyay.homora.mappers.PropertyMapper;
import com.avyay.homora.repos.PropertyRepo;
import com.avyay.homora.responses.PropertyResponse;

@Component
public class PropertyManager {
    @Autowired
    private PropertyRepo propertyRepo;

    public PropertyDTO save(PropertyDTO propertyDTO) {
        PropertyEntity propertyEntity = PropertyMapper.INSTANCE.toEntity(propertyDTO);

        return PropertyMapper.INSTANCE.toPropertyDTO(propertyRepo.save(propertyEntity));
    }

    public PropertyDTO findById(long id) {

        Optional<PropertyEntity> entity = propertyRepo.findById(id);

        // return entity.map(PropertyMapper.INSTANCE::toPropertyDTO).orElse(null);

        if (entity.isPresent()) {
            return PropertyMapper.INSTANCE.toPropertyDTO(entity.get());
        }
        return null;
    }

    public Page<PropertyResponse> getAllProperties(String title, String location, Double minPrice, Double maxPrice,
            PropertyTypeEnum type, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<PropertyEntity> propertyEntityPage = propertyRepo.findAllWithFilters(title, location, minPrice, maxPrice, type,
                pageable);

        return PropertyMapper.INSTANCE.toPagePropertyResponse(propertyEntityPage);
    }   

    public List<PropertyDTO> getPropertiesByOwner(Long ownerId){
        return PropertyMapper.INSTANCE.toPropertyDTOList(propertyRepo.findByOwnerId(ownerId));
    }

}
