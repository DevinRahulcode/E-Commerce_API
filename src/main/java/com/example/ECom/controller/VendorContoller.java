package com.example.ECom.controller;

import com.example.ECom.exception.RecordNotFoundException;
import com.example.ECom.model.Vendor;
import com.example.ECom.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorContoller {

    @Autowired
    private VendorRepository vendorRepository;

    @PostMapping
    public Vendor addVendor(@RequestBody Vendor vendor){
        return vendorRepository.save(vendor);
    }


    @GetMapping
    public List<Vendor> getAllVendors(Vendor vendor){
        return vendorRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Vendor> getVendorByID(@PathVariable int id){
        Vendor vendor = vendorRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Record Is not Found"+ id));

        return ResponseEntity.ok(vendor);
    }

    @PutMapping("{id}")
    public ResponseEntity<Vendor> updateDetails(@PathVariable int id, @RequestBody Vendor updateVendorDetails){
        Vendor updateDetails = vendorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Record Not Found"+id));

        updateDetails.setShop(updateVendorDetails.getShop());
        updateDetails.setLocation(updateVendorDetails.getLocation());

        vendorRepository.save(updateDetails);
        return ResponseEntity.ok(updateDetails);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteVendor(@PathVariable int id){
        Vendor vendor = vendorRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Record Not Found"+id));

        vendorRepository.delete(vendor);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
