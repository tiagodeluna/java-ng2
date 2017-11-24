package com.skipthedishes.api.services.order;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
public class OrderManagementServiceImplTest extends BaseTest {

	@Mock
	private OrderRepository orderRepository;
	@Mock
	private CustomerRepository customerRepository;
	
	@InjectMocks
	private OrderManagementServiceImpl service; 
	
	private Order newOrder;
	private Order savedOrder;
	private Customer customer;
	private String orderId = "11111";
	
	@Before
	public void setUp() {
		this.newOrder = new Order();
		this.newOrder.setStatus(OrderStatusEnum.PROCESSING);
		
		this.savedOrder = new Order();
		this.savedOrder.setId(this.orderId);
		this.savedOrder.setStatus(OrderStatusEnum.PROCESSING);
		this.savedOrder.setTotal(1000.0);
		this.savedOrder.setPaymentMethod(PaymentMethodsEnum.DISH_POINTS);
		
		this.customer = new Customer();
		this.customer.setId("111");
		this.customer.setDishCoin(1000);
		this.savedOrder.setCustomerId(this.customer.getId());
	}
	
	@Test
	public void testSave_NewOrder_ReturnsOrder() {
		//Arrange
		Order expected = new Order();
		expected.setId(this.orderId);
		expected.setStatus(this.newOrder.getStatus());
		
		Mockito.when(this.orderRepository.save(this.newOrder)).thenReturn(expected);
		
		//Act
		Order actual = this.service.saveOrder(this.newOrder);
		
		//Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFinishOrder_NonExistentId_ReturnsFalse() throws InvalidOrderTotalException {
		//Arrange
		String invalidId = "99999";
		Mockito.when(this.orderRepository.findOne(invalidId)).thenReturn(null);
		
		//Act
		Boolean actual = this.service.finishOrder(invalidId, PaymentMethodsEnum.DISH_POINTS);
		
		//Assertion
		assertEquals(Boolean.FALSE, actual);
	}

	@Test
	public void testFinishOrder_OrderTotalIsNull_ThrowsInvalidOrderTotalException() {
		//Arrange
		this.savedOrder.setTotal(null);
		Mockito.when(this.orderRepository.findOne(this.orderId)).thenReturn(this.savedOrder);
		
		try {
			//Act
			this.service.finishOrder(this.orderId, PaymentMethodsEnum.DISH_POINTS);
			//Assertion
			fail("Should have thrown InvalidOrderTotalException");
		} catch (InvalidOrderTotalException e) {
			//Good!
		}
	}

	@Test
	public void testFinishOrder_OrderTotalIsNegative_ThrowsInvalidOrderTotalException() {
		//Arrange
		this.savedOrder.setTotal(-100.0);
		Mockito.when(this.orderRepository.findOne(this.orderId)).thenReturn(this.savedOrder);
		
		try {
			//Act
			this.service.finishOrder(this.orderId, PaymentMethodsEnum.DISH_POINTS);
			//Assertion
			fail("Should have thrown InvalidOrderTotalException");
		} catch (InvalidOrderTotalException e) {
			//Good!
		}
	}

	@Test
	public void testFinishOrder_UnsuficientDishCoins_ReturnsFalse() throws InvalidOrderTotalException {
		//Arrange
		this.customer.setDishCoin(500);
		Mockito.when(this.orderRepository.findOne(this.orderId)).thenReturn(this.savedOrder);
		Mockito.when(this.customerRepository.findOne(this.customer.getId())).thenReturn(this.customer);

		//Act
		Boolean actual = this.service.finishOrder(this.orderId, PaymentMethodsEnum.DISH_POINTS);
		
		//Assertion
		assertEquals(Boolean.FALSE, actual);
	}
}
