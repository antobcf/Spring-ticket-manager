package com.assetmanagement.AssetManagement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:9001")
@RequestMapping("/asset")
public class AssetController {
    
    @Autowired
    private AssetRepository repo;

    @GetMapping("")
    public List<Asset> getAllAssets() {
        List<Asset> assets = repo.findAll();
        return assets;
    }

    @PostMapping("/new")
    public void createAsset(@RequestBody Asset asset) {
        repo.save(asset);
    } 

    @PutMapping("/update/{id}")
    public void changeStatus(@PathVariable("id") Long id, @RequestBody Asset assetStatus) {
        Optional<Asset> assetOpt = repo.findById(id);
        Asset asset = assetOpt.get();
        if(asset != null) {
            asset.setStatus(assetStatus.getStatus());
            repo.save(asset);
        } 
    }

    @DeleteMapping("/delete/{id}")
    public void deteleAsset(@PathVariable("id") Long id) {
        repo.deleteById(id);
    }

}
