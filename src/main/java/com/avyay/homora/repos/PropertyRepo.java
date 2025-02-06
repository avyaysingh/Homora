package com.avyay.homora.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.avyay.homora.entities.PropertyEntity;
import com.avyay.homora.enums.PropertyTypeEnum;
import com.avyay.homora.projection.PropertyProjection;

@Repository
public interface PropertyRepo extends JpaRepository<PropertyEntity, Long> {

        @Query("SELECT p FROM PropertyEntity p WHERE " +
                        "(:title IS NULL OR p.title LIKE %:title%) AND" +
                        "(:location IS NULL OR p.location LIKE %:location%) AND " +
                        "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
                        "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
                        "(:type IS NULL OR p.type = :type)")
        Page<PropertyProjection> findAllWithFilters(
                        @Param("title") String title,
                        @Param("location") String location,
                        @Param("minPrice") Double minPrice,
                        @Param("maxPrice") Double maxPrice,
                        @Param("type") PropertyTypeEnum type,
                        Pageable pageable);

        List<PropertyEntity> findByOwnerId(Long ownerId);
}
