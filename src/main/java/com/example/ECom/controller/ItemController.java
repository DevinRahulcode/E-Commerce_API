package com.example.ECom.controller;


import com.example.ECom.exception.RecordNotFoundException;
import com.example.ECom.model.Items;
import com.example.ECom.model.Vendor;
import com.example.ECom.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping
    public Items addItem(@RequestBody Items items){
        return itemRepository.save(items);
    }

    @GetMapping
    public List<Items> getAllItems(){
        return itemRepository.findAll();

    }

    @GetMapping("{id}")
    public ResponseEntity<Items> getItemsById(@PathVariable int id){
        Items items = itemRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record Not Found"+id));
        return ResponseEntity.ok(items);
    }

    @PutMapping("{id}")
    public ResponseEntity<Items> updateitems(@PathVariable int id, @RequestBody Items updateItemsDetails){
        Items updateitems = itemRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Record Not Found"+id));

        updateitems.setName(updateItemsDetails.getName());
        updateitems.setDescription(updateItemsDetails.getDescription());
        itemRepository.save(updateitems);
        return ResponseEntity.ok(updateitems);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable int id){

        Items items = itemRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Record Not Founded"+id));
        itemRepository.delete(items);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
