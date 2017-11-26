package com.skipthedishes.api.services.order;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skipthedishes.api.BaseTest;
import com.skipthedishes.api.entities.Customer;
import com.skipthedishes.api.entities.Order;
import com.skipthedishes.api.entities.OrderStatusEnum;
import com.skipthedishes.api.entities.PaymentMethodsEnum;
import com.skipthedishes.api.exceptions.InvalidOrderTotalException;
import com.skipthedishes.api.repositories.CustomerRepository;
import com.skipthedishes.api.repositories.OrderRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceImplTest extends BaseTest {

	@Mock
	private OrderRepository orderRepository;
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private ApplicationEventPublisher publisher;

	@InjectMocks
	private OrderServiceImpl service;
	
	private Order newOrder;
	private Order orderSaved;
	private Customer customer;
	private String orderId = "11111";
	
	@Before
	public void setUp() {
		this.newOrder = new Order();
		this.newOrder.setStatus(OrderStatusEnum.PROCESSING);
		
		this.orderSaved = Mockito.spy(new Order());
		this.orderSaved.setId(this.orderId);
		this.orderSaved.setStatus(OrderStatusEnum.PROCESSING);
		this.orderSaved.setTotal(10.0);
		this.orderSaved.setPaymentMethod(PaymentMethodsEnum.DISH_COINS);
		
		this.customer = Mockito.spy(new Customer());
		this.customer.setId("111");
		this.customer.setDishCoin(100);
		this.orderSaved.setCustomerId(this.customer.getId());
	}

    @Test
    public void save_whenNewOrderIsProvided_thenReturnSavedOrder() {
        //Arrange
        Order expected = new Order();
        expected.setId(this.orderId);
        expected.setStatus(this.newOrder.getStatus());

        Mockito.when(this.orderRepository.save(this.newOrder)).thenReturn(expected);

        //Act
        Order actual = this.service.save(this.newOrder);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    public void update_whenOrderIsProvided_thenReturnSavedOrder() {
        //Arrange
        String id = this.orderSaved.getId();
        OrderStatusEnum newStatus = OrderStatusEnum.CANCELED;
        Order orderUpdated = this.orderSaved;
        orderUpdated.setStatus(newStatus);

        Mockito.when(this.orderRepository.findOne(id)).thenReturn(this.orderSaved);
        Mockito.verify(this.orderSaved).setStatus(newStatus);
        Mockito.when(this.orderRepository.save(this.orderSaved)).thenReturn(orderUpdated);

        //Act
        Order actual = this.service.update(id, orderUpdated);

        //Assert
        assertEquals(orderUpdated, actual);
    }

    @Test
    public void findById_whenNewOrderIsProvided_thenReturnSavedOrder() {
        //Arrange
        Order expected = new Order();
        expected.setId(this.orderId);
        expected.setStatus(this.newOrder.getStatus());

        Mockito.when(this.orderRepository.save(this.newOrder)).thenReturn(expected);

        //Act
        Order actual = this.service.save(this.newOrder);

        //Assert
        assertEquals(expected, actual);
    }
	
	@Test
	public void finishOrder_whenOrderIdDoesNotExist_thenReturnsFalse() throws InvalidOrderTotalException {
		//Arrange
		String invalidId = "99999";
		Mockito.when(this.orderRepository.findOne(invalidId)).thenReturn(null);

		try {
			//Act
			Boolean actual = this.service.finishOrder(invalidId, PaymentMethodsEnum.DISH_COINS);
			//Assertion
			fail("Should have thrown EmptyResultDataAccessException");
		} catch (EmptyResultDataAccessException e) {
			//Good!
		}
	}

	@Test
	public void finishOrder_whenTotalIsNull_thenThrowsInvalidOrderTotalException() {
		//Arrange
		this.orderSaved.setTotal(null);
		Mockito.when(this.orderRepository.findOne(this.orderId)).thenReturn(this.orderSaved);
		
		try {
			//Act
			this.service.finishOrder(this.orderId, PaymentMethodsEnum.DISH_COINS);
			//Assertion
			fail("Should have thrown InvalidOrderTotalException");
		} catch (InvalidOrderTotalException e) {
			//Good!
		}
	}

	@Test
	public void finishOrder_whenTotalIsNegative_thenThrowsInvalidOrderTotalException() {
		//Arrange
		this.orderSaved.setTotal(-100.0);
		Mockito.when(this.orderRepository.findOne(this.orderId)).thenReturn(this.orderSaved);
		
		try {
			//Act
			this.service.finishOrder(this.orderId, PaymentMethodsEnum.DISH_COINS);
			//Assertion
			fail("Should have thrown InvalidOrderTotalException");
		} catch (InvalidOrderTotalException e) {
			//Good!
		}
	}

	@Test
	public void finishOrder_whenDishCoinsAreInsufficient_thenReturnsFalse() throws InvalidOrderTotalException {
		//Arrange
		this.customer.setDishCoin(5);
		Mockito.when(this.orderRepository.findOne(this.orderId)).thenReturn(this.orderSaved);
		Mockito.when(this.customerRepository.findOne(this.orderSaved.getCustomerId())).thenReturn(this.customer);

		//Act
		Boolean actual = this.service.finishOrder(this.orderId, PaymentMethodsEnum.DISH_COINS);
		
		//Assertion
        Mockito.verify(this.orderSaved).setStatus(OrderStatusEnum.PAYMENT_FAILURE);
        assertEquals(OrderStatusEnum.PAYMENT_FAILURE, this.orderSaved.getStatus());
		assertEquals(Boolean.FALSE, actual);
	}

	@Test
	public void finishOrder_whenPayingWithDishCoins_thenReturnsTrue() throws InvalidOrderTotalException {
		//Arrange
		Mockito.when(this.orderRepository.findOne(this.orderId)).thenReturn(this.orderSaved);
		Mockito.when(this.customerRepository.findOne(this.orderSaved.getCustomerId())).thenReturn(this.customer);
		Mockito.when(this.customerRepository.save(this.customer)).thenReturn(this.customer);
		Mockito.when(this.orderRepository.save(this.orderSaved)).thenReturn(this.orderSaved);
        Mockito.when(this.customer.spendDishCoins(this.orderSaved.getValueInDishCoins())).thenReturn(Boolean.TRUE);

		//Act
		Boolean actual = this.service.finishOrder(this.orderId, PaymentMethodsEnum.DISH_COINS);

		//Assertion
		assertEquals(Boolean.TRUE, this.customer.spendDishCoins(this.orderSaved.getValueInDishCoins()));
        Mockito.verify(this.orderSaved).setStatus(OrderStatusEnum.PAYMENT_CONFIRMED);
        assertEquals(OrderStatusEnum.PAYMENT_CONFIRMED, this.orderSaved.getStatus());
		assertEquals(Boolean.TRUE, actual);
	}

	@Test
	public void finishOrder_whenPayingWithCash_thenReturnsTrue() throws InvalidOrderTotalException {
		//Arrange
		Mockito.when(this.orderRepository.findOne(this.orderId)).thenReturn(this.orderSaved);
		Mockito.when(this.customerRepository.findOne(this.orderSaved.getCustomerId())).thenReturn(customer);
		Mockito.when(this.customerRepository.save(customer)).thenReturn(customer);
		Mockito.when(this.orderRepository.save(this.orderSaved)).thenReturn(this.orderSaved);

		//Act
		Boolean actual = this.service.finishOrder(this.orderId, PaymentMethodsEnum.CASH);

		//Assertion
		Mockito.verify(this.customer).accumulateDishCoins(this.orderSaved.getTotal());
        Mockito.verify(this.orderSaved).setStatus(OrderStatusEnum.PAYMENT_CONFIRMED);
        assertEquals(OrderStatusEnum.PAYMENT_CONFIRMED, this.orderSaved.getStatus());
		assertEquals(Boolean.TRUE, actual);
	}

}
