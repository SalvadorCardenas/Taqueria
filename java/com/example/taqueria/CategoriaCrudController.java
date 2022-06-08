package com.example.taqueria;

import com.example.taqueria.Base.CategoriaDAO;
import com.example.taqueria.Base.Conexion;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoriaCrudController implements Initializable {
    CategoriaDAO cat=new CategoriaDAO(Conexion.getConnection());

    @FXML
    Button addButton,dropButton,updateBtn;
    @FXML
    TextField txtNueva;
    @FXML
    TableView tblCategorias;
    @FXML
    GridPane gridPane;
    @FXML
    Label lblError;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gridPane.setStyle("-fx-background-color:#fff8e1");
        lblError.setStyle("-fx-text-fill:#b71c1c");
        addButton.setDisable(false);
        dropButton.setDisable(true);
        updateBtn.setDisable(true);

        tblCategorias.setItems(cat.getAll());
        tblCategorias.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lblError.setText("");
                Categoria categ= (Categoria) tblCategorias.getSelectionModel().getSelectedItem();
                if (categ!=null){
                    dropButton.setDisable(false);
                    updateBtn.setDisable(false);
                    txtNueva.setText(categ.getNombre());
                }
                else
                    lblError.setText("No se pudo leer la categoria");
            }
        });
        updateBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lblError.setText("");
                String nueva=txtNueva.getText();
                Categoria categ = (Categoria) tblCategorias.getSelectionModel().getSelectedItem();
                if (cat.update(categ.getId_Categoria(), nueva)){
                        tblCategorias.setItems(cat.getAll());
                        tblCategorias.refresh();
                }
                else {
                    lblError.setText("No se pudo actualizar");
                }
            }
        });
        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lblError.setText("");
                String nueva=txtNueva.getText();
                if (cat.create(nueva)){
                    tblCategorias.setItems(cat.getAll());
                    tblCategorias.refresh();
                }
                else{
                    lblError.setText("No se pudo crear");
                }
            }
        });
        dropButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lblError.setText("");
                Categoria categ = (Categoria) tblCategorias.getSelectionModel().getSelectedItem();

                int id = categ.getId_Categoria();
                if (cat.drop(id)){
                    tblCategorias.setItems(cat.getAll());
                    tblCategorias.refresh();
                    dropButton.setDisable(true);
                }
                else{
                    lblError.setText("No se pudo eliminar");
                }
            }
        });
    }
}
