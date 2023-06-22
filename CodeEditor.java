import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CodeEditor extends Application {

    private static final String[] SUPPORTED_EXTENSIONS = { ".c", ".cdart", ".java", ".html", ".js" };

    private TextArea codeArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        codeArea = new TextArea();
        codeArea.setPrefRowCount(20);
        codeArea.textProperty().addListener((observable, oldValue, newValue) -> {
            // Perform actions when code changes (e.g., syntax highlighting, linting, etc.)
            // You can customize this based on the specific language being edited
        });

        codeArea.setStyle("-fx-font-family: 'JetBrains Mono'; -fx-control-inner-background: black; -fx-text-fill: white;");

        root.setCenter(codeArea);

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-radius: 20px;");
        saveButton.setOnAction(e -> saveFile());

        Button openButton = new Button("Open");
        openButton.setStyle("-fx-background-radius: 20px;");
        openButton.setOnAction(e -> openFile());

        Button aboutButton = new Button("About Us");
        aboutButton.setStyle("-fx-background-radius: 20px;");
        aboutButton.setOnAction(e -> openAboutPage());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(saveButton, openButton, aboutButton);
        buttonBox.setStyle("-fx-background-color: #f5f5f5; -fx-padding: 10px; -fx-border-radius: 10px; -fx-border-color: #d3d3d3;");

        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Code Editor");
        primaryStage.show();
    }

    private void saveFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(codeArea.getScene().getWindow());
        if (file != null) {
            try {
                Files.write(Paths.get(file.getAbsolutePath()), codeArea.getText().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(codeArea.getScene().getWindow());
        if (file != null) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
                codeArea.setText(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openAboutPage() {
        TextArea aboutArea = new TextArea();
        aboutArea.setEditable(false);
        aboutArea.setText("This program was made by Aayush Sharma in Java.\n\nRemotikal Inc.");
        aboutArea.setPrefRowCount(5);
        aboutArea.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");

        Stage aboutStage = new Stage();
        aboutStage.setTitle("About Us");
        aboutStage.setScene(new Scene(aboutArea, 300, 150));
        aboutStage.show();
    }
}
