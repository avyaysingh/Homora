package com.avyay.homora.services;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.avyay.homora.dtos.PropertyDTO;
import com.avyay.homora.dtos.UserDTO;
import com.avyay.homora.enums.PropertyTypeEnum;
import com.avyay.homora.managers.PropertyManager;
import com.avyay.homora.managers.UserManager;
import com.avyay.homora.mappers.PropertyMapper;
import com.avyay.homora.requests.CreatePropertyRequest;
import com.avyay.homora.responses.PropertyResponse;

@Service
public class PropertyService {

    @Autowired
    private PropertyManager propertyManager;

    @Autowired
    private UserManager userManager;

    private final String UPLOAD_DIR = "D:\\CodN\\WebDev\\Projects\\property-listing\\homora\\uploads\\";

    public PropertyResponse createProperty(CreatePropertyRequest request, MultipartFile image, String ownerEmail)
            throws IOException {
        UserDTO onwer = userManager.getByEmail(ownerEmail);

        if (onwer == null) {
            throw new IllegalArgumentException("Owner not found");
        }

        String imagePath = null;
        if (image != null && !image.isEmpty()) {
            // Get the original file name and sanitize it (replace spaces and special chars)
            String originalFileName = image.getOriginalFilename();
            String sanitizedFileName = originalFileName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");

            // Generate a unique file name based on the current timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
            String uniqueFileName = timestamp + "_" + sanitizedFileName;

            // Combine directory path and unique file name
            String filePath = UPLOAD_DIR + uniqueFileName;

            // Save the image to the server
            image.transferTo(new File(filePath));
            imagePath = filePath;
        }

        PropertyDTO propertyDTO = new PropertyDTO();

        propertyDTO.setTitle(request.getTitle());
        propertyDTO.setDescription(request.getDescription());
        propertyDTO.setPrice(request.getPrice());
        propertyDTO.setLocation(request.getLocation());
        propertyDTO.setType(request.getType());
        propertyDTO.setOwner(onwer);
        propertyDTO.setImageUrl(imagePath);

        return PropertyMapper.INSTANCE.toPropertyResponse(propertyManager.save(propertyDTO));
    }

    public PropertyResponse editProperty(Long propertyId, CreatePropertyRequest request, String ownerEmail) {
        PropertyDTO propertyDTO = propertyManager.findById(propertyId);
        if (propertyDTO == null) {
            throw new IllegalArgumentException("Property not found");
        }

        if (!propertyDTO.getOwner().getEmail().equals(ownerEmail)) {
            throw new IllegalArgumentException("Owner email does not match");
        }

        propertyDTO.setTitle(request.getTitle());
        propertyDTO.setDescription(request.getDescription());
        propertyDTO.setPrice(request.getPrice());
        propertyDTO.setLocation(request.getLocation());
        propertyDTO.setType(request.getType());

        return PropertyMapper.INSTANCE.toPropertyResponse(propertyManager.save(propertyDTO));

    }

    public Page<PropertyResponse> getAllProperties(String title, String location, Double minPrice, Double maxPrice,
            PropertyTypeEnum type, int page, int size) {
        return propertyManager.getAllProperties(title, location, minPrice, maxPrice, type, page, size);
    }
}
