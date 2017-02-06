package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Main extends Application {

    static PublicKey publicKey;
    static PrivateKey privateKey;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Load the public and private keys
        String workingDir = System.getProperty("user.dir");
        System.out.println(workingDir);

        publicKey = CipherUtils.readPublicKey("public.der");
        privateKey = CipherUtils.readPrivateKey("private.der");

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("RSA Encryptor");
        primaryStage.setScene(new Scene(root, 700, 250));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
