package sand.controller;

import java.util.ResourceBundle;

import java.net.URL;

import herudi.config.Config;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author SFernando
 */
public class MenuController implements Initializable
{
  @FXML
  private Button close;

  @FXML
  private Button maximize;

  @FXML
  private Button minimize;

  @FXML
  private Button resize;

  @FXML
  private Button fullscreen;

  @FXML
  private Label title;

  Stage stage;

  Rectangle2D rec2;

  Double w, h;

  @FXML
  private ListView<String> listMenu;

  @FXML
  private AnchorPane paneData;

  Config con = new Config();

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
    w = 0.1;
    h = 0.1;
    listMenu.getItems().addAll("  Orders", "  Clinics", "  Invoices");
    Platform.runLater(() -> {
      stage = (Stage) maximize.getScene().getWindow();
      maximize.getStyleClass().add("decoration-button-restore");
      resize.setVisible(false);
      listMenu.getSelectionModel().select(0);
      con.loadAnchorPane(paneData, "/sand/view/Order.fxml");
      listMenu.requestFocus();
    });
  }

  @FXML
  private void aksiMaximized(ActionEvent event)
  {
    stage = (Stage) maximize.getScene().getWindow();
    if (stage.isMaximized())
    {
      if (w == rec2.getWidth() && h == rec2.getHeight())
      {
        stage.setMaximized(false);
        stage.setHeight(600);
        stage.setWidth(800);
        stage.centerOnScreen();
        maximize.getStyleClass().remove("decoration-button-restore");
        resize.setVisible(true);
      }
      else
      {
        stage.setMaximized(false);
        maximize.getStyleClass().remove("decoration-button-restore");
        resize.setVisible(true);
      }

    }
    else
    {
      stage.setMaximized(true);
      stage.setHeight(rec2.getHeight());
      maximize.getStyleClass().add("decoration-button-restore");
      resize.setVisible(false);
    }
  }

  @FXML
  private void aksiminimize(ActionEvent event)
  {
    stage = (Stage) minimize.getScene().getWindow();
    if (stage.isMaximized())
    {
      w = rec2.getWidth();
      h = rec2.getHeight();
      stage.setMaximized(false);
      stage.setHeight(h);
      stage.setWidth(w);
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
  private void aksiResize(ActionEvent event)
  {
  }

  @FXML
  private void aksifullscreen(ActionEvent event)
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
  private void aksiClose(ActionEvent event)
  {
    Platform.exit();
    System.exit(0);
  }

  @FXML
  private void aksiKlikListMenu(MouseEvent event)
  {
    switch (listMenu.getSelectionModel().getSelectedIndex())
    {
      case 0:
      {
        con.loadAnchorPane(paneData, "/sand/view/Order.fxml");
      }
      break;
      case 1:
      {
        //                con.loadAnchorPane(paneData, "product.fxml");
      }
      break;
      case 2:
      {
        //                con.loadAnchorPane(paneData, "micro.fxml");
      }
      break;
    }
  }

  @FXML
  private void aksiLogout(ActionEvent event)
  {
    Config config = new Config();
    config.newStage(btnLogout, "/sand/view/Login.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
  }

}
