package com.code.order.bo;

import com.code.order.dao.OrderDAO;
import com.code.order.dat.Order;

import java.sql.SQLException;

public class OrderBOImpl implements OrderBO
{
    private OrderDAO dao;

    public void setDao(OrderDAO dao)
    {
        this.dao = dao;
    }

    public OrderDAO getDao()
    {
        return dao;
    }

    @Override
    public boolean placeOrder(Order order) throws SQLException {

        try
        {
            int result = dao.create(order);

            if(result == 0)
            {
                return false;
            }
        }
        catch (SQLException e)
        {
            throw new SQLException();
        }

        return true;
    }

    @Override
    public boolean cancelOrder(int id) throws SQLException {

        try
        {
            Order order = dao.read(id);
            order.setStatus("Cancelled");

            int result = dao.update(order);

            if(result == 0)
            {
                return false;
            }
        }
        catch (SQLException e)
        {
            throw new SQLException();
        }

        return true;

    }

    @Override
    public boolean deleteOrder(int id) throws SQLException {
        return false;
    }
}
