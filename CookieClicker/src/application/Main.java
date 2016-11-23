package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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
	Text mustaleiba = new Text("leiba");
	
	Button automaatneLeib;
	int upgradehind = 10;

	Button yksLisaLeib;
	int upgrade2hind = 100;
	int upgrade2lisa = 1;
	
	Button vanaema;
	int vanaemahind = 500;
	int vanaemalisa = 25;

	Button tehas;
	int tehasehind = 1000;
	int tehaselisa = 100;

	MediaPlayer mediaplayer;
	
	Button sulge;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Kurat, kus must leib on!? 0.3");// pealkiri aknal

		Text kuipalju = new Text();
		kuipalju.setText(leib + "");
		kuipalju.setTextAlignment(TextAlignment.CENTER);// tekst keskel

		automaatneLeib = new Button();
		//automaatneLeib.setText("Upgrade, maksab " + upgradehind);
		automaatneLeib.setTooltip(new Tooltip("+1 leib iga 10 sekundi tagant, maksab: " + upgradehind));
		automaatneLeib.setOnAction(e -> {
			if (leib >= upgradehind) {
				leib -= upgradehind;// votab arvelt 10 maha
				upgradehind = upgradehind + upgradehind / 4;// hinnale lisandub
															// juurde 1/4
															// hinnast
				automaatneLeib.setTooltip(new Tooltip("+1 leib iga 10 sekundi tagant, maksab: " + upgradehind));// uuendab upgrade hinda
																			
				kuipalju.setText(leib + "");
				Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), ev -> {// TAIMER

					leib++;
					kuipalju.setText(leib + "");
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
		//yksLisaLeib.setText("+1 leib  " + upgrade2hind);
		yksLisaLeib.setTooltip(new Tooltip("+1 leib klikkimisel, maksab: " + upgrade2hind));
		yksLisaLeib.setOnAction(e -> {
			if (leib >= upgrade2hind) {
				leib -= upgrade2hind;
				upgrade2lisa++;
				upgrade2hind = upgrade2hind + upgrade2hind / 4;
				yksLisaLeib.setTooltip(new Tooltip("+1 leib klikkimisel, maksab: " + upgrade2hind));
				kuipalju.setText(leib + "");
			} else {
				System.out.println("liiga vahe");
				Alert alert = new Alert(AlertType.INFORMATION);// Alert aken 2
				alert.setTitle("Sul on liiga vahe musta leiba!");
				alert.setHeaderText("+1 leib klikkimisel maksab " + upgrade2hind);
				alert.show();
			}
		});
		
		vanaema = new Button();
		
		vanaema.setTooltip(new Tooltip("Vanaema, +25 leiba iga 10s tagant, maksab: " + vanaemahind));
		vanaema.setOnAction(e -> {
			if (leib >= vanaemahind) {
				leib -= vanaemahind;// votab arvelt vanaemahinna maha
				vanaemahind = vanaemahind + vanaemahind / 4;// hinnale lisandub
															// juurde 1/4
															// hinnast
				vanaema.setTooltip(new Tooltip("Vanaema, +25 leiba iga 10s tagant, maksab: " + vanaemahind));
				kuipalju.setText(leib + "");
				Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), ev -> {// TAIMER

					leib += vanaemalisa;
					kuipalju.setText(leib + "");
				}));
				timeline.setCycleCount(Animation.INDEFINITE);
				timeline.play();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);// Alert aken
				alert.setTitle("Sul on liiga vahe musta leiba!");
				alert.setHeaderText("Vanaema maksab " + vanaemahind);
				alert.show();
			}
		});

		tehas = new Button();
		//tehas.setText("Upgrade, maksab " + tehasehind);
		tehas.setTooltip(new Tooltip("Tehas, +100 leiba iga 10s tagant, maksab: " + tehasehind));
		tehas.setOnAction(e -> {
			if (leib >= tehasehind) {
				leib -= tehasehind;// votab arvelt 10 maha
				tehasehind = tehasehind + tehasehind / 4;// hinnale lisandub
															// juurde 1/4
															// hinnast
				tehas.setTooltip(new Tooltip("Tehas, +100 leiba iga 10s tagant, maksab: " + tehasehind));
				kuipalju.setText(leib + "");
				Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), ev -> {// TAIMER

					leib += tehaselisa;
					kuipalju.setText(leib + "");
				}));
				timeline.setCycleCount(Animation.INDEFINITE);
				timeline.play();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);// Alert aken
				alert.setTitle("Sul on liiga vahe musta leiba!");
				alert.setHeaderText("Tehas maksab " + tehasehind);
				alert.show();
			}
		});

		leivanupp = new Button();
		//leivanupp.setText(" ");
		leivanupp.setTooltip(new Tooltip("test"));
		leivanupp.setOnAction(e -> {
			leib = leib + upgrade2lisa;// lisab int upgrade2lisa v6rra musta
										// leiba
			kuipalju.setText(leib + "");// e-> eventhandler lyhend
		});
		
		sulge = new Button("sulge");
		sulge.setOnAction(e -> closeProgram());
		
		

		Pane scene1 = new Pane();
		scene1.getChildren().add(kuipalju);
		kuipalju.setLayoutX(400);
		kuipalju.setLayoutY(55);
		kuipalju.getStyleClass().add("tekst");
		
		scene1.getChildren().add(mustaleiba);
		mustaleiba.getStyleClass().add("tekst");
		mustaleiba.setLayoutX(350);
		mustaleiba.setLayoutY(120);

		scene1.getChildren().add(leivanupp);// toob nupu nahtavale
		leivanupp.setLayoutX(200);
		leivanupp.setLayoutY(200);
		leivanupp.getStyleClass().add("leivanupp");// leivapilt
		leivanupp.setPadding(new Insets(0));

		scene1.getChildren().add(automaatneLeib);
		automaatneLeib.setLayoutX(20);
		automaatneLeib.setLayoutY(10);
		automaatneLeib.setPadding(new Insets(0));
		automaatneLeib.getStyleClass().add("autoleib");

		scene1.getChildren().add(yksLisaLeib);
		yksLisaLeib.setLayoutX(10);
		yksLisaLeib.setLayoutY(110);
		yksLisaLeib.getStyleClass().add("lisaleib");
		;
		scene1.getChildren().add(vanaema);
		vanaema.setLayoutX(10);
		vanaema.setLayoutY(215);
		vanaema.getStyleClass().add("vanaema");

		scene1.getChildren().add(tehas);
		tehas.setLayoutX(10);
		tehas.setLayoutY(325);
		tehas.getStyleClass().add("tehas");
		;
		scene1.getChildren().add(sulge);
		sulge.setLayoutX(745);
		sulge.setLayoutY(550);

		scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(new Scene(scene1, 800, 600));
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
	
	private void closeProgram(){
		
		Platform.exit();
	}

}
