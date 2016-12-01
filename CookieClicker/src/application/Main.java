package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

public class Main extends Application {
	/*"Kurat, kus must leib on!?
	 * Klikkimis m2ng by Andres*/
	
    //Main meetod, launchib kogu v2rgi
	public static void main(String[] args) {
		launch(args);
	}

	public static Button leivaNupp;// SUUR LEIVANUPP
	public static int leib = 0;// Kui palju sul leiba on.
	public static Text mustaLeiba = new Text(leib + "\n" + "leiba");// TEKST,mis n2itab kui palju leiba on

	public static Button automaatneLeib;
	public static int autoLeivaHind = 25;
	public static int autoLeivaLisa = 1;
	public static int mituAutoLeibaOn = 0;
	public static Text naitaMituAutoLeibaOn = new Text(mituAutoLeibaOn + "");
	public static Text naitaPaljuAutoLeibMaksab = new Text("Maksab: " + autoLeivaHind);
	public static boolean automaatneLeib1 = false;

	public static Button plusYksKlikk;
	public static int plusYksKlikkHind = 250;
	public static int plusYksKlikkLisa = 1;//Hiirega klikkides saadav leibade arv!
	public static int mituPlusYksKlikkOn = 0;
	public static Text naitaMituPlusYksKlikkOn = new Text(mituPlusYksKlikkOn + "");
	public static Text naitaPaljuPlusYksKlikkMaksab = new Text("Maksab: " + plusYksKlikkHind);

	public static Button vanaema;
	public static int vanaemaHind = 200;
	public static int vanaemaLisa = 1;
	public static int mituVanaemaOn = 0;
	public static Text naitaMituVanaemaOn = new Text(mituVanaemaOn + "");
	public static Text naitaPaljuVanaemaMaksab = new Text("Maksab: " + vanaemaHind);
	public static boolean vanaema1 = false;

	public static Button tehas;
	public static int tehaseHind = 1000;
	public static int tehaseLisa = 100;
	public static int mituTehastOn = 0;
	public static Text naitaMituTehastOn = new Text(mituTehastOn + "");
	public static Text naitaPaljuTehasMaksab = new Text("Maksab: " + tehaseHind);
	public static boolean tehas1 = false;

	public static MediaPlayer mediaPlayer;
	public static Button reset;
	public static Button sulge;
	public static Timeline leivaTaimer;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// leivaNupp on lihtsalt nupp, millele klikkides tuleb leiba juurde
		leivaNupp = new Button();
		leivaNupp.setTooltip(new Tooltip("Lihtsalt must leib"));// Tooltip n2itab infot objekti kohta, kui hiir selle kohale liigutada
		leivaNupp.setOnAction(e -> {// e-> on kompaktsem versioon EventHandlerist
			lisaleib(plusYksKlikkLisa);// Lisab int plusYksKlikkLisa(tuleb plusYksKlikk upgradest) v6rra leiba.
		});
		
		// automaatneLeib on upgrade, igakord kui see osta, tekib juurde klikker, mis lisab iga 10 sekundi tagant +1 leiva.
		automaatneLeib = new Button();
		automaatneLeib.setTooltip(new Tooltip("+1 leib iga 10 sekundi tagant" + "\n" + "Maksab: " + autoLeivaHind + "\n"
											+ "Sul on: " + mituAutoLeibaOn));// "\n" l2heb tekstiga j2rgmisele reale.
		automaatneLeib.setOnAction(e -> {
			if (leib >= autoLeivaHind) {
				automaatneLeib1 = true;
				leib -= autoLeivaHind;// votab arvelt autoLeivaHind v6rra mahamaha
				autoLeivaHind = autoLeivaHind + autoLeivaHind / 4;// Igakord, kui osta upgrade siis lisandub 25% hinnale
				mituAutoLeibaOn++;// +1
				update();//uuendab leiva arvu igalpool(muidu j22ks igalepoole 0)
				if (automaatneLeib1 = true) {
					taimer(autoLeivaLisa, 10);//taimer(int mitu leiba lisab, int mis ajatagant)
				}
			} else {
				pleier("file:///home/andres/projekt/CookieClicker/bin/application/resources/helid/DING.mp3");//m2ngib automaatselt heli
				Alert alert = new Alert(AlertType.INFORMATION);// Alert aken, kui pole piisavalt leiba
				alert.setTitle("Sul on liiga vahe musta leiba!");
				alert.setHeaderText("+1 leib iga 10 sekundi tagant maksab: " + autoLeivaHind);
				alert.show();
			}
		});
		
		//plusYksKlikk on teine upgrade, igakord kui selle ostad, saad hiirega leivale klikkides yhe leiva rohkem
		plusYksKlikk = new Button();
		plusYksKlikk.setTooltip(new Tooltip(
				"+1 leib klikkimisel" + "\n" + "Maksab: " + plusYksKlikkHind + "\n" + "Sul on: " + mituPlusYksKlikkOn));
		plusYksKlikk.setOnAction(e -> {
			if (leib >= plusYksKlikkHind) {
				leib -= plusYksKlikkHind;
				plusYksKlikkLisa++;//see on int, mis n2itab mitu leiba tuleb klikkides
				plusYksKlikkHind = plusYksKlikkHind + plusYksKlikkHind / 4;
				mituPlusYksKlikkOn++;
				update();
			} else {
				pleier("file:///home/andres/projekt/CookieClicker/bin/application/resources/helid/DING.mp3");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sul on liiga vahe musta leiba!");
				alert.setHeaderText("+1 leib klikkimisel maksab " + plusYksKlikkHind);
				alert.show();
			}
		});

		vanaema = new Button();
		vanaema.setTooltip(new Tooltip("Vanaema, teeb +1 leiba iga 1s tagant" + "\n" + "Maksab: " + vanaemaHind + "\n"
				+ "Sul on: " + naitaMituVanaemaOn));
		vanaema.setOnAction(e -> {
			if (leib >= vanaemaHind) {
				vanaema1 = true;
				leib -= vanaemaHind;
				vanaemaHind = vanaemaHind + vanaemaHind / 4;
				mituVanaemaOn++;
				update();
				if (vanaema1 = true) {
					taimer(vanaemaLisa, 1);
				}
			} else {
				pleier("file:///home/andres/projekt/CookieClicker/bin/application/resources/helid/DING.mp3");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sul on liiga vahe musta leiba!");
				alert.setHeaderText("Vanaema abi maksab " + vanaemaHind);
				alert.show();
			}
		});

		tehas = new Button();
		tehas.setTooltip(new Tooltip("Tehas, toodab +100 leiba iga 10s tagant" + "\n" + "Maksab: " + tehaseHind + "\n"
				+ "Sul on: " + mituTehastOn));
		tehas.setOnAction(e -> {
			if (leib >= tehaseHind) {
				tehas1 = true;
				leib -= tehaseHind;
				tehaseHind = tehaseHind + tehaseHind / 4;
				mituTehastOn++;
				update();
				if (tehas1 = true) {
					taimer(tehaseLisa, 10);
				}
			} else {
				pleier("file:///home/andres/projekt/CookieClicker/bin/application/resources/helid/DING.mp3");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sul on liiga vahe musta leiba!");
				alert.setHeaderText("Tehas maksab " + tehaseHind);
				alert.show();
			}
		});
		//Kindlasti on olemas parem lahendus, kuidas nuppe ja asju aknas paigutada :/
		sulge = new Button(" ");
		sulge.setLayoutX(700);
		sulge.setLayoutY(550);
		sulge.getStyleClass().add("sulgenupp");
		sulge.setOnAction(e -> sulgemine());//kutsub sulgemine();meetodi

		reset = new Button(" ");
		reset.setLayoutX(625);
		reset.setLayoutY(550);
		reset.getStyleClass().add("resetnupp");
		reset.setOnAction(e -> nulli());//kutsub nulli() meetodi

		mustaLeiba.getStyleClass().add("tekst");
		mustaLeiba.setLayoutX(350);
		mustaLeiba.setLayoutY(120);

		leivaNupp.setLayoutX(220);
		leivaNupp.setLayoutY(200);
		leivaNupp.getStyleClass().add("leivanupp");

		automaatneLeib.setLayoutX(10);//nupu paigutus
		automaatneLeib.setLayoutY(30);
		automaatneLeib.getStyleClass().add("autoleib");
		naitaMituAutoLeibaOn.setLayoutX(110);//upgrade arvu paigutus
		naitaMituAutoLeibaOn.setLayoutY(100);
		naitaMituAutoLeibaOn.getStyleClass().add("tekst");
		naitaPaljuAutoLeibMaksab.setLayoutX(10);//upgrade maksumuse paigutus
		naitaPaljuAutoLeibMaksab.setLayoutY(30);
		naitaPaljuAutoLeibMaksab.getStyleClass().add("tekst2");

		plusYksKlikk.setLayoutX(10);
		plusYksKlikk.setLayoutY(160);
		plusYksKlikk.getStyleClass().add("lisaleib");
		naitaMituPlusYksKlikkOn.setLayoutX(110);
		naitaMituPlusYksKlikkOn.setLayoutY(230);
		naitaMituPlusYksKlikkOn.getStyleClass().add("tekst");
		naitaPaljuPlusYksKlikkMaksab.setLayoutX(10);
		naitaPaljuPlusYksKlikkMaksab.setLayoutY(160);
		naitaPaljuPlusYksKlikkMaksab.getStyleClass().add("tekst2");

		vanaema.setLayoutX(10);
		vanaema.setLayoutY(290);
		vanaema.getStyleClass().add("vanaema");
		naitaMituVanaemaOn.setLayoutX(110);
		naitaMituVanaemaOn.setLayoutY(360);
		naitaMituVanaemaOn.getStyleClass().add("tekst");
		naitaPaljuVanaemaMaksab.setLayoutX(10);
		naitaPaljuVanaemaMaksab.setLayoutY(290);
		naitaPaljuVanaemaMaksab.getStyleClass().add("tekst2");

		tehas.setLayoutX(10);
		tehas.setLayoutY(420);
		tehas.getStyleClass().add("tehas");
		naitaMituTehastOn.setLayoutX(110);
		naitaMituTehastOn.setLayoutY(490);
		naitaMituTehastOn.getStyleClass().add("tekst");
		naitaPaljuTehasMaksab.setLayoutX(10);
		naitaPaljuTehasMaksab.setLayoutY(420);
		naitaPaljuTehasMaksab.getStyleClass().add("tekst2");

		primaryStage.setTitle("Kurat, kus must leib on!? 0.4 by Andres");// Pealkiri aknal
		primaryStage.setOnCloseRequest(e -> {// mis juhtub kui "X" vajutad
			e.consume();// votab systeemilt yle(muidu l2heks "cancel" vajutamisel ikkagi kinni
			sulgemine();
		});
		Pane scene1 = new Pane();
		primaryStage.setScene(new Scene(scene1, 800, 600));
		scene1.getChildren().addAll(mustaLeiba, leivaNupp, automaatneLeib, plusYksKlikk, vanaema, tehas, sulge,
				naitaMituAutoLeibaOn, naitaMituPlusYksKlikkOn, naitaMituVanaemaOn, naitaMituTehastOn, reset,
				naitaPaljuAutoLeibMaksab, naitaPaljuPlusYksKlikkMaksab, naitaPaljuTehasMaksab, naitaPaljuVanaemaMaksab);// teeb n2htavaks
																												
		scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setResizable(false);// akna suurust ei saa muuta
		primaryStage.show();

		Alert info = new Alert(AlertType.INFORMATION);// Alert aken programmi avamisel
		info.setTitle("Kurat, kus must leib on!? by Andres");
		info.setHeaderText("Absoluutselt k6ik leivatootjad Eestis pandi enne j6ule ja aastavahetust ootamatult" + "\n" + // best story evr
				"kohutavate kuritegude eest vangi ja nende tehased lammutati maha." + "\n"
				+ "Reaalselt mitte keegi ei oska nutiajastul ise leiba teha, aga 6nneks on sinu k2tte" + "\n"
				+ "sattunud ainus k6ikv6imas leivatootmis programm" + "\n"
				+ "Asu t88le ja aita must leib k6igi toidulauale!");
		
		pleier("file:///home/andres/projekt/CookieClicker/bin/application/resources/helid/DING.mp3");
		pleier("file:///home/andres/projekt/CookieClicker/bin/application/resources/helid/kusmustleibon.mp3");
		info.showAndWait();//ootab kuni nuppu vajutatakse
		pleier("file:///home/andres/projekt/CookieClicker/bin/application/resources/helid/kummipart.mp3");

	}//end of start

	// Taimer
	public static void taimer(int kuiPaljuLisab, int aeg) {
		leivaTaimer = new Timeline(new KeyFrame(Duration.seconds(aeg), ev -> {
			lisaleib(kuiPaljuLisab);
		}));
		leivaTaimer.setCycleCount(Animation.INDEFINITE);
		leivaTaimer.play();
	}

	// M2ngib helisid
	public static void pleier(String helifail) {
		Media heli = new Media(helifail);
		mediaPlayer = new MediaPlayer(heli);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setVolume(0.1);
	}

	// lisab leiba juurde
	public static void lisaleib(double kuiPalju) {
		leib += kuiPalju;
		update();
	}

	// uuendab leiva arvu
	public static void update() {
		mustaLeiba.setText(leib + "\n" + "leiba");

		naitaMituAutoLeibaOn.setText(mituAutoLeibaOn + "");
		automaatneLeib.setTooltip(new Tooltip("+1 leib iga 10 sekundi tagant" + "\n" + "Maksab: " + autoLeivaHind + "\n"
				+ "Sul on: " + mituAutoLeibaOn));
		naitaPaljuAutoLeibMaksab.setText("Maksab: " + autoLeivaHind);

		naitaMituPlusYksKlikkOn.setText(mituPlusYksKlikkOn + "");
		plusYksKlikk.setTooltip(new Tooltip(
				"+1 leib klikkimisel" + "\n" + "Maksab: " + plusYksKlikkHind + "\n" + "Sul on: " + mituPlusYksKlikkOn));
		naitaPaljuPlusYksKlikkMaksab.setText("Maksab: " + plusYksKlikkHind);

		naitaMituVanaemaOn.setText(mituVanaemaOn + "");
		vanaema.setTooltip(new Tooltip("Vanaema, teeb +1 leiba iga 1s tagant" + "\n" + "Maksab: " + vanaemaHind + "\n"
				+ "Sul on: " + mituVanaemaOn));
		naitaPaljuVanaemaMaksab.setText("Maksab: " + vanaemaHind);

		naitaMituTehastOn.setText(mituTehastOn + "");
		tehas.setTooltip(new Tooltip("Tehas, toodab +100 leiba iga 10s tagant" + "\n" + "Maksab: " + tehaseHind + "\n"
				+ "Sul on: " + mituTehastOn));
		naitaPaljuTehasMaksab.setText("Maksab: " + tehaseHind);
	}

	// nullib skoori
	private void nulli() {
		pleier("file:///home/andres/projekt/CookieClicker/bin/application/resources/helid/kummipart.mp3");
		Alert alert = new Alert(AlertType.CONFIRMATION, "Oled kindel, et soovid skoori nullida?", ButtonType.YES,
				ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			if (automaatneLeib1 || vanaema1 || tehas1) {//vaatab kas booleanid = true
				leivaTaimer.stop();// leivataimer kinni esimesena, muidu bad bad error
			}

			leib = 0;

			autoLeivaHind = 25;
			autoLeivaLisa = 1;
			mituAutoLeibaOn = 0;
			automaatneLeib1 = false;

			plusYksKlikkHind = 250;
			plusYksKlikkLisa = 1;
			mituPlusYksKlikkOn = 0;

			vanaemaHind = 200;
			vanaemaLisa = 1;
			mituVanaemaOn = 0;
			vanaema1 = false;

			tehaseHind = 1000;
			tehaseLisa = 100;
			mituTehastOn = 0;
			tehas1 = false;

			update();
		}
	}

	// Programmi sulgemisel confirmation aken
	private void sulgemine() {
		pleier("file:///home/andres/projekt/CookieClicker/bin/application/resources/helid/kummipart.mp3");
		Alert alert = new Alert(AlertType.CONFIRMATION, "Sulgen programmi?", ButtonType.YES, ButtonType.NO,
				ButtonType.CANCEL);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			Platform.exit();
		}
	}

}//theend
