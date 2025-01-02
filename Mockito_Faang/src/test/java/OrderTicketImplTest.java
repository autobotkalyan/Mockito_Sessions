import com.code.order.bo.OrderBOImpl;
import com.code.order.dao.OrderDAO;
import com.code.order.dat.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class OrderTicketImplTest
{
    private OrderBOImpl bo;

    @Mock
    private OrderDAO dao;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
        bo = new OrderBOImpl();
        bo.setDao(dao);
    }

    @Test
    public void placeOrderShouldCreateOrder() throws SQLException
    {
        Order order = new Order();
        when(dao.create(order)).thenReturn(1);

        boolean result = bo.placeOrder(order);
        assertTrue(result);
    }

    @Test
    public void placeOrderShouldNotCreateOrder() throws SQLException
    {
        Order order = new Order();
        when(dao.create(order)).thenReturn(0);

        boolean result = bo.placeOrder(order);
        assertFalse(result);
    }

    @Test
    public void cancelOrderShouldCancelTheOrder() throws SQLException
    {
        Order order = new Order();
        when(dao.read(5678)).thenReturn(order);
        when(dao.update(order)).thenReturn(1);

        boolean result = bo.cancelOrder(5678);
        assertTrue(result);
    }

    @Test
    public void cancelOrderShouldNotCancelTheOrder() throws SQLException
    {
        Order order = new Order();
        when(dao.read(12)).thenReturn(order);
        when(dao.update(order)).thenReturn(0);

        boolean result = bo.cancelOrder(12);
        assertFalse(result);
    }

    @Test
    public void placeOrderShouldThrowException() throws SQLException
    {
        Order order = new Order();
        when(dao.create(order)).thenThrow(SQLException.class);

        assertThrows(SQLException.class, ()-> {
            bo.placeOrder(order);
        });
    }
}
