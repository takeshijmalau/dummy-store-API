package com.mandiri.projectbe1.controller;

import com.mandiri.projectbe1.constant.ApiUrlConstant;
import com.mandiri.projectbe1.constant.ResponseMessage;
import com.mandiri.projectbe1.dto.StoreSearchDTO;
import com.mandiri.projectbe1.entity.Store;
import com.mandiri.projectbe1.service.StoreService;
import com.mandiri.projectbe1.utils.PageResponseWrapper;
import com.mandiri.projectbe1.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUrlConstant.STORE_PATH)
public class StoreController {
    StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    public ResponseEntity<Response<Store>> saveStore(@RequestBody Store store){
        String message = String.format(ResponseMessage.DATA_INSERTED_MESSAGE);
        Response<Store> response = new Response<>();
        response.setMessage(message);
        response.setData(storeService.saveStore(store));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PutMapping
    public ResponseEntity<Response<Store>> updateStore (@RequestBody Store store){
        String message = String.format(ResponseMessage.DATA_UPDATED_MESSAGE);
        Response<Store> response = new Response<>();
        response.setMessage(message);
        response.setData(storeService.updateStore(store));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/all")
    public PageResponseWrapper<Store> getAllStore(@RequestParam(name = "page",defaultValue = "0") Integer page,
                                                  @RequestParam(name = "size",defaultValue = "2") Integer size,
                                                  @RequestParam(name = "sortBy", defaultValue = "address") String sort,
                                                  @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                  @RequestParam(name = "address") String address,
                                                  @RequestParam(name = "license") String license){
        Sort sort1 = Sort.by(Sort.Direction.fromString(direction), sort);
        Pageable pageable = PageRequest.of(page, size, sort1);
        StoreSearchDTO storeSearchDTO = new StoreSearchDTO();
        storeSearchDTO.setStoreAddress(address);
        storeSearchDTO.setStoreLicense(license);
        Page<Store> stores = storeService.getStorePerPage(pageable, storeSearchDTO);
        return new PageResponseWrapper<>(stores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Store>> getStoreById(@PathVariable String id){
        String message = String.format(ResponseMessage.GET_STORE_BY_ID_MESSAGE);
        Response<Store> response = new Response<>();
        response.setMessage(message);
        response.setData(storeService.getStoreById(id));
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Store>>deleteStore(@PathVariable String id){
        String message = String.format(ResponseMessage.DATA_DELETED_MESSAGE);
        Response<Store> response = new Response<>();
        response.setMessage(message);
        storeService.deleteStore(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
