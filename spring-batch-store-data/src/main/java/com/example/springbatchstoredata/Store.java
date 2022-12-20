package com.example.springbatchstoredata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    private Long id;
    private String storeName;
    private String address;
    private String roadAddress;
    private double x;
    private double y;

    @Override
    public String toString() {
        return "Store{" +
            "id=" + id +
            ", storeName='" + storeName + '\'' +
            ", address='" + address + '\'' +
            ", roadAddress='" + roadAddress + '\'' +
            ", x=" + x +
            ", y=" + y +
            '}';
    }
}
