import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Username Label
        Label usernameLabel = new Label("Username:");
        GridPane.setConstraints(usernameLabel, 0, 0);
        gridPane.getChildren().add(usernameLabel);

        // Username Input
        TextField usernameInput = new TextField();
        usernameInput.setPromptText("Enter your username");
        GridPane.setConstraints(usernameInput, 1, 0);
        gridPane.getChildren().add(usernameInput);

        // Password Label
        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 0, 1);
        gridPane.getChildren().add(passwordLabel);

        // Password Input
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Enter your password");
        GridPane.setConstraints(passwordInput, 1, 1);
        gridPane.getChildren().add(passwordInput);

        // Login Button
        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 2);
        gridPane.getChildren().add(loginButton);

        // Status Label
        Label statusLabel = new Label();
        GridPane.setConstraints(statusLabel, 1, 3);
        gridPane.getChildren().add(statusLabel);

        // Login Button Action
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();

            // Simulate authentication (replace with your actual logic)
            if (authenticate(username, password)) {
                statusLabel.setText("Login successful!");
                // Launch GPSApp upon successful login
                CarDashBoard gpsApp = new CarDashBoard();
                gpsApp.start(new Stage());
                primaryStage.close(); // Close the login window
            } else {
                statusLabel.setText("Invalid username or password");
            }
        });

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean authenticate(String username, String password) {
        // Replace with your actual authentication logic
        return username.equals("admin") && password.equals("password");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
