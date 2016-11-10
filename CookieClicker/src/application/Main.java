package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	int upgradehind = 10;
	
	Button upgrade2;
	int upgrade2hind = 100;
	int upgrade2lisa = 1;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Kupsiseklikker 0.2");// pealkiri aknal

		Text kuipalju = new Text();
		kuipalju.setText(kupsis + " kupsist");
		kuipalju.setTextAlignment(TextAlignment.CENTER);// tekst keskel

		upgrade = new Button();
		upgrade.setText("Upgrade, maksab " + upgradehind);
		upgrade.setOnAction(e -> {
			if (kupsis >= upgradehind) {
				kupsis -= upgradehind;// votab arvelt 10 maha
				upgradehind = upgradehind + upgradehind/4;//hinnale lisandub juurde 1/4 hinnast
				upgrade.setText("Upgrade, maksab " + upgradehind);//uuendab upgrade hinda
				kuipalju.setText(kupsis + " kupsist");
				Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), ev -> {// TAIMER

					kupsis++;
					kuipalju.setText(kupsis + " kupsist");
				}));
				timeline.setCycleCount(Animation.INDEFINITE);
				timeline.play();
			} else {
				System.out.println("liiga vahe");
				Alert alert = new Alert(AlertType.INFORMATION);// Alert aken
				alert.setTitle("Liiga vahe kupsiseid!");
				alert.setHeaderText("Upgrade maksab " + upgradehind);
				alert.show();
			}
		});
		
		upgrade2 = new Button();
		upgrade2.setText("Upgrade2, maksab " + upgrade2hind);
		upgrade2.setOnAction(e-> {
			if(kupsis >= upgrade2hind){
				kupsis -= upgrade2hind;
				upgrade2lisa++;
				upgrade2hind = upgrade2hind + upgrade2hind/4;
				upgrade2.setText("Upgrade2, maksab " + upgrade2hind);
				kuipalju.setText(kupsis + " kupsist");
			}else {
				System.out.println("liiga vahe");
				Alert alert = new Alert(AlertType.INFORMATION);// Alert aken 2
				alert.setTitle("Liiga vahe kupsiseid!");
				alert.setHeaderText("Upgrade2 maksab " + upgrade2hind);
				alert.show();
			}
		});
		
		

		kupsisenupp = new Button();
		kupsisenupp.setText("+kupsis");
		kupsisenupp.setOnAction(e -> {
			kupsis = kupsis + upgrade2lisa;// lisab int upgrade2lisa v6rra kupsiseid
			kuipalju.setText(kupsis + " kupsist");// e-> eventhandler lyhend
		});

		StackPane layout = new StackPane();
		layout.getChildren().add(kuipalju);

		layout.getChildren().add(kupsisenupp);// toob nupu nahtavale
		StackPane.setAlignment(kuipalju, Pos.TOP_CENTER);

		layout.getChildren().add(upgrade);
		StackPane.setAlignment(upgrade, Pos.TOP_LEFT);
		
		layout.getChildren().add(upgrade2);
		StackPane.setAlignment(upgrade2, Pos.TOP_RIGHT);
		
		Scene scene = new Scene(layout, 400, 400);// kui suur aken on
		
		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);//akna suurust ei saa muuta
		primaryStage.show();

	}

}
