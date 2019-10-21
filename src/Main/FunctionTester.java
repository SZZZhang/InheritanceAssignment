package Main;

import Functions.Arc;
import Functions.Cubic;
import Functions.Logarithm;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.stage.Stage;

/**
 * Example of using the Canvas and GraphicsContext class within JavaFX
 *
 */

public class FunctionTester extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Drawing Functions Test");
		Group root = new Group();
		Canvas canvas = new Canvas(500, 500);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Cubic cubic = new Cubic(0.35, 0.25, -0.5, -1, 2);
		Cubic c2 = new Cubic(1 ,0,0,0,0);
		Arc arc = new Arc(4, 0, -2);
		Logarithm log = new Logarithm(-1, 2, 0);
		//cubic.draw(canvas);
		log.draw(canvas);
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

	}
}