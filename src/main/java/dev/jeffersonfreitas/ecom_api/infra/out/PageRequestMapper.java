package dev.jeffersonfreitas.ecom_api.infra.out;

import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequestInput;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public final class PageRequestMapper {

    public static Pageable toSpring(PageableRequestInput input){
        List<Sort.Order> orders = input.sort().stream()
                .map(order -> new Sort.Order(Sort.Direction.fromString(order.direction()), order.property())).toList();
        Sort sort = orders.isEmpty() ? Sort.unsorted() : Sort.by(orders);
        return PageRequest.of(input.page(), input.size(), sort);
    }
}
