package com.model.masterdata;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookStore {

    private Long id;
    private String name;
    private String address;

    @Override
    public String toString() {
        return "BookStore{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
