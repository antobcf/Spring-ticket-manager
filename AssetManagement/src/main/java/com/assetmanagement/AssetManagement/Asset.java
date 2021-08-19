package com.assetmanagement.AssetManagement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "assets")
public class Asset {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long asset_id;

    @Column(nullable = false, length = 32)
    private String name;

    @Column(nullable = false, length = 32)
    private String status;
   
    public Asset() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return asset_id;
    }

    public void setId(Long asset_id) {
        this.asset_id = asset_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Asset(Long asset_id, String name, String status) {
        this.asset_id = asset_id;
        this.name = name;
        this.status = status;
    }
    
    

}
