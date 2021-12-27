package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import utils.Configs;

/**
 * This class controls the flow of place rush order usecase in our AIMS project
 * @author duongvb
 */
public class PlaceRushOrderController {

	/**
     * Calculate extra shipping fee of rush order
     * @return shippingFee
     */
	public static int calculateExtraShippingFee() {
		return 10;
	}
	
	public boolean checkProvinceSupportRushOrder(String province) {
        if (Arrays.asList(Configs.SUPPORT_RUSH_ORDER_PROVINCES).contains(province)) {
            return true;
        }
        
        return false;
    }

    /**
     * Method checks if media support rush order
     * @param mediaID
     */
    public boolean checkMediaSupportRushOrder(int mediaID) {
        if (Arrays.asList(Configs.MEDIA_SUPPORT_RUSH_ORDER_IDS).contains(mediaID)) {
            return true;
        }
        
        return false;
    }

    /**
     * validates rush order info
     * @param info: rush order info
     */
    public boolean validateRushOrderInfo(String info) {
        return info != null;
    }

    /**
     * validates rush order instruction
     * @param instruction: rush order instruction
     */
    public boolean validateRushOrderInstruction(String instruction) {
    	return instruction != null;
    }
    
    /**
     * validates receive time
     * @param time receive time
     */
    public boolean validateReceiveTime(String time) {
    	if (time == null) return false;
    	SimpleDateFormat dateFormat = new SimpleDateFormat(Configs.TIME_FORMATTER);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(time.trim());
        } catch (ParseException pe) {
            return false;
        }
        
        return true;
    }
}
