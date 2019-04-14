package sand.controller;

import java.util.ResourceBundle;

import java.net.URL;

import herudi.config.Config;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author SFernando
 */
public class MenuController implements Initializable
{

  private static final String DECORATION_BUTTON_RESTORE = "decoration-button-restore";

  private Config con = new Config();

  private Stage stage;

  private Rectangle2D rec2;

  private Double width;

  private Double height;

  @FXML
  private Button maximize;

  @FXML
  private Button minimize;

  @FXML
  private Button resize;

  @FXML
  private Button fullscreen;

  @FXML
  private ListView<String> listMenu;

  @FXML
  private AnchorPane paneData;

  @FXML
  private Button btnLogout;

  /**
   * Initializes the controller class.
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb)
  {
    rec2 = Screen.getPrimary().getVisualBounds();
    width = 0.1;
    height = 0.1;
    listMenu.getItems().addAll("  Orders", "  Clinics", "  Invoices");
    Platform.runLater(() -> {
      stage = (Stage) maximize.getScene().getWindow();
      maximize.getStyleClass().add(DECORATION_BUTTON_RESTORE);
      resize.setVisible(false);
      listMenu.getSelectionModel().select(0);
      con.loadAnchorPane(paneData, "/sand/view/Order.fxml");
      listMenu.requestFocus();
    });
  }

  @FXML
  private void maximize()
  {
    stage = (Stage) maximize.getScene().getWindow();
    if (stage.isMaximized())
    {
      if (width == rec2.getWidth() && height == rec2.getHeight())
      {
        stage.setMaximized(false);
        stage.setHeight(600);
        stage.setWidth(800);
        stage.centerOnScreen();
        maximize.getStyleClass().remove(DECORATION_BUTTON_RESTORE);
        resize.setVisible(true);
      }
      else
      {
        stage.setMaximized(false);
        maximize.getStyleClass().remove(DECORATION_BUTTON_RESTORE);
        resize.setVisible(true);
      }

    }
    else
    {
      stage.setMaximized(true);
      stage.setHeight(rec2.getHeight());
      maximize.getStyleClass().add(DECORATION_BUTTON_RESTORE);
      resize.setVisible(false);
    }
  }

  @FXML
  private void minimize()
  {
    stage = (Stage) minimize.getScene().getWindow();
    if (stage.isMaximized())
    {
      width = rec2.getWidth();
      height = rec2.getHeight();
      stage.setMaximized(false);
      stage.setHeight(height);
      stage.setWidth(width);
      stage.centerOnScreen();
      Platform.runLater(() -> {
        stage.setIconified(true);
      });
    }
    else
    {
      stage.setIconified(true);
    }
  }

  @FXML
  private void resize()
  {
    //TODO
  }

  @FXML
  private void fullscreen()
  {
    stage = (Stage) fullscreen.getScene().getWindow();
    if (stage.isFullScreen())
    {
      stage.setFullScreen(false);
    }
    else
    {
      stage.setFullScreen(true);
    }
  }

  @FXML
  private void close()
  {
    Platform.exit();
    System.exit(0);
  }

  @FXML
  private void clickListMenu()
  {
    switch (listMenu.getSelectionModel().getSelectedIndex())
    {
      case 0:
        con.loadAnchorPane(paneData, "/sand/view/Order.fxml");
        break;
      case 1:
        //                con.loadAnchorPane(paneData, "product.fxml");
        break;
      case 2:
        //                con.loadAnchorPane(paneData, "micro.fxml");
        break;
    }
  }

  @FXML
  private void logout()
  {
    Config config = new Config();
    config.newStage(btnLogout, "/sand/view/Login.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
  }

}
