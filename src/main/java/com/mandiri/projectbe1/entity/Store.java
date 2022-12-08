package com.mandiri.projectbe1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Store {
    @Id
    @Column(name = "id_store")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name =  "system-uuid",strategy = "uuid")
    private String id;
    @Column(nullable = false)
    private String address;
    private String license;
}
