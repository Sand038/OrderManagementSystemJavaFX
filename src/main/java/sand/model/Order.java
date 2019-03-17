package sand.model;

public class Order implements java.io.Serializable
{
  private Integer orderId;

  private String date;

  private String fixedCheckboxValues;

  private String removableCheckboxValues;

  private String implantCheckboxValues;

  private String customerName;

  private String patientId;

  private String toothSelectionType;

  private String toothNumbers;

  private String toothDropDownText;

  private String toothText;

  public Order()
  {
  }

  public Order(Integer customerId)
  {
    this.orderId = customerId;
  }

  public Integer getOrderId()
  {
    return this.orderId;
  }

  public void setOrderId(Integer orderId)
  {
    this.orderId = orderId;
  }

  public String getDate()
  {
    return this.date;
  }

  public void setDate(String date)
  {
    this.date = date;
  }

  public String getFixedCheckboxValues()
  {
    return this.fixedCheckboxValues;
  }

  public void setFixedCheckboxValues(String fixedCheckboxValues)
  {
    this.fixedCheckboxValues = fixedCheckboxValues;
  }

  public String getRemovableCheckboxValues()
  {
    return removableCheckboxValues;
  }

  public void setRemovableCheckboxValues(String removableCheckboxValues)
  {
    this.removableCheckboxValues = removableCheckboxValues;
  }

  public String getImplantCheckboxValues()
  {
    return implantCheckboxValues;
  }

  public void setImplantCheckboxValues(String implantCheckboxValues)
  {
    this.implantCheckboxValues = implantCheckboxValues;
  }

  public String getCustomerName()
  {
    return customerName;
  }

  public void setCustomerName(String customerName)
  {
    this.customerName = customerName;
  }

  public String getPatientId()
  {
    return patientId;
  }

  public void setPatientId(String patientId)
  {
    this.patientId = patientId;
  }

  public String getToothDropDownText()
  {
    return toothDropDownText;
  }

  public void setToothDropDownText(String toothDropDownText)
  {
    this.toothDropDownText = toothDropDownText;
  }

  public String getToothText()
  {
    return toothText;
  }

  public void setToothText(String toothText)
  {
    this.toothText = toothText;
  }

  public String getToothSelectionType()
  {
    return toothSelectionType;
  }

  public void setTeethSelectionType(String toothSelectionType)
  {
    this.toothSelectionType = toothSelectionType;
  }

  public String getToothNumbers()
  {
    return toothNumbers;
  }

  public void setTeethNumbers(String toothNumbers)
  {
    this.toothNumbers = toothNumbers;
  }
}


