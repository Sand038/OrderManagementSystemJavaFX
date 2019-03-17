package sand.dboperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sandeepa
 */
abstract class DBConfigurations
{

  Connection databaseConnection = null;

  PreparedStatement preparedStatement = null;

  ResultSet resultSet = null;

  Connection getDatabaseConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:sqlite::resource:OrderManagementSystem.db", "root", "");
  }

  void closeConnections()
  {
    try
    {
      if (preparedStatement != null)
      {
        preparedStatement.close();
      }
      if (databaseConnection != null)
      {
        databaseConnection.close();
      }
    }
    catch (Exception e)
    {
      System.out.println(e);
    }
  }
}

