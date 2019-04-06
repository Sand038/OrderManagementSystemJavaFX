package sand.controller;

import java.util.ResourceBundle;

import java.net.URL;

import herudi.animations.FadeInLeftTransition;
import herudi.animations.FadeInRightTransition;
import herudi.config.Config;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

/**
 * @author SFernando
 */
public class LoginController implements Initializable
{
  @FXML
  private TextField txtUsername;

  @FXML
  private PasswordField txtPassword;

  @FXML
  private Text lblWelcome;

  @FXML
  private Text lblUserLogin;

  @FXML
  private Text lblUsername;

  @FXML
  private Text lblPassword;

  @FXML
  private Button btnLogin;

  @FXML
  private Label lblClose;

  /**
   * Initializes the controller class.
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb)
  {
    Platform.runLater(() -> {
      new FadeInRightTransition(lblUserLogin).play();
      new FadeInLeftTransition(lblWelcome).play();
      new FadeInLeftTransition(lblPassword).play();
      new FadeInLeftTransition(lblUsername).play();
      new FadeInLeftTransition(txtUsername).play();
      new FadeInLeftTransition(txtPassword).play();
      new FadeInRightTransition(btnLogin).play();
      lblClose.setOnMouseClicked((MouseEvent event) -> {
        Platform.exit();
        System.exit(0);
      });
    });
  }

  @FXML
  private void aksiLogin(ActionEvent event)
  {
    if (txtUsername.getText().equals("test") && txtPassword.getText().equals("test"))
    {
      Config c = new Config();
      c.newStage(lblClose, "/sand/view/Main.fxml", "Order management system", true, StageStyle.UNDECORATED, false);
    }
    else
    {
      Config.dialog(Alert.AlertType.ERROR, "Error login, Please check username and password");
    }
  }

}
