package com.mandiri.projectbe1.service.implementation;

import com.mandiri.projectbe1.constant.ResponseMessage;
import com.mandiri.projectbe1.dto.StoreSearchDTO;
import com.mandiri.projectbe1.entity.Store;
import com.mandiri.projectbe1.repository.StoreRepository;
import com.mandiri.projectbe1.service.StoreService;
import com.mandiri.projectbe1.specification.StoreSpecification;
import com.mandiri.projectbe1.utils.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store updateStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public List<Store> getAllStore() {
        return storeRepository.findAll();
    }

    @Override
    public Store getStoreById(String storeId) {
    if (storeRepository.findById(storeId).isPresent()){
        return storeRepository.findById(storeId).get();
    }else {
        throw new DataNotFoundException(String.format(ResponseMessage.NOT_FOUND_MESSAGE));
    }
    }

    @Override
    public Page<Store> getStorePerPage(Pageable pageable, StoreSearchDTO storeSearchDTO) {
        Specification<Store> storeSpecification = StoreSpecification.getSpecification(storeSearchDTO);
        return storeRepository.findAll(storeSpecification, pageable);
    }
}
