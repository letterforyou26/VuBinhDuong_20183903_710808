package utils;

import entity.order.Order;

public class Strategy2 implements ShippingFeeCalculator{
	
	/**
	 * @author DuongVB
	 * Calculate shipping fee by order's total quantity
	 */
	@Override
	public int calculateShippingFee(Order order) {
		return Configs.FEE_PER_MEDIA_STRATEGY_2 * order.getTotalQuantity();
	}
}
