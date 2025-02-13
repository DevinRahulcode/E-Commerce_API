package com.example.ECom.repository;

import com.example.ECom.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Items,Integer> {

}
