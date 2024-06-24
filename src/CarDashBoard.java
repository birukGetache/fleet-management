import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class CarDashBoard extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Creating a list of items
        ListView<String> listView = new ListView<>();
        IntStream.rangeClosed(1, 30).forEach(i -> listView.getItems().add("Item " + i));
        listView.setPrefWidth(150); // Fixed width for the ListView

        // Creating VBox to hold the ListView
        VBox sidebar = new VBox(listView);
        sidebar.setPadding(new Insets(10)); // Optional: Add padding for better appearance

        // Setting VBox to take up full height
        sidebar.prefHeightProperty().bind(primaryStage.heightProperty());

        // Bind ListView height to VBox height
        listView.prefHeightProperty().bind(sidebar.heightProperty());

        // Creating a BorderPane for the main layout
        BorderPane root = new BorderPane();
        root.setLeft(sidebar);

        // URL of the image to load
        String imageUrl = "https://images.pexels.com/photos/3680219/pexels-photo-3680219.jpeg?auto=compress&cs=tinysrgb&h=627&fit=crop&w=1200";

        // Asynchronously load the image from the URL
        Image image = new Image(imageUrl, true);
        image.errorProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                System.err.println("Failed to load image from URL: " + imageUrl);
            }
        });

        // Create an ImageView for the loaded image
        ImageView icon = new ImageView(image);
        icon.setFitWidth(300); // Set the width to match the scene width
        icon.setPreserveRatio(true); // Preserve aspect ratio

        HBox topBox = new HBox(icon);
        topBox.setPadding(new Insets(10));

        // Adding the top box to the BorderPane and setting it to the top
        root.setTop(topBox);
        BorderPane.setAlignment(topBox, javafx.geometry.Pos.CENTER); // Align center
        BorderPane.setMargin(topBox, new Insets(10, 10, 0, 10)); // Set margins

        // Label to display clicked item at the top left
        Label selectedItemLabel = new Label();
        selectedItemLabel.setPadding(new Insets(10)); // Padding for the label
        root.setCenter(selectedItemLabel);

        // Event handler for ListView item clicks
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedItemLabel.setText("Selected Item: " + newValue);
            }
        });

        // Creating a scene
        Scene scene = new Scene(root, 300, 400); // Set overall scene size

        // Setting the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dashboard");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
