package utils;

import entity.order.Order;

// Vũ Bình Dương - 20183903
public interface ShippingFeeCalculator {
	public int calculateShippingFee(Order order);
}
