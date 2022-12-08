package com.mandiri.projectbe1.service;

import com.mandiri.projectbe1.dto.StoreSearchDTO;
import com.mandiri.projectbe1.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreService {
    Store saveStore(Store store);
    Store updateStore(Store store);
    List<Store> getAllStore();
    Store getStoreById(String storeId);
    Page<Store>getStorePerPage(Pageable pageable, StoreSearchDTO storeSearchDTO);
}
