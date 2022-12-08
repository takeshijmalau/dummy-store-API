package com.mandiri.projectbe1;

import com.mandiri.projectbe1.constant.ApiUrlConstant;
import com.mandiri.projectbe1.constant.ResponseMessage;
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

    public PageResponseWrapper<Store> getAllStore(@RequestParam(name = "page",defaultValue = "0") Integer page,
                                                  @RequestParam(name = "size",defaultValue = "2") Integer size,
                                                  @RequestParam(name = "sortBy", defaultValue = "address") String sort,
                                                  @RequestParam(name = "direction", defaultValue = "ASC") String direction){
        Sort sort1 = Sort.by(Sort.Direction.fromString(direction), sort);
        Pageable pageable = PageRequest.of(page, size);
        Page<Store> stores = storeService.getAllStore(pageable);
        return new PageResponseWrapper<>(stores);
    }
}
