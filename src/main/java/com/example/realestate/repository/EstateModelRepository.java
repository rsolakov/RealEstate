package com.example.realestate.repository;

import com.example.realestate.model.entity.EstateModel;
import com.example.realestate.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstateModelRepository extends JpaRepository<EstateModel, Long> {
}
