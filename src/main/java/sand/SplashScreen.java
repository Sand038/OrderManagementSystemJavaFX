package sand;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author SFernando
 */
public class SplashScreen extends Application
{

  @Override
  public void start(Stage stage) throws Exception
  {
    Parent root = FXMLLoader.load(getClass().getResource("/sand/view/Splash.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.initStyle(StageStyle.UNDECORATED);
    stage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args)
  {
    launch(args);
  }

}
