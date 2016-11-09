package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	Button kupsisenupp;// loob uue nupu nimega kupsis
	int kupsis = 0;
	TextField kuipalju;
	
	Button upgrade;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Kupsiseklikker 0.1");// pealkiri aknal

		Text kuipalju = new Text();
		kuipalju.setText(kupsis + " kupsist");
		kuipalju.setTextAlignment(TextAlignment.CENTER);// tekst keskel
		
		upgrade = new Button();
		upgrade.setText("Upgrade");
		upgrade.setOnAction(e ->{
			if(kupsis >= 10){
				
			}
		});
		
		
		

		kupsisenupp = new Button();
		kupsisenupp.setText("+kupsis");
		kupsisenupp.setOnAction(e -> {
			kupsis++;// lisab yhe kupsise
			kuipalju.setText(kupsis + " kupsist");// e-> eventhandler lyhend
		});

		StackPane layout = new StackPane();
		layout.getChildren().add(kuipalju);

		layout.getChildren().add(kupsisenupp);// toob nupu nahtavale
		StackPane.setAlignment(kuipalju, Pos.TOP_CENTER);

		Scene scene = new Scene(layout, 400, 400);// kui suur aken on

		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
