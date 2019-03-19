package sand.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import org.jpedal.examples.viewer.Commands;
import org.jpedal.examples.viewer.OpenViewerFX;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;

import herudi.animations.FadeInUpTransition;
import herudi.config.Config;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import sand.dboperations.OrderDBOperations;
import sand.model.Order;

/**
 * @author SFernando
 */
public class OrderController implements Initializable
{
  @FXML
  private TableView<Order> tableData;

  @FXML
  private TableColumn colAction;

  @FXML
  private TableColumn<Order, String> colOrderId;

  @FXML
  private TableColumn<Order, String> colCustomerName;

  @FXML
  private TableColumn<Order, String> colPatientID;

  @FXML
  private TableColumn<Order, String> colDate;

  @FXML
  private TableColumn<Order, String> colOne;

  @FXML
  private TableColumn<Order, String> colTwo;

  @FXML
  private TableColumn<Order, String> colThree;

  @FXML
  private TableColumn<Order, String> colTeethSelectionType;

  @FXML
  private TableColumn<Order, String> colTeethNumbers;

  @FXML
  private TableColumn<Order, String> colToothDropDownText;

  @FXML
  private TableColumn<Order, String> colToothTextArea;

  @FXML
  private Button btnNew;

  @FXML
  private AnchorPane paneTabel;

  @FXML
  private AnchorPane paneCrud;

  @FXML
  private AnchorPane secondPane;

  @FXML
  private TextField txtId;

  @FXML
  private ComboBox cbZip;

  @FXML
  private DatePicker date;

  @FXML
  private CheckBox implant1, implant2, implant3, implant4;

  @FXML
  private CheckBox removable1, removable2, removable3, removable4, removable5, removable6, removable7, removable8, removable9, removable10, removable11, removable12, removable13, removable14, removable15, removable16;

  @FXML
  private CheckBox fixed1, fixed2, fixed3, fixed4, fixed5, fixed6, fixed7, fixed8, fixed9, fixed10, fixed11;

  @FXML
  private TextField txtName;

  @FXML
  private TextField customerName;

  @FXML
  private TextArea txtAddress1;

  @FXML
  private TextArea txtAddress2;

  @FXML
  private TextField txtCity;

  @FXML
  private TextField txtState;

  @FXML
  private TextField txtPhone;

  @FXML
  private TextField txtFax;

  @FXML
  private TextField txtEmail;

  @FXML
  private TextField txtCredit;

  @FXML
  private Button btnSave;

  @FXML
  private Button btnBack;

  Integer status;

  @FXML
  private ImageView imgLoad;

  @FXML
  private ProgressBar bar;

  @FXML
  private DatePicker startDate;

  @FXML
  private DatePicker endDate;

  @FXML
  private ToggleButton btnSingle;

  @FXML
  private ToggleButton btnBridge;

  @FXML
  private GridPane teethPane;

  @FXML
  private ToggleButton btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn21, btn22, btn23, btn24, btn25, btn26, btn27, btn28, btn31, btn32, btn33, btn34, btn35, btn36, btn37, btn38, btn41, btn42, btn43, btn44, btn45, btn46, btn47, btn48;

  @FXML
  private TextField patientID;

  @FXML
  private TextArea toothTextArea;

  @FXML
  private ComboBox<String> toothComboBoxOne, toothComboBoxTwo, toothComboBoxThree;

  private ToggleGroup teethToggleGroup = new ToggleGroup();

  private ObservableList<Order> listData;

  private List<ToggleButton> teethList;

  private List<String> toothSelectionList;

  /**
   * Initializes the controller class.
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb)
  {
    Platform.runLater(() -> {
      listData = FXCollections.observableArrayList();
      status = 0;
      Config.setModelColumn(colOrderId, "orderId");
      Config.setModelColumn(colDate, "date");
      Config.setModelColumn(colCustomerName, "customerName");
      Config.setModelColumn(colPatientID, "patientId");
      Config.setModelColumn(colOne, "fixedCheckboxValues");
      Config.setModelColumn(colTwo, "removableCheckboxValues");
      Config.setModelColumn(colThree, "implantCheckboxValues");
      Config.setModelColumn(colTeethSelectionType, "toothSelectionType");
      Config.setModelColumn(colTeethNumbers, "toothNumbers");
      Config.setModelColumn(colToothDropDownText, "toothDropDownText");
      Config.setModelColumn(colToothTextArea, "toothText");
      colAction
          .setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, Boolean>, ObservableValue<Boolean>>()
          {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Object, Boolean> p)
            {
              return new SimpleBooleanProperty(p.getValue() != null);
            }
          });
      colAction.setCellFactory(new Callback<TableColumn<Object, Boolean>, TableCell<Object, Boolean>>()
      {
        @Override
        public TableCell<Object, Boolean> call(TableColumn<Object, Boolean> p)
        {
          return new ButtonCell(tableData);
        }
      });

      teethList = new ArrayList<>();

      teethList.addAll(Arrays
          .asList(btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn21, btn22, btn23, btn24, btn25, btn26,
              btn27, btn28, btn31, btn32, btn33, btn34, btn35, btn36, btn37, btn38, btn41, btn42, btn43, btn44, btn45,
              btn46, btn47, btn48));

      final ToggleGroup toggleGroup = new ToggleGroup();
      btnSingle.setToggleGroup(toggleGroup);
      btnBridge.setToggleGroup(toggleGroup);

      teethPane.disableProperty().setValue(true);

      btnSingle.setOnAction(new EventHandler<ActionEvent>()
      {
        @Override
        public void handle(ActionEvent e)
        {
          if (btnSingle.isSelected())
          {
            clearTeethList();
            setToggleGroupForTeeth();
          }
          else
          {
            clearTeethList();
          }
          if (!btnSingle.isSelected() && !btnBridge.isSelected())
          {
            teethPane.disableProperty().setValue(true);
            clearTeethList();
          }
          else
          {
            teethPane.disableProperty().setValue(false);
            clearTeethList();
          }
        }
      });

      btnBridge.setOnAction(new EventHandler<ActionEvent>()
      {
        @Override
        public void handle(ActionEvent e)
        {
          if (btnBridge.isSelected())
          {
            clearTeethList();
            removetToggleGroupForTeeth();
          }
          else
          {
            clearTeethList();
          }

          if (!btnSingle.isSelected() && !btnBridge.isSelected())
          {
            teethPane.disableProperty().setValue(true);
            clearTeethList();
          }
          else
          {
            teethPane.disableProperty().setValue(false);
            clearTeethList();
          }
        }
      });

      toothSelectionList = new ArrayList<>();
      toothSelectionList.addAll(Arrays
          .asList("A1", "A2", "A3", "A3", "A4", "A5", "B1", "B2", "B3", "B4", "C1", "C2", "C3", "C4", "D1", "D2", "D3",
              "D4"));

      toothComboBoxOne.getItems().addAll(toothSelectionList);
      toothComboBoxTwo.getItems().addAll(toothSelectionList);
      toothComboBoxThree.getItems().addAll(toothSelectionList);

      selectWithService();
    });
  }

  private void clearTeethList()
  {
    for (ToggleButton tooth : teethList)
    {
      tooth.setSelected(false);
    }
  }

  private void setToggleGroupForTeeth()
  {
    for (ToggleButton tooth : teethList)
    {
      tooth.setToggleGroup(teethToggleGroup);
    }
  }

  private void removetToggleGroupForTeeth()
  {
    for (ToggleButton tooth : teethList)
    {
      tooth.setToggleGroup(null);
    }
  }

  private String getCombinedTeethNumbers()
  {
    String combinedTeethNumbers = "";
    for (ToggleButton tooth : teethList)
    {
      if (tooth.isSelected())
      {
        combinedTeethNumbers += tooth.getText() + ", ";
      }
    }
    if (!combinedTeethNumbers.equals(""))
    {
      combinedTeethNumbers = combinedTeethNumbers.substring(0, combinedTeethNumbers.length() - 2);
    }
    return combinedTeethNumbers;
  }

  private void clear()
  {
    txtId.setText("Generate");
    customerName.setText("");
    patientID.setText("");
    date.setValue(null);
    clearCheckboxValuesText(Arrays
        .asList(fixed1, fixed2, fixed3, fixed4, fixed5, fixed6, fixed7, fixed8, fixed9, fixed10, fixed11, removable1,
            removable2, removable3, removable4, removable5, removable6, removable7, removable8, removable9, removable10,
            removable11, removable12, removable13, removable14, removable15, removable16, implant1, implant2, implant3,
            implant4));
    btnSingle.setSelected(false);
    btnBridge.setSelected(false);
    clearTeethList();
    teethPane.disableProperty().setValue(true);
    toothComboBoxOne.setValue(null);
    toothComboBoxTwo.setValue(null);
    toothComboBoxThree.setValue(null);
    toothTextArea.setText("");
  }

  private void selectData()
  {
    listData.clear();
    listData.addAll(OrderDBOperations.getInstance().getOrders());
    tableData.setItems(listData);
  }

  private void selectWithService()
  {
    Service<Integer> service = new Service<Integer>()
    {
      @Override
      protected Task<Integer> createTask()
      {
        selectData();
        return new Task<Integer>()
        {
          @Override
          protected Integer call() throws Exception
          {
            Integer max = 1;
            if (max > 35)
            {
              max = 30;
            }
            updateProgress(0, max);
            for (int k = 0; k < max; k++)
            {
              Thread.sleep(40);
              updateProgress(k + 1, max);
            }
            return max;
          }
        };
      }
    };
    service.start();
    bar.progressProperty().bind(service.progressProperty());
    service.setOnRunning((WorkerStateEvent event) -> {
      imgLoad.setVisible(true);
    });
    service.setOnSucceeded((WorkerStateEvent event) -> {
      imgLoad.setVisible(false);
      new FadeInUpTransition(paneTabel).play();
    });
  }

  @FXML
  private void keyState(KeyEvent e)
  {
    if (txtState.getText().length() > 2)
    {
      Config.dialog(Alert.AlertType.INFORMATION, "State Must 2 Char");
      txtState.clear();
    }
  }

  @FXML
  private void aksiKlikTableData(MouseEvent event)
  {
    if (status == 1)
    {
      try
      {
        Order klik = tableData.getSelectionModel().getSelectedItem();
        clear();
        txtId.setText(klik.getOrderId().toString());
        customerName.setText(klik.getCustomerName());
        patientID.setText(klik.getPatientId());
        date.setValue(LocalDate.parse(klik.getDate()));
        setCheckboxValuesText(
            Arrays.asList(fixed1, fixed2, fixed3, fixed4, fixed5, fixed6, fixed7, fixed8, fixed9, fixed10, fixed11),
            Arrays.asList(klik.getFixedCheckboxValues().split("\n")));
        setCheckboxValuesText(Arrays
                .asList(removable1, removable2, removable3, removable4, removable5, removable6, removable7, removable8,
                    removable9, removable10, removable11, removable12, removable13, removable14, removable15, removable16),
            Arrays.asList(klik.getRemovableCheckboxValues().split("\n")));
        setCheckboxValuesText(Arrays.asList(implant1, implant2, implant3, implant4),
            Arrays.asList(klik.getImplantCheckboxValues().split("\n")));
        if (klik.getToothSelectionType().equals("SINGLE"))
        {
          btnSingle.setSelected(true);
        }
        else
        {
          btnBridge.setSelected(true);
        }
        setToggleButtonValues(teethList, Arrays.asList(klik.getToothNumbers().split(", ")));
        teethPane.disableProperty().setValue(false);
        if (btnSingle.isSelected())
        {
          setToggleGroupForTeeth();
        }
        String[] dropDownTexts = klik.getToothDropDownText().split(", ");
        toothComboBoxOne.setValue(dropDownTexts[0]);
        toothComboBoxTwo.setValue(dropDownTexts[1]);
        toothComboBoxThree.setValue(dropDownTexts[2]);
        toothTextArea.setText(klik.getToothText());
      }
      catch (Exception e)
      {
      }
    }
  }

  private void setCheckboxValuesText(List<CheckBox> list, List<String> checkboxNames)
  {
    for (CheckBox checkBox : list)
    {
      if (checkboxNames.contains(checkBox.getText()))
      {
        checkBox.setSelected(true);
      }
    }
  }

  private void setToggleButtonValues(List<ToggleButton> list, List<String> toggleButtonNames)
  {
    for (ToggleButton toggleButton : list)
    {
      if (toggleButtonNames.contains(toggleButton.getText()))
      {
        toggleButton.setSelected(true);
      }
    }
  }

  private void clearCheckboxValuesText(List<CheckBox> list)
  {
    for (CheckBox checkBox : list)
    {
      checkBox.setSelected(false);
    }
  }

  @FXML
  private void aksiNew(ActionEvent event)
  {
    paneTabel.setOpacity(0);
    new FadeInUpTransition(paneCrud).play();
    Platform.runLater(() -> {
      clear();
    });
  }

  @FXML
  private void searchOrder(ActionEvent event)
  {
    if (startDate.getValue() == null)
    {
      Config.dialog(Alert.AlertType.ERROR, "Please select the start date. . .");
      return;
    }
    else if (endDate.getValue() == null)
    {
      Config.dialog(Alert.AlertType.ERROR, "Please select the end date. . .");
      return;
    }
    listData.clear();
    listData.addAll(
        OrderDBOperations.getInstance().getOrders(startDate.getValue().toString(), endDate.getValue().toString()));
    tableData.setItems(listData);
  }

  @FXML
  private void createPDF(ActionEvent event)
  {
    Document tableDocument = new Document(PageSize.A4.rotate(), 0, 0, 40, 40);
    try
    {
      File directory = new File("Order info");
      if (!directory.exists())
      {
        directory.mkdir();
      }
      String fileName = directory + "\\" + LocalDateTime.now().toString().replace(":", "_") + ".pdf";
      PdfWriter.getInstance(tableDocument, new FileOutputStream(fileName));
      tableDocument.open();
      PdfPTable pdfPTable = new PdfPTable(11);
      pdfPTable.setTotalWidth(new float[] { 45, 80, 50, 50, 80, 80, 100, 50, 80, 80, 80 });
      pdfPTable.setLockedWidth(true);

      pdfPTable.addCell(getTableHeader("ORDER ID"));
      pdfPTable.addCell(getTableHeader("CUSTOMER NAME"));
      pdfPTable.addCell(getTableHeader("PATIENT ID"));
      pdfPTable.addCell(getTableHeader("DATE"));
      pdfPTable.addCell(getTableHeader("FIXED PROSTHODONTICS"));
      pdfPTable.addCell(getTableHeader("REMOVABLE PROSTHODONTICS"));
      pdfPTable.addCell(getTableHeader("IMPLANT SUPPORTED PROSTHODONTICS"));
      pdfPTable.addCell(getTableHeader("TEETH SELECTION"));
      pdfPTable.addCell(getTableHeader("TEETH NUMBERS"));
      pdfPTable.addCell(getTableHeader("TOOTH DROP DOWN TEXT"));
      pdfPTable.addCell(getTableHeader("TOOTH TEXT AREA"));

      pdfPTable.setHeaderRows(1);
      //create a cell object
      PdfPCell table_cell;
      for (Order order : listData)
      {
        String orderId = order.getOrderId().toString();
        table_cell = new PdfPCell(getTextWithStyles(orderId));
        pdfPTable.addCell(table_cell);

        String customerName = order.getCustomerName();
        table_cell = new PdfPCell(getTextWithStyles(customerName));
        pdfPTable.addCell(table_cell);

        String patientId = order.getPatientId();
        table_cell = new PdfPCell(getTextWithStyles(patientId));
        pdfPTable.addCell(table_cell);

        String createdDate = order.getDate();
        table_cell = new PdfPCell(getTextWithStyles(createdDate));
        pdfPTable.addCell(table_cell);

        String typeOne = order.getFixedCheckboxValues();
        table_cell = new PdfPCell(getTextWithStyles(typeOne));
        pdfPTable.addCell(table_cell);

        String typeTwo = order.getRemovableCheckboxValues();
        table_cell = new PdfPCell(getTextWithStyles(typeTwo));
        pdfPTable.addCell(table_cell);

        String typeThree = order.getImplantCheckboxValues();
        table_cell = new PdfPCell(getTextWithStyles(typeThree));
        pdfPTable.addCell(table_cell);

        String teethSelection = order.getToothSelectionType();
        table_cell = new PdfPCell(getTextWithStyles(teethSelection));
        pdfPTable.addCell(table_cell);

        String teethNumbers = order.getToothNumbers();
        table_cell = new PdfPCell(getTextWithStyles(teethNumbers));
        pdfPTable.addCell(table_cell);

        String teethDropDownText = order.getToothDropDownText();
        table_cell = new PdfPCell(getTextWithStyles(teethDropDownText));
        pdfPTable.addCell(table_cell);

        String toothTextArea = order.getToothText();
        table_cell = new PdfPCell(getTextWithStyles(toothTextArea));
        pdfPTable.addCell(table_cell);
      }
      /* Attach report table to PDF */
      tableDocument.add(pdfPTable);
      tableDocument.close();

      OpenViewerFX.exitOnClose = false;
      OpenViewerFX pdfViewer = new OpenViewerFX(new Stage(), null);
      pdfViewer.setupViewer();
      Object[] input = { new File(fileName) };
      pdfViewer.executeCommand(Commands.OPENFILE, input);
    }
    catch (DocumentException e)
    {
      e.printStackTrace();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (RuntimeException e)
    {
      Config.dialog(Alert.AlertType.ERROR, "Table view is empty. . .");
      tableDocument.close();
    }
  }

  private Paragraph getTableHeader(String headerText)
  {
    Font tableHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    Paragraph header = new Paragraph();
    header.setFont(tableHeader);
    header.add(headerText);
    return header;
  }

  private Paragraph getTextWithStyles(String text)
  {
    Font tableHeader = FontFactory.getFont(FontFactory.HELVETICA, 8);
    Paragraph paragraph = new Paragraph();
    paragraph.setFont(tableHeader);
    paragraph.add(text);
    return paragraph;
  }

  @FXML
  private void next(ActionEvent event)
  {
    if (customerName.getText() == null || (customerName.getText().replaceAll("\\s+", "")).equals(""))
    {
      Config.dialog(Alert.AlertType.ERROR, "Please enter the customer name. . .");
      previous(null);
      customerName.requestFocus();
      return;
    }
    if (patientID.getText() == null || (patientID.getText().replaceAll("\\s+", "")).equals(""))
    {
      Config.dialog(Alert.AlertType.ERROR, "Please enter the patient ID. . .");
      previous(null);
      patientID.requestFocus();
      return;
    }
    if (date.getValue() == null)
    {
      Config.dialog(Alert.AlertType.ERROR, "Please select a date. . .");
      previous(null);
      date.requestFocus();
      return;
    }
    if (!btnSingle.isSelected() && !btnBridge.isSelected())
    {
      Config.dialog(Alert.AlertType.ERROR, "Please select the tooth type. . .");
      previous(null);
      txtId.requestFocus();
      return;
    }
    if (getCombinedTeethNumbers().equals(""))
    {
      Config.dialog(Alert.AlertType.ERROR, "Please select the teeth numbers. . .");
      previous(null);
      txtId.requestFocus();
      return;
    }
    paneTabel.setOpacity(0);
    paneCrud.setOpacity(0);
    new FadeInUpTransition(secondPane).play();
    Platform.runLater(() -> {
    });
  }

  @FXML
  private void previous(ActionEvent event)
  {
    paneTabel.setOpacity(0);
    secondPane.setOpacity(0);
    new FadeInUpTransition(paneCrud).play();
    Platform.runLater(() -> {
    });
  }

  @FXML
  private void saveOrUpdateOrder(ActionEvent event)
  {
    if (toothComboBoxOne.getValue() == null || toothComboBoxTwo.getValue() == null
        || toothComboBoxThree.getValue() == null)
    {
      Config.dialog(Alert.AlertType.ERROR, "Please select a value from all drop downs . . .");
      toothComboBoxOne.requestFocus();
      return;
    }

    Order order = new Order();
    order.setDate(date.getValue().toString());
    order.setCustomerName(customerName.getText());
    order.setPatientId(patientID.getText());
    order.setFixedCheckboxValues(getCheckboxValuesText(
        Arrays.asList(fixed1, fixed2, fixed3, fixed4, fixed5, fixed6, fixed7, fixed8, fixed9, fixed10, fixed11)));
    order.setRemovableCheckboxValues(getCheckboxValuesText(new ArrayList<>(Arrays
        .asList(removable1, removable2, removable3, removable4, removable5, removable6, removable7, removable8,
            removable9, removable10, removable11, removable12, removable13, removable14, removable15, removable16))));
    order.setImplantCheckboxValues(
        getCheckboxValuesText(new ArrayList<>(Arrays.asList(implant1, implant2, implant3, implant4))));
    order.setTeethSelectionType(btnSingle.isSelected() ? btnSingle.getText() : btnBridge.getText());
    order.setTeethNumbers(getCombinedTeethNumbers());
    order.setToothDropDownText(
        toothComboBoxOne.getValue() + ", " + toothComboBoxTwo.getValue() + ", " + toothComboBoxThree.getValue());
    order.setToothText(toothTextArea.getText());

    if (!txtId.getText().equals("Generate"))
    {
      order.setOrderId(Integer.parseInt(txtId.getText()));
      OrderDBOperations.getInstance().updateOrder(order);
    }
    else
    {
      OrderDBOperations.getInstance().saveOrder(order);
    }
    clear();
    selectData();
    Config.dialog(Alert.AlertType.INFORMATION, "Data saved successfully. . .");
    aksiBack(null);
  }

  private String getCheckboxValuesText(List<CheckBox> list)
  {
    String checkboxValuesText = "";
    for (CheckBox checkBox : list)
    {
      if (checkBox.isSelected())
        checkboxValuesText += checkBox.getText() + "\n";
    }
    return checkboxValuesText;
  }

  @FXML
  private void aksiBack(ActionEvent event)
  {
    paneCrud.setOpacity(0);
    secondPane.setOpacity(0);
    new FadeInUpTransition(paneTabel).play();
  }

  private class ButtonCell extends TableCell<Object, Boolean>
  {
    final Hyperlink cellButtonDelete = new Hyperlink("Delete");

    final Hyperlink cellButtonEdit = new Hyperlink("Edit");

    final HBox hb = new HBox(cellButtonDelete, cellButtonEdit);

    ButtonCell(final TableView tblView)
    {
      hb.setSpacing(4);
      hb.setAlignment(Pos.CENTER_LEFT);
      cellButtonDelete.setOnAction((ActionEvent t) -> {
        status = 1;
        int row = getTableRow().getIndex();
        tableData.getSelectionModel().select(row);
        aksiKlikTableData(null);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this data?");
        alert.initStyle(StageStyle.UTILITY);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
          OrderDBOperations.getInstance().deleteOrder(txtId.getText());
          clear();
          selectData();
        }
        status = 0;
      });
      cellButtonEdit.setOnAction((ActionEvent event) -> {
        status = 1;
        int row = getTableRow().getIndex();
        tableData.getSelectionModel().select(row);
        aksiKlikTableData(null);
        paneTabel.setOpacity(0);
        new FadeInUpTransition(paneCrud).play();
        status = 0;
      });
    }

    @Override
    protected void updateItem(Boolean t, boolean empty)
    {
      super.updateItem(t, empty);
      if (!empty)
      {
        setGraphic(hb);
      }
      else
      {
        setGraphic(null);
      }
    }
  }
}
