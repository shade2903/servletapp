package com.haiduk.converter;

import com.haiduk.domain.Order;
import com.haiduk.domain.Product;
import com.haiduk.dto.OrderDto;
import com.haiduk.dto.ProductDto;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper  {

    private MapperFacade mapperFacade;
    @Autowired
    public ProductMapper(MapperFacade mapperFacade){
        this.mapperFacade = mapperFacade;
    }



    public ProductDto toDto(Product entity) {

        return mapperFacade.map(entity, ProductDto.class);
    }

    public Product fromDto(ProductDto dto) {
        return mapperFacade.map(dto, Product.class);
    }
}
