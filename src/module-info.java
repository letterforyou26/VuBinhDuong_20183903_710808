module AIMS_Student {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires org.junit.jupiter.api;
	requires org.junit.jupiter.params;
	requires junit;
	requires com.jfoenix;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.graphics, javafx.fxml;
	opens views.screen to javafx.graphics, javafx.fxml;
	opens views.screen.home to javafx.graphics, javafx.fxml;
	opens views.screen.cart to javafx.graphics, javafx.fxml;
	opens views.screen.invoice to javafx.graphics, javafx.fxml;
	opens views.screen.payment to javafx.graphics, javafx.fxml;
	opens views.screen.popup to javafx.graphics, javafx.fxml;
	opens views.screen.shipping to javafx.graphics, javafx.fxml;
}
