package com.jjikmuk.sikdorak.store.query.response;

import com.jjikmuk.sikdorak.store.command.domain.Store;

public record StoreRadiusSearchResponse(

    long id,
    String storeName,
    String contactNumber,
    String addressName,
    String roadAddressName,
    double x,
    double y

) {

    public static StoreRadiusSearchResponse from(Store store) {
        return new StoreRadiusSearchResponse(store.getId(),
            store.getStoreName(),
            store.getContactNumber(),
            store.getAddressName(),
            store.getRoadAddressName(),
            store.getX(),
            store.getY());
    }

}
