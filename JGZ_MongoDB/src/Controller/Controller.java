package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import View.View;
import Model.modelo;

// @author Jairo_Otaku
public class Controller implements ActionListener, MouseListener {

    View view;
    modelo modelo;
    int filaseleccionada = -1;

    public Controller(View vista) {
        this.view = vista;
    }

    public enum AccionMVC {
        btnAceptarClientes,
        btnModificarClientes,
        btnEliminarClientes,
        btnAceptarArticulos,
        btnModificarArticulos,
        btnEliminarArticulos,
        btnAceptarVentas,
        btnModificarVentas,
        btnEliminarVentas
    }

    public void iniciar() {

        try {
            this.vista.setVisible(true);
            this.vista.jTableClientes.setModel(this.modelo.getTablaCliente());
            this.vista.jTableArticulos.setModel(this.modelo.getTablaArticulos());
            this.vista.jTableVentas.setModel(this.modelo.getTablaVentas());

        } catch (Exception e) {
        }

        this.vista.btnAceptarClientes.setActionCommand("btnAceptarClientes");
        this.vista.btnAceptarClientes.addActionListener(this);

        this.vista.btnModificarClientes.setActionCommand("btnModificarClientes");
        this.vista.btnModificarClientes.addActionListener(this);

        this.vista.btnEliminarClientes.setActionCommand("btnEliminarClientes");
        this.vista.btnEliminarClientes.addActionListener(this);

        this.vista.btnAceptarArticulos.setActionCommand("btnAceptarArticulos");
        this.vista.btnAceptarArticulos.addActionListener(this);

        this.vista.btnModificarArticulos.setActionCommand("btnModificarArticulos");
        this.vista.btnModificarArticulos.addActionListener(this);

        this.vista.btnEliminarArticulos.setActionCommand("btnEliminarArticulos");
        this.vista.btnEliminarArticulos.addActionListener(this);

        this.vista.btnAceptarVentas.setActionCommand("btnAceptarVentas");
        this.vista.btnAceptarVentas.addActionListener(this);

        this.vista.btnModificarVentas.setActionCommand("btnModificarVentas");
        this.vista.btnModificarVentas.addActionListener(this);

        this.vista.btnEliminarVentas.setActionCommand("btnEliminarVentas");
        this.vista.btnEliminarVentas.addActionListener(this);

        this.vista.jTableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClientesMouseClicked(evt);
            }
        });

        this.vista.jTableArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ArticulosMouseClicked(evt);
            }
        });

        this.vista.jTableVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VentasMouseClicked(evt);
            }
        });

        this.vista.txtPVPArticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {

                entraNumero(evt);
                if (vista.txtPVPArticulos.getText().length() >= 16) {
                    evt.consume();
                }

            }
        });

        this.vista.txtStockArticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {

                entraNumero(evt);
                if (vista.txtStockArticulos.getText().length() >= 16) {
                    evt.consume();
                }

            }
        });

        this.vista.txtUnidadesVentas.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {

                entraNumero(evt);
                if (vista.txtUnidadesVentas.getText().length() >= 16) {
                    evt.consume();
                }

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (AccionMVC.valueOf(e.getActionCommand())) {

            case btnAceptarClientes:
                try {
                    String codigo = this.vista.txtCodigoClientes.getText();
                    String nombre = this.vista.txtNombreClientes.getText();
                    String poblacion = this.vista.txtPoblacionClientes.getText();
                    this.modelo.InsertarCliente(codigo, nombre, poblacion);
                    this.vista.jTableClientes.setModel(this.modelo.getTablaCliente());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                break;
            case btnModificarClientes:
                try {
                    String codigo = this.vista.txtCodigoClientes.getText();
                    String nombre = this.vista.txtNombreClientes.getText();
                    String poblacion = this.vista.txtPoblacionClientes.getText();
                    this.modelo.ModificarCliente(codigo, nombre, poblacion);
                    this.vista.jTableClientes.setModel(this.modelo.getTablaCliente());
                } catch (Exception ex) {
                }

                break;
            case btnEliminarClientes:
                try {
                    String codigo = this.vista.txtCodigoClientes.getText();
                    String nombre = this.vista.txtNombreClientes.getText();
                    String poblacion = this.vista.txtPoblacionClientes.getText();
                    this.modelo.EliminarCliente(codigo);
                    this.vista.jTableClientes.setModel(this.modelo.getTablaCliente());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                break;

            case btnAceptarArticulos:
                try {
                    String codigo = this.vista.txtCodigoArticulos.getText();
                    String Denominacion = this.vista.txtDenominacionArticulos.getText();
                    int pvp = Integer.parseInt(this.vista.txtPVPArticulos.getText());
                    int stock = Integer.parseInt(this.vista.txtStockArticulos.getText());
                    this.modelo.InsertarArticulos(codigo, Denominacion, pvp, stock);
                    this.vista.jTableArticulos.setModel(this.modelo.getTablaArticulos());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case btnModificarArticulos:
                try {
                    String codigo = this.vista.txtCodigoArticulos.getText();
                    String denominacion = this.vista.txtDenominacionArticulos.getText();
                    int pvp = Integer.parseInt(this.vista.txtPVPArticulos.getText());
                    int stock = Integer.parseInt(this.vista.txtStockArticulos.getText());
                    this.modelo.ModificarArticulo(codigo, denominacion, pvp, stock);
                    this.vista.jTableArticulos.setModel(this.modelo.getTablaArticulos());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;

            case btnEliminarArticulos:
                try {
                    String codigo = this.vista.txtCodigoArticulos.getText();
                    this.modelo.EliminarArticulo(codigo);
                    this.vista.jTableArticulos.setModel(this.modelo.getTablaArticulos());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;

            case btnAceptarVentas:
                try {
                    String numventa = this.vista.txtNumeroVentas.getText();
                    String codCli = this.vista.txtClientesVentas.getText();
                    String codArt = this.vista.txtArticuloVentas.getText();
                    String fecha = this.vista.txtFechaVentas.getText();
                    int unidades = Integer.parseInt(this.vista.txtUnidadesVentas.getText());
                    this.modelo.InsertarVentas(numventa, codArt, codCli, fecha, unidades);
                    this.vista.jTableVentas.setModel(this.modelo.getTablaVentas());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case btnModificarVentas:
                try {
                    String numventa = this.vista.txtNumeroVentas.getText();
                    String codCli = this.vista.txtClientesVentas.getText();
                    String codArt = this.vista.txtArticuloVentas.getText();
                    String fecha = this.vista.txtFechaVentas.getText();
                    int unidades = Integer.parseInt(this.vista.txtUnidadesVentas.getText());
                    this.modelo.ModificarVentas(numventa, codCli, codArt, fecha, unidades);
                    this.vista.jTableVentas.setModel(this.modelo.getTablaVentas());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case btnEliminarVentas:
                try {
                    String numVenta = this.vista.txtNumeroVentas.getText();
                    this.modelo.EliminarVentas(numVenta);
                    this.vista.jTableVentas.setModel(this.modelo.getTablaVentas());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    public void ClientesMouseClicked(java.awt.event.MouseEvent evt) {

        filaseleccionada = this.vista.jTableClientes.getSelectedRow();
        String cod = (String) this.vista.jTableClientes.getValueAt(filaseleccionada, 0);
        String nom = (String) this.vista.jTableClientes.getValueAt(filaseleccionada, 1);
        String pob = (String) this.vista.jTableClientes.getValueAt(filaseleccionada, 2);
        this.vista.txtCodigoClientes.setText(cod);
        this.vista.txtNombreClientes.setText(nom);
        this.vista.txtPoblacionClientes.setText(pob);

    }

    public void ArticulosMouseClicked(java.awt.event.MouseEvent evt) {

        filaseleccionada = this.vista.jTableArticulos.getSelectedRow();
        String cod = (String) this.vista.jTableArticulos.getValueAt(filaseleccionada, 0);
        String den = (String) this.vista.jTableArticulos.getValueAt(filaseleccionada, 1);
        String pvp = (String) this.vista.jTableArticulos.getValueAt(filaseleccionada, 2);
        String stock = (String) this.vista.jTableArticulos.getValueAt(filaseleccionada, 3);
        this.vista.txtCodigoArticulos.setText(cod);
        this.vista.txtDenominacionArticulos.setText(den);
        this.vista.txtPVPArticulos.setText(pvp);
        this.vista.txtStockArticulos.setText(stock);
    }

    public void VentasMouseClicked(java.awt.event.MouseEvent evt) {

        filaseleccionada = this.vista.jTableVentas.getSelectedRow();
        String num = (String) this.vista.jTableVentas.getValueAt(filaseleccionada, 0);
        String codCli = (String) this.vista.jTableVentas.getValueAt(filaseleccionada, 1);
        String codArt = (String) this.vista.jTableVentas.getValueAt(filaseleccionada, 2);
        String fecha = (String) this.vista.jTableVentas.getValueAt(filaseleccionada, 3);
        String unid = (String) this.vista.jTableVentas.getValueAt(filaseleccionada, 4);
        this.vista.txtNumeroVentas.setText(num);
        this.vista.txtClientesVentas.setText(codCli);
        this.vista.txtArticuloVentas.setText(codArt);
        this.vista.txtFechaVentas.setText(fecha);
        this.vista.txtUnidadesVentas.setText(unid);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    //Para Numeros
    public void entraNumero(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if ((c < '0') || (c > '9')) {
            evt.consume();
        }
    }
}
