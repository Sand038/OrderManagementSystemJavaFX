package sand.controller;

import java.util.ResourceBundle;

import java.net.URL;

import herudi.animations.FadeInLeftTransition;
import herudi.animations.FadeInTransition;
import herudi.config.Config;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author SFernando
 */
public class SplashController implements Initializable
{
  @FXML
  private Text lblWelcome;

  @FXML
  private Text lblRudy;

  @FXML
  private VBox vboxBottom;

  @FXML
  private Label lblClose;

  private Stage stage;

  @FXML
  private ImageView imgLoading;

  /**
   * Initializes the controller class.
   *
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb)
  {
    longStart();
    lblClose.setOnMouseClicked((MouseEvent event) -> {
      Platform.exit();
      System.exit(0);
    });
  }

  private void longStart()
  {
    Service<ObservableList> service = new Service<ObservableList>()
    {
      @Override
      protected Task createTask()
      {
        return new Task<ObservableList<String>>()
        {
          @Override
          protected ObservableList<String> call() throws InterruptedException
          {
            updateMessage("Finding friends . . .");
            updateProgress(0, 10);
            for (int i = 0; i < 10; i++)
            {
              Thread.sleep(300);
              updateProgress(i + 1, 10);
            }
            updateMessage("Found them.");
            return FXCollections.observableArrayList();
          }
        };
      }
    };
    service.start();
    service.setOnRunning((WorkerStateEvent event) -> {
      new FadeInLeftTransition(lblWelcome).play();
      new FadeInTransition(vboxBottom).play();
    });
    service.setOnSucceeded((WorkerStateEvent event) -> {
      Config config = new Config();
      config.newStage(lblClose, "/sand/view/Login.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
    });
  }
}
