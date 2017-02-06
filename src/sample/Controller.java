package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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
    private TextArea outputTextArea;

    @FXML
    private Button encryptButton;

    @FXML
    private Button decryptButton;

    @FXML
    public void encryptButtonPressed() {

        System.out.println("Encrypt Button Pressed");
        String inputText = inputTextArea.getText();

        try {
            // 1. Turn the desired message into a byte array
            byte[] message = inputText.getBytes("UTF8");

            // 2. Encrypt the desired message
            byte[] secret = CipherUtils.encrypt(Main.publicKey, message);
            System.out.println(new String(secret, "UTF8"));

            // 3. Turn the encrypted message into a character array
            char[] secretChars = new char[secret.length];

            for (int i = 0; i < secret.length; i++) {
                secretChars[i] = (char) secret[i];
            }

            // 4. Store the secret message into a text file
            FileWriter fw = new FileWriter("C:/Users/Miltos/Desktop/secret.txt");
            fw.write(Base64.encodeBase64String(secret));
            fw.close();

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

        try
        {
            // 1. Read the encrypted message
            FileReader fr = new FileReader("C:/Users/Miltos/Desktop/secret.txt");
            BufferedReader br = new BufferedReader(fr);

            // 2. Construct the encrypted message
            int t;
            char c;
            String recoveredSecret = "";
            while ((t = fr.read()) != -1) {
                c = (char) t;
                recoveredSecret += c;
            }

            byte[] recSecret = Base64.decodeBase64(recoveredSecret);

            // 4. Decrypt the message and print it on the user's screen
            byte[] recovered_message = CipherUtils.decrypt(Main.privateKey, recSecret);
            System.out.println(new String(recovered_message, "UTF8"));

            br.close();
            fr.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
