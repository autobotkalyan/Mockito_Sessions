package com.code.order.bo;

import com.code.order.dat.Order;

import java.sql.SQLException;

public interface OrderBO
{
    boolean placeOrder(Order order) throws SQLException;

    boolean cancelOrder(int id) throws SQLException;

    boolean deleteOrder(int id) throws SQLException;
}
