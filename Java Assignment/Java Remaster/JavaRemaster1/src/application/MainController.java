package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button loginBtn;

    @FXML
    private Button registerBtn;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void loginForm(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void registerForm(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RegisterForm.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public 
}
