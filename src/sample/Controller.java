package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Controller {

    @FXML
    private TextArea inputTextArea;

    @FXML
    private Button encryptButton;

    @FXML
    private Button decryptButton;

    @FXML
    private RadioButton small;

    @FXML
    private RadioButton medium;

    @FXML
    private RadioButton large;

    @FXML
    private ToggleGroup fontMenu;


    @FXML
    public void encryptButtonPressed() {

        System.out.println("Encrypt Button Pressed");

        // 0. Retrieve the desired message
        String inputText = inputTextArea.getText();

        try {
            // 1. Turn the desired message into a byte array
            byte[] message = inputText.getBytes("UTF8");

            // 2. Encrypt the desired message
            byte[] secret = CipherUtils.encrypt(Main.publicKey, message);

            // 3. Print the encrypted message to the console
            inputTextArea.setText(Base64.encodeBase64String(secret));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchPaddingException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        } catch (InvalidKeyException e) {
            System.out.println(e.getMessage());
        } catch (IllegalBlockSizeException e) {
            System.out.println(e.getMessage());
        } catch (BadPaddingException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void decryptButtonPressed() {

        System.out.println("Decrypt Button Pressed");

        try {
            // 1. Read the encrypted message
            String recoveredSecret = inputTextArea.getText();

            // 2. Turn the message into a byte array
            byte[] recSecret = Base64.decodeBase64(recoveredSecret);

            // 3. Decrypt the message and print it on the user's screen
            byte[] recovered_message = CipherUtils.decrypt(Main.privateKey, recSecret);

            // 4. Print the decrypted message to the console
            inputTextArea.setText(new String(recovered_message, "UTF8"));

        } catch (IOException e) {
            System.out.println(e);
        } catch (NoSuchPaddingException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        } catch (InvalidKeyException e) {
            System.out.println(e.getMessage());
        } catch (IllegalBlockSizeException e) {
            System.out.println(e.getMessage());
        } catch (BadPaddingException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void onSizeChecked() {

        // Get the user's choice
        Toggle toggle = fontMenu.getSelectedToggle();

        // Check the user's choice and open the desired scene
        if(toggle == small){
            inputTextArea.setStyle("-fx-font-size:14");
        } else if(toggle == medium) {
            inputTextArea.setStyle("-fx-font-size:18");
        } else if(toggle == large) {
            inputTextArea.setStyle("-fx-font-size:24");
        } else {
            System.out.println("Something went wrong!");
        }
    }
}
