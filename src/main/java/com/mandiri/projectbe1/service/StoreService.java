package com.mandiri.projectbe1.service;

import com.mandiri.projectbe1.entity.Store;

import java.util.List;

public interface StoreService {
    Store saveStore(Store store);
    Store updateStore(Store store);
    List<Store> getAllStore();
    Store getStoreById(String storeId);
}
