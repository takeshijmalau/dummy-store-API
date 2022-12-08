package com.mandiri.projectbe1.specification;

import com.mandiri.projectbe1.dto.StoreSearchDTO;
import com.mandiri.projectbe1.entity.Store;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
public class StoreSpecification {
    public static Specification<Store> getSpecification(StoreSearchDTO storeSearchDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (storeSearchDTO.getStoreAddress() != null){
                Predicate storeAddressPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + storeSearchDTO.getStoreAddress().toLowerCase() + "%");
            }
            if (storeSearchDTO.getStoreLicense() != null){
                Predicate storeAddressPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + storeSearchDTO.getStoreAddress() + "%");
            }
            Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
            return criteriaBuilder.and(arrayPredicates);
        };
    }
}
