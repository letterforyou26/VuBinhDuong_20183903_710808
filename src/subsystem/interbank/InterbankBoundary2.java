package subsystem.interbank;

import common.exception.UnrecognizedException;
import utils.API;

// DuongVB - 20183903
public class InterbankBoundary2 {
	String query(String url, String data) {
		String response = null;
		try {
			response = API.post(url, data, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UnrecognizedException();
		}
		return response;
	}
}
