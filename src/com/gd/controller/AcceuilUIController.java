package com.gd.controller;



import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class AcceuilUIController {

	@FXML
	public Label lblLoading;

	@FXML
	public Label lblLoadingA;

	@FXML
	public ProgressBar progressBar;

	@FXML
	private void initialize() {

		AnimationTimer programButtonAnimation = new AnimationTimer() {

			private long lastUpdate = 100;

			@Override
			public void handle(long now) {
				if (now - lastUpdate >= 28_000_000) {
					Bar();
					lastUpdate = now;
				}

			}
		};
		programButtonAnimation.start();
	}

	public Label loadvalue() {
		return lblLoadingA;
	}

	public Label loadText() {
		return lblLoading;
	}

	@SuppressWarnings("static-access")
	public void Bar() {

		new Thread(() -> {
			
			
			 try {
					Thread.sleep(100);

			
				
				// int position = +i;
					Platform.runLater(() -> {
						
						for (int position = 0; position <= 100; position++) {
						lblLoadingA.setText(position + "%");

						if (position == 10) {
							lblLoading.setText("Turning 0n Module ...");
						}
						if (position == 20) {
							lblLoading.setText("Loading Module ...");
						}
						if (position == 50) {
							lblLoading.setText("Connecting To Database ...");
						}
						if (position == 60) {
							lblLoading.setText("Launching Application ...");
						}
						if (position == 80) {
							lblLoading.setText("Connection succes ...");
						}
						if (position == 100) {
							
							
						}

						progressBar.setProgress(position);
						System.out.println("Index: " + position);
						
					}
					});
				
		
				
		
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}).start();
		
		

	}

}
