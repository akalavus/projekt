package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	Button leivanupp;// loob uue nupu nimega leivanupp
	int leib = 0;
	TextField kuipalju;// naitab,kui palju leiba on

	Button automaatneLeib;
	int upgradehind = 10;

	Button yksLisaLeib;
	int upgrade2hind = 100;
	int upgrade2lisa = 1;

	Button tehas;
	int tehasehind = 1000;
	int tehaselisa = 100;

	MediaPlayer mediaplayer;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Kurat, kus must leib on!? 0.3");// pealkiri aknal

		Text kuipalju = new Text();
		kuipalju.setText(leib + " musta leiba");
		kuipalju.setTextAlignment(TextAlignment.CENTER);// tekst keskel

		automaatneLeib = new Button();
		automaatneLeib.setText("Upgrade, maksab " + upgradehind);
		automaatneLeib.setOnAction(e -> {
			if (leib >= upgradehind) {
				leib -= upgradehind;// votab arvelt 10 maha
				upgradehind = upgradehind + upgradehind / 4;// hinnale lisandub
															// juurde 1/4
															// hinnast
				automaatneLeib.setText("Auto leib iga 10s " + upgradehind);// uuendab
																			// upgrade
																			// hinda
				kuipalju.setText(leib + " musta leiba");
				Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), ev -> {// TAIMER

					leib++;
					kuipalju.setText(leib + " musta leiba");
				}));
				timeline.setCycleCount(Animation.INDEFINITE);
				timeline.play();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);// Alert aken
				alert.setTitle("Sul on liiga vahe musta leiba!");
				alert.setHeaderText("Upgrade maksab " + upgradehind);
				alert.show();
			}
		});

		yksLisaLeib = new Button();
		yksLisaLeib.setText("+1 leib  " + upgrade2hind);
		yksLisaLeib.setOnAction(e -> {
			if (leib >= upgrade2hind) {
				leib -= upgrade2hind;
				upgrade2lisa++;
				upgrade2hind = upgrade2hind + upgrade2hind / 4;
				yksLisaLeib.setText("Upgrade2, maksab " + upgrade2hind);
				kuipalju.setText(leib + " musta leiba");
			} else {
				System.out.println("liiga vahe");
				Alert alert = new Alert(AlertType.INFORMATION);// Alert aken 2
				alert.setTitle("Sul on liiga vahe musta leiba!");
				alert.setHeaderText("Upgrade2 maksab " + upgrade2hind);
				alert.show();
			}
		});

		tehas = new Button();
		tehas.setText("Upgrade, maksab " + tehasehind);
		tehas.setOnAction(e -> {
			if (leib >= tehasehind) {
				leib -= tehasehind;// votab arvelt 10 maha
				tehasehind = tehasehind + tehasehind / 4;// hinnale lisandub
															// juurde 1/4
															// hinnast
				tehas.setText("Tehas " + tehasehind);// uuendab upgrade hinda
				kuipalju.setText(leib + " musta leiba");
				Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), ev -> {// TAIMER

					leib += tehaselisa;
					kuipalju.setText(leib + " musta leiba");
				}));
				timeline.setCycleCount(Animation.INDEFINITE);
				timeline.play();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);// Alert aken
				alert.setTitle("Sul on liiga vahe musta leiba!");
				alert.setHeaderText("Upgrade maksab " + upgradehind);
				alert.show();
			}
		});

		leivanupp = new Button();
		leivanupp.setText(" ");
		leivanupp.setOnAction(e -> {
			leib = leib + upgrade2lisa;// lisab int upgrade2lisa v6rra musta
										// leiba
			kuipalju.setText(leib + " musta leiba");// e-> eventhandler lyhend
		});

		Pane scene1 = new Pane();
		scene1.getChildren().add(kuipalju);
		kuipalju.setLayoutX(230);
		kuipalju.setLayoutY(50);

		scene1.getChildren().add(leivanupp);// toob nupu nahtavale
		leivanupp.setLayoutX(200);
		leivanupp.setLayoutY(200);
		leivanupp.getStyleClass().add("leivanupp");// leivapilt
		leivanupp.setPadding(new Insets(0));

		scene1.getChildren().add(automaatneLeib);
		automaatneLeib.setLayoutX(10);
		automaatneLeib.setLayoutY(10);
		automaatneLeib.setPadding(new Insets(0));

		scene1.getChildren().add(yksLisaLeib);
		yksLisaLeib.setLayoutX(10);
		yksLisaLeib.setLayoutY(35);
		;

		scene1.getChildren().add(tehas);
		tehas.setLayoutX(10);
		tehas.setLayoutY(60);
		;

		scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(new Scene(scene1, 400, 400));
		primaryStage.setResizable(false);// akna suurust ei saa muuta
		primaryStage.show();

		Alert info = new Alert(AlertType.INFORMATION);// Alert aken programmi
														// avamisel
		info.setTitle("Kurat, kus must leib on!? by Andres");
		info.setHeaderText(" tekst ");
		info.show();

		Media helifail = new Media("file:///home/andres/projekt/CookieClicker/bin/application/helid/kusmustleibon.mp3");
		mediaplayer = new MediaPlayer(helifail);
		mediaplayer.setAutoPlay(true);
		mediaplayer.setVolume(0.2);

	}

}
