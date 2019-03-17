package sand.dboperations;

import java.util.ArrayList;
import java.util.List;

import sand.model.Order;

/**
 * @author Sandeepa
 */
public class OrderDBOperations extends DBConfigurations
{

  private static OrderDBOperations instance = new OrderDBOperations();

  private OrderDBOperations()
  {
  }

  public static OrderDBOperations getInstance()
  {
    return instance;
  }

  public List<Order> getOrders()
  {
    try
    {
      ArrayList<Order> lst = new ArrayList<>();
      databaseConnection = getDatabaseConnection();
      String query = "SELECT * FROM orders";
      preparedStatement = databaseConnection.prepareStatement(query);

      resultSet = preparedStatement.executeQuery();

      while (resultSet.next())
      {
        Order order = new Order();
        order.setOrderId(resultSet.getInt(1));
        order.setCustomerName(resultSet.getString(2));
        order.setPatientId(resultSet.getString(3));
        order.setDate(resultSet.getString(4));
        order.setFixedCheckboxValues(resultSet.getString(5));
        order.setRemovableCheckboxValues(resultSet.getString(6));
        order.setImplantCheckboxValues(resultSet.getString(7));
        order.setTeethSelectionType(resultSet.getString(8));
        order.setTeethNumbers(resultSet.getString(9));
        order.setToothDropDownText(resultSet.getString(10));
        order.setToothText(resultSet.getString(11));
        lst.add(order);
      }
      return lst;

    }
    catch (Exception e)
    {
      System.out.println(e);
      return null;
    }
    finally
    {
      closeConnections();
    }
  }

  public List<Order> getOrders(String startDate, String endDate)
  {
    try
    {
      ArrayList<Order> lst = new ArrayList<>();
      databaseConnection = getDatabaseConnection();
      String query = "SELECT * FROM orders WHERE created_date between '" + startDate + "' AND '" + endDate + "'";
      preparedStatement = databaseConnection.prepareStatement(query);

      resultSet = preparedStatement.executeQuery();

      while (resultSet.next())
      {
        Order order = new Order();
        order.setOrderId(resultSet.getInt(1));
        order.setCustomerName(resultSet.getString(2));
        order.setPatientId(resultSet.getString(3));
        order.setDate(resultSet.getString(4));
        order.setFixedCheckboxValues(resultSet.getString(5));
        order.setRemovableCheckboxValues(resultSet.getString(6));
        order.setImplantCheckboxValues(resultSet.getString(7));
        order.setTeethSelectionType(resultSet.getString(8));
        order.setTeethNumbers(resultSet.getString(9));
        order.setToothDropDownText(resultSet.getString(10));
        order.setToothText(resultSet.getString(11));
        lst.add(order);
      }
      return lst;

    }
    catch (Exception e)
    {
      System.out.println(e);
      return null;
    }
    finally
    {
      closeConnections();
    }
  }

  public void saveOrder(Order order)
  {
    try
    {
      databaseConnection = getDatabaseConnection();
      String query = "INSERT INTO orders (customer_name, patient_id, created_date, fixed_prosthodontics, removable_prosthodontics, implant_supported_prosthodontics, teeth_selection_type, teeth_numbers, tooth_selection_text, tooth_text) VALUES (?,?,?,?,?,?,?,?,?,?)";
      preparedStatement = databaseConnection.prepareStatement(query);
      preparedStatement.setString(1, order.getCustomerName());
      preparedStatement.setString(2, order.getPatientId());
      preparedStatement.setString(3, order.getDate());
      preparedStatement.setString(4, order.getFixedCheckboxValues());
      preparedStatement.setString(5, order.getRemovableCheckboxValues());
      preparedStatement.setString(6, order.getImplantCheckboxValues());
      preparedStatement.setString(7, order.getToothSelectionType());
      preparedStatement.setString(8, order.getToothNumbers());
      preparedStatement.setString(9, order.getToothDropDownText());
      preparedStatement.setString(10, order.getToothText());
      preparedStatement.executeUpdate();
    }
    catch (Exception e)
    {
      System.out.println(e);
    }
    finally
    {
      closeConnections();
    }
  }

  public void updateOrder(Order order)
  {
    try
    {
      databaseConnection = getDatabaseConnection();
      String query =
          "UPDATE orders SET customer_name='" + order.getCustomerName() + "',patient_id='" + order.getPatientId()
              + "',created_date='" + order.getDate() + "',fixed_prosthodontics='" + order.getFixedCheckboxValues()
              + "',removable_prosthodontics='" + order.getRemovableCheckboxValues()
              + "',implant_supported_prosthodontics='" + order.getImplantCheckboxValues() + "',teeth_selection_type='"
              + order.getToothSelectionType() + "',teeth_numbers='" + order.getToothNumbers()
              + "',tooth_selection_text='" + order.getToothDropDownText() + "',tooth_text='" + order.getToothText()
              + "' WHERE order_id=" + order.getOrderId();
      preparedStatement = databaseConnection.prepareStatement(query);
      preparedStatement.executeUpdate();
    }
    catch (Exception e)
    {
      System.out.println(e);
    }
    finally
    {
      closeConnections();
    }
  }

  public void deleteOrder(String orderId)
  {
    try
    {
      databaseConnection = getDatabaseConnection();
      String query = "DELETE FROM orders WHERE order_id=" + orderId;
      preparedStatement = databaseConnection.prepareStatement(query);
      preparedStatement.executeUpdate();
    }
    catch (Exception e)
    {
      System.out.println(e);
    }
    finally
    {
      closeConnections();
    }
  }
}
