package com.jjikmuk.sikdorak.store.command.domain;

import com.jjikmuk.sikdorak.common.domain.BaseTimeEntity;
import com.jjikmuk.sikdorak.common.domain.Deleted;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@NoArgsConstructor // for @Entity
@SQLDelete(sql = "UPDATE store SET deleted = true WHERE store_id = ?")
@Where(clause = "deleted = false")
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "place_id", unique = true)
    private Long placeId;

    @Embedded
    private StoreName storeName;

    @Embedded
    private StoreContactNumber contactNumber;

    @Embedded
    private Address address;

    @Embedded
    private StoreLocation location;

    @Embedded
    private Deleted deleted = Deleted.FALSE;

    public Store(String storeName, String contactNumber, Address address, Double x, Double y) {
        this.storeName = new StoreName(storeName);
        this.contactNumber = new StoreContactNumber(contactNumber);
        this.address = address;
        this.location = new StoreLocation(x, y);
    }

    public Store(Long placeId, String storeName, String contactNumber, Address address, Double x, Double y) {
        this.placeId = placeId;
        this.storeName = new StoreName(storeName);
        this.contactNumber = new StoreContactNumber(contactNumber);
        this.address = address;
        this.location = new StoreLocation(x, y);
    }

    public Long getId() {
        return id;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public String getStoreName() {
        return storeName.getStoreName();
    }

    public String getContactNumber() {
        return contactNumber.getContactNumber();
    }

    public String getAddressName() {
        return address.getAddressName();
    }

    public String getRoadAddressName() {
        return this.address.getRoadAddressName();
    }

    public Address getAddress() {
        return this.address;
    }

    public double getY() {
        return location.getY();
    }

    public double getX() {
        return location.getX();
    }

    public void editAll(String storeName, String contactNumber, Address address, Double y, Double x) {
        this.storeName = new StoreName(storeName);
        this.contactNumber = new StoreContactNumber(contactNumber);
        this.address = address;
        this.location = new StoreLocation(x, y);
    }

    public void delete() {
        deleted.delete();
    }
}
