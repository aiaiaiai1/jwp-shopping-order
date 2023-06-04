package cart.repository;

import cart.dao.OrderCartItemDao;
import cart.dao.OrderDao;
import cart.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    private final OrderDao orderDao;
    private final OrderCartItemDao orderCartItemDao;

    public OrderRepository(OrderDao orderDao, OrderCartItemDao orderCartItemDao) {
        this.orderDao = orderDao;
        this.orderCartItemDao = orderCartItemDao;
    }

    public Long insert(Member member, Order order) {
        Long orderId = orderDao.insert(OrderEntity.of(member, order));
        List<OrderCartItem> orderCartItems = order.getOrderCartItems();
        for (OrderCartItem orderCartItem : orderCartItems) {
            orderCartItemDao.insert(OrderCartItemEntity.of(orderId, orderCartItem));
        }
        return orderId;
    }

}
