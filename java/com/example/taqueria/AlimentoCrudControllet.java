package com.example.taqueria;

import com.example.taqueria.Base.AlimentoDAO;
import com.example.taqueria.Base.CategoriaDAO;
import com.example.taqueria.Base.Conexion;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AlimentoCrudControllet implements Initializable {
    CategoriaDAO cat=new CategoriaDAO(Conexion.getConnection());
    AlimentoDAO alimneto=new AlimentoDAO(Conexion.getConnection());

    @FXML
    TableView tblCategorias,tblAlimentos;
    @FXML
    TextField txtNombre,txtPrecio,txtImagen;
    @FXML
    Button btnNuevo,btnUpdate,btnBorrar;
    @FXML
    Label lblError;
    @FXML
    ImageView imgView;
    @FXML
    AnchorPane anchorPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorPane.setStyle("-fx-background-color:#fff8e1");
        lblError.setStyle("-fx-text-fill:#b71c1c");
        btnBorrar.setDisable(true);
        btnUpdate.setDisable(true);

        tblCategorias.setItems(cat.getAll());
        tblCategorias.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Categoria categoria= (Categoria) tblCategorias.getSelectionModel().getSelectedItem();
                int id_categoria = categoria.getId_Categoria();
                tblAlimentos.setItems(alimneto.mostrar(id_categoria));
                tblAlimentos.refresh();
            }
        });
        tblAlimentos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alimento alm= (Alimento) tblAlimentos.getSelectionModel().getSelectedItem();
                txtNombre.setText(alm.getNombre());
                txtPrecio.setText(alm.getPrecio());
                txtImagen.setText(alm.getImagen());
                try{
                    Image img = new Image(alm.getImagen());
                    imgView.setImage(img);
                }catch (Exception e) {
                    imgView.setImage(null);
                }
                btnUpdate.setDisable(false);
                btnBorrar.setDisable(false);
            }
        });
        btnNuevo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    lblError.setText("");
                    Categoria categoria = (Categoria) tblCategorias.getSelectionModel().getSelectedItem();
                    if (alimneto.create(txtNombre.getText(), txtPrecio.getText(), categoria.getId_Categoria(), txtImagen.getText())) {
                        tblAlimentos.setItems(alimneto.mostrar(categoria.getId_Categoria()));
                        tblAlimentos.refresh();
                        txtNombre.setText("");
                        txtPrecio.setText("");
                        txtImagen.setText("");
                        btnUpdate.setDisable(true);
                        btnBorrar.setDisable(true);
                    }
                    lblError.setText("No se pudo crear");
                }catch(NullPointerException npe){
                    lblError.setText("No se ha seleccionado categoria");
                }
            }
        });
        btnUpdate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    lblError.setText("");
                    int id = ((Alimento) tblAlimentos.getSelectionModel().getSelectedItem()).getId_Alimento();
                    Categoria categoria = (Categoria) tblCategorias.getSelectionModel().getSelectedItem();
                    if (alimneto.update(id, txtNombre.getText(), txtPrecio.getText(), txtImagen.getText())) {
                        tblAlimentos.setItems(alimneto.mostrar(categoria.getId_Categoria()));
                        tblAlimentos.refresh();
                        txtNombre.setText("");
                        txtPrecio.setText("");
                        txtImagen.setText("");
                        btnUpdate.setDisable(true);
                        btnBorrar.setDisable(true);
                    }
                    else {
                        lblError.setText("No se pudo actualizar");
                    }
                }catch(NullPointerException npe){
                    lblError.setText(npe.getMessage());
                }
            }
        });
        btnBorrar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lblError.setText("");
                try {
                    int id = ((Alimento) tblAlimentos.getSelectionModel().getSelectedItem()).getId_Alimento();
                    if (alimneto.drop(id)) {
                        Categoria categoria = (Categoria) tblCategorias.getSelectionModel().getSelectedItem();
                        tblAlimentos.setItems(alimneto.mostrar(categoria.getId_Categoria()));
                        tblAlimentos.refresh();
                        txtNombre.setText("");
                        txtPrecio.setText("");
                        txtImagen.setText("");
                        imgView.setImage(null);
                        btnUpdate.setDisable(true);
                        btnBorrar.setDisable(true);
                    }
                    else lblError.setText("No se pudo borrar");
                }catch(NullPointerException npe){
                    lblError.setText(npe.getMessage());
                }
            }
        });
        txtImagen.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                try{
                    Image img = new Image(txtImagen.getText());
                    imgView.setImage(img);
                }catch (Exception e) {
                    imgView.setImage(null);
                }
            }
        });
    }
}
