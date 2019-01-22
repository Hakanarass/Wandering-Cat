
/* Hakan ARAS, 15 December 2017 */
/* Wandering Cat */
/* Simulate a randomly walking cat in a 2D discrete world. */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Hakan_ARAS extends Application {
	Pane pane = new Pane();

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) throws IOException {

		java.io.File file = new java.io.File("world.txt");
		File file1 = new File("world.txt");
		byte[] bytes = new byte[(int) file1.length()];
		FileInputStream fis = new FileInputStream(file1);
		fis.read(bytes);
		fis.close();

		if (!file.exists()) { // if file is not found, report and exit program
			System.out.println("File can not be found! Exiting program...");
			System.exit(1);
		}

		Scanner inputCounter = new Scanner(file); // Scanner for counter of Row
		int numberOfRow = 0;

		while (inputCounter.hasNext()) { // Number of row
			String[] tempNumbers = inputCounter.nextLine().trim().split(" ");
			numberOfRow++;
		}
		inputCounter.close();

		String[] numberOfData = new String(bytes).trim().split("\\s+"); // Number of data
		int numberOfCol = 0;
		numberOfCol = numberOfData.length / numberOfRow; // Find number of column

		int sceneWidth = numberOfCol * 10;
		int sceneHeight = numberOfRow * 10;
		// Program displays dimensional screen based on row and column; moreover, this
		// program allows to create screen dimension based on the input.txt data.
		// Therefore, when you change input.txt name, you do not have to change the
		// screen size.

		Cell[][] lineCell = new Cell[numberOfRow][numberOfCol]; // Multidimensional array of Cell objects

		int[] tall = new int[numberOfData.length];
		for (int i = 0; i < numberOfData.length; i++) { // take all data from input.txt
			tall[i] = Integer.parseInt(numberOfData[i]);
		}

		int i = 0;
		for (int u = 0; u < numberOfRow; u++) { // Create multidimensional array of Cell Objects
			for (int m = 0; m < numberOfCol; m++) {
				lineCell[u][m] = new Cell();
				lineCell[u][m].type = tall[i];
				i++;
			}
		}

		Cat juniurCat = new Cat(Color.RED);
		Cell juniurCatCell = new Cell();
		juniurCat.row = 13; // Start row
		juniurCat.col = 9; // Start col

		for (int h = 0; h < numberOfRow; h++) { // Draw rectangle
			for (int k = 0; k < numberOfCol; k++) {
				Rectangle blueRectangle = new Rectangle(k * 10, 10 * h, 10, 10); // Create blue rectangles

				if (lineCell[h][k].type == 1) {
					blueRectangle.setFill(juniurCatCell.cellColor.CORNFLOWERBLUE);
					blueRectangle.setStroke(Color.BLACK);
					blueRectangle.setStrokeWidth(2);
				} else {
					blueRectangle.setFill(juniurCatCell.cellColor.WHITE);
					blueRectangle.setStroke(Color.BLACK);
					blueRectangle.setStrokeWidth(2);
				}
				pane.getChildren().addAll(blueRectangle);
			}
		}

		int steps = 0; // number of steps
		drawCircles(juniurCat.col, juniurCat.row);

		for (steps = 1; steps <= 250000; steps++) {

			Random randomGenerator = new Random();
			int number = 1 + randomGenerator.nextInt(8); // Random between 1-8

			// the cat can randomly pick a direction among eight directions: 1=north-east,
			// 2=north, 3=north-west, 4=west, 5=south-west,6=south, 7=south-east, 8=east,
			// and then move one cell in that direction.
			if (number == 1 && (juniurCat.row) - (1) >= 0 && (juniurCat.col) + (1) >= 0 && (juniurCat.row) - (1) <= 49
					&& (juniurCat.col) + (1) <= 49) {

				if (lineCell[juniurCat.row - 1][juniurCat.col + 1].type == 0) {
					juniurCat.row = juniurCat.row - 1;
					juniurCat.col = juniurCat.col + 1;
					drawCircles(juniurCat.col, juniurCat.row);
				}
			} else if (number == 2 && (juniurCat.row) - (1) >= 0 && juniurCat.col >= 0 && (juniurCat.row) - (1) <= 49
					&& juniurCat.col <= 49) {
				if (lineCell[juniurCat.row - 1][juniurCat.col].type == 0) {
					juniurCat.row = juniurCat.row - 1;
					drawCircles(juniurCat.col, juniurCat.row);
				}
			} else if (number == 3 && (juniurCat.row) - (1) >= 0 && (juniurCat.col) - (1) >= 0
					&& (juniurCat.row) - (1) <= 49 && (juniurCat.col) - (1) <= 49) {

				if (lineCell[juniurCat.row - 1][juniurCat.col - 1].type == 0) {
					juniurCat.row = juniurCat.row - 1;
					juniurCat.col = juniurCat.col - 1;
					drawCircles(juniurCat.col, juniurCat.row);
				}
			} else if (number == 4 && juniurCat.row >= 0 && (juniurCat.col) - (1) >= 0 && juniurCat.row <= 49
					&& (juniurCat.col) - (1) <= 49) {
				if (lineCell[juniurCat.row][juniurCat.col - 1].type == 0) {
					juniurCat.col = juniurCat.col - 1;
					drawCircles(juniurCat.col, juniurCat.row);
				}
			} else if (number == 5 && (juniurCat.row) + (1) >= 0 && (juniurCat.col) - (1) >= 0
					&& (juniurCat.row) + (1) <= 49 && (juniurCat.col) - (1) <= 49) {
				if (lineCell[juniurCat.row + 1][juniurCat.col - 1].type == 0) {
					juniurCat.row = juniurCat.row + 1;
					juniurCat.col = juniurCat.col - 1;
					drawCircles(juniurCat.col, juniurCat.row);
				}
			} else if (number == 6 && juniurCat.row + 1 >= 0 && juniurCat.col >= 0 && (juniurCat.row) + (1) <= 49
					&& juniurCat.col <= 49) {
				if (lineCell[juniurCat.row + 1][juniurCat.col].type == 0) {
					juniurCat.row = juniurCat.row + 1;
					drawCircles(juniurCat.col, juniurCat.row);
				}
			} else if (number == 7 && juniurCat.row + 1 >= 0 && (juniurCat.col) + (1) >= 0
					&& (juniurCat.row) + (1) <= 49 && (juniurCat.col) + (1) <= 49) {
				if (lineCell[juniurCat.row + 1][juniurCat.col + 1].type == 0) {
					juniurCat.row = juniurCat.row + 1;
					juniurCat.col = juniurCat.col + 1;
					drawCircles(juniurCat.col, juniurCat.row);
				}
			} else if (number == 8 && juniurCat.row >= 0 && (juniurCat.col) + (1) >= 0 && juniurCat.row <= 49
					&& (juniurCat.col) + (1) <= 49) {
				if (lineCell[juniurCat.row][juniurCat.col + 1].type == 0) {
					juniurCat.col = juniurCat.col + 1;
					drawCircles(juniurCat.col, juniurCat.row);
				}
			}
		}

		Scene scene = new Scene(pane, sceneWidth, sceneHeight);
		primaryStage.setTitle("JavaFX Wandering Cat Application"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support. Not
	 * needed for running from the command line.
	 */
	public static void main(String[] args) { // main
		launch(args);
	}

	public void drawCircles(int x, int y) { // draw Circles
		Circle catCircles = new Circle(); // Circles
		catCircles.setCenterX((x * 10) + 5);
		catCircles.setCenterY((y * 10) + 5);
		catCircles.setRadius(2);
		catCircles.setFill(Color.RED);
		catCircles.setStroke(Color.RED);
		catCircles.setStrokeWidth(1);
		pane.getChildren().addAll(catCircles);
	}
}
