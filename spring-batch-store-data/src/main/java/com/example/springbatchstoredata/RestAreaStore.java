package com.example.springbatchstoredata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestAreaStore {

    private Long id;
    private String storeName;
    private double x;
    private double y;

    @Override
    public String toString() {
        return "RestAreaStore{" +
            "id=" + id +
            ", storeName='" + storeName + '\'' +
            ", x=" + x +
            ", y=" + y +
            '}';
    }
}
