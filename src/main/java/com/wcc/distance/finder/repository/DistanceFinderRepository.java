package com.wcc.distance.finder.repository;

import com.wcc.distance.finder.repository.entitiy.DistanceFinderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DistanceFinderRepository Interface
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2021
 */
public interface DistanceFinderRepository extends JpaRepository<DistanceFinderEntity, String> {
}
