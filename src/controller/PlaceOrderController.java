package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

import entity.cart.Cart;
import entity.cart.CartMedia;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;
import utils.ShippingFeeCalculator;
import utils.Strategy2;

/**
 * This class controls the flow of place order usecase in our AIMS project
 * @author nguyenlm
 */
public class PlaceOrderController extends BaseController{

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());
    
    /**
     * This method checks the avalibility of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeOrder() throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException{
        Order order = new Order();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), 
                                                   cartMedia.getQuantity(), 
                                                   cartMedia.getPrice());    
            order.getlstOrderMedia().add(orderMedia);
        }
        return order;
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void processDeliveryInfo(HashMap info) throws InterruptedException, IOException{
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateDeliveryInfo(info);
    }
    
    /**
   * The method validates the info
   * @param info
     * @return 
   * @throws InterruptedException
   * @throws IOException
   */
    public boolean validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
    	return validateAddress(info.get("address")) && validateName(info.get("name"))
    			&& validatePhoneNumber(info.get("phone"));
    }
    
    public boolean validatePhoneNumber(String phoneNumber) {
    	// check phoneNumber has 10 digits
    	if (phoneNumber.length() != 10) return false;
    	
    	// check phoneNumber start with 0
    	if (!phoneNumber.startsWith("0")) return false;
    	
    	// check phoneNumber contains only number
    	try {
			Integer.parseInt(phoneNumber);
		} catch (NumberFormatException e) {
			return false;
		}
    	
    	return true;
    }
    
    public boolean validateName(String name) {
    	// check name contains only letter and space
    	String regx = "^[\\p{L} .'-]+$";
        if (!name.matches(regx)) return false;
    	
        // check name length between 5 and 30
        if(name.length() < 5 || name.length() > 30) return false;
        
    	return true;
    }
    
    public boolean validateAddress(String address) {
    	// check address contains only letter, space and number
    	String regx = "^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$";
    	if (!address.matches(regx)) return false;
    	
    	 // check address length between 5 and 100
        if(address.length() < 5 || address.length() > 100) return false;
    	
    	return true;
    }
    

    /**
     * This method calculates the shipping fees of order
     * @author DuongVB
     * @param order
     * @return shippingFee
     */
    public int calculateShippingFee(Order order){
    	ShippingFeeCalculator shippingFeeCalculator = new Strategy2();
        return shippingFeeCalculator.calculateShippingFee(order);
    }
}
