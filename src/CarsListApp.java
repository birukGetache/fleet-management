import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CarsListApp extends Application {

    private List<Car> cars = new ArrayList<>();
    private ListView<Car> listView = new ListView<>();
    private VBox carDetails = new VBox(10);

    @Override
    public void start(Stage primaryStage) {
        // Initialize some sample cars
        cars.add(new Car("Toyota Camry", "A comfortable sedan", "https://example.com/toyota_camry.jpg"));
        cars.add(new Car("Honda Civic", "Efficient and reliable", "https://example.com/honda_civic.jpg"));
        cars.add(new Car("BMW X5", "Luxurious SUV", "https://example.com/bmw_x5.jpg"));

        BorderPane root = new BorderPane();
        carDetails.setPadding(new Insets(10));

        Label titleLabel = new Label("Cars List");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        listView.getItems().addAll(cars);
        listView.setPrefWidth(200);
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showCarDetails(newValue));

        VBox buttonsBox = new VBox(10);
        buttonsBox.setPadding(new Insets(10));
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");

        editButton.setOnAction(event -> {
            Car selectedCar = listView.getSelectionModel().getSelectedItem();
            if (selectedCar != null) {
                // Implement edit functionality
                System.out.println("Editing: " + selectedCar.getName());
            }
        });

        deleteButton.setOnAction(event -> {
            Car selectedCar = listView.getSelectionModel().getSelectedItem();
            if (selectedCar != null) {
                cars.remove(selectedCar);
                listView.getItems().remove(selectedCar);
                clearCarDetails();
                System.out.println("Deleting: " + selectedCar.getName());
            }
        });

        buttonsBox.getChildren().addAll(editButton, deleteButton);

        HBox carBox = new HBox(10);
        carBox.getChildren().addAll(listView, carDetails);

        root.setTop(titleLabel);
        root.setCenter(carBox);
        root.setRight(buttonsBox);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Cars List Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showCarDetails(Car car) {
        carDetails.getChildren().clear();
        if (car != null) {
            ImageView imageView = new ImageView(new Image(car.getImageUrl()));
            imageView.setFitWidth(200);
            imageView.setFitHeight(150);
            Label nameLabel = new Label("Name: " + car.getName());
            Label descriptionLabel = new Label("Description: " + car.getDescription());
            carDetails.getChildren().addAll(imageView, nameLabel, descriptionLabel);
        }
    }

    private void clearCarDetails() {
        carDetails.getChildren().clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
