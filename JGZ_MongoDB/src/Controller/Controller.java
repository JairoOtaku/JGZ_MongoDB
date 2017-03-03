package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import View.View;
import Model.modelo;

// @author Jairo_Otaku
public class Controller implements ActionListener, MouseListener {

    View vista;
    modelo modelo;
    int filaseleccionada = -1;

    public Controller(View vista) {
        this.vista = vista;
    }

    public enum AccionMVC {
        btnClientesOk,
        btnrClientesMod,
        btnClientesDel,
        btnArticulosOk,
        btnArticulosMod,
        btnArticulosDel,
        btnVentasOk,
        btnVentasMod,
        btnVentasDel
    }

    public void iniciar() {

        try {
            //cargamos los modelos de las tablas
            vista.jTableClientes.setModel(modelo.getTablaCliente());
            vista.jTableArticulos.setModel(modelo.getTablaArticulos());
            vista.jTableVentas.setModel(modelo.getTablaVentas());
            //despues hacemos visible la vista
            vista.setVisible(true);
        } catch (Exception e) {
        }

        this.vista.btnAceptarClientes.setActionCommand("btnClientesOk");
        this.vista.btnAceptarClientes.addActionListener(this);

        this.vista.btnModificarClientes.setActionCommand("btnrClientesMod");
        this.vista.btnModificarClientes.addActionListener(this);

        this.vista.btnEliminarClientes.setActionCommand("btnClientesDel");
        this.vista.btnEliminarClientes.addActionListener(this);

        this.vista.btnAceptarArticulos.setActionCommand("btnArticulosOk");
        this.vista.btnAceptarArticulos.addActionListener(this);

        this.vista.btnModificarArticulos.setActionCommand("btnArticulosMod");
        this.vista.btnModificarArticulos.addActionListener(this);

        this.vista.btnEliminarArticulos.setActionCommand("btnArticulosDel");
        this.vista.btnEliminarArticulos.addActionListener(this);

        this.vista.btnAceptarVentas.setActionCommand("btnVentasOk");
        this.vista.btnAceptarVentas.addActionListener(this);

        this.vista.btnModificarVentas.setActionCommand("btnVentasMod");
        this.vista.btnModificarVentas.addActionListener(this);

        this.vista.btnEliminarVentas.setActionCommand("btnVentasDel");
        this.vista.btnEliminarVentas.addActionListener(this);

        /*Mouse Listener de solo numeros para las tablas*/
        this.vista.jTableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eventoClientes(evt);
            }
        });

        this.vista.jTableArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eventoArticulos(evt);
            }
        });

        this.vista.jTableVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eventoVentas(evt);
            }
        });

        /*Mouse Listener de solo numeros para los textfield*/
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
            case btnClientesOk:
                try {
                    String codigo = vista.txtCodigoClientes.getText();
                    String nombre = vista.txtNombreClientes.getText();
                    String poblacion = vista.txtPoblacionClientes.getText();
                    modelo.InsertarCliente(codigo, nombre, poblacion);
                    vista.jTableClientes.setModel(modelo.getTablaCliente());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case btnrClientesMod:
                try {
                    String codigo = vista.txtCodigoClientes.getText();
                    String nombre = vista.txtNombreClientes.getText();
                    String poblacion = vista.txtPoblacionClientes.getText();
                    modelo.ModificarCliente(codigo, nombre, poblacion);
                    vista.jTableClientes.setModel(modelo.getTablaCliente());
                } catch (Exception ex) {
                }
                break;
            case btnClientesDel:
                try {
                    String codigo = vista.txtCodigoClientes.getText();
                    String nombre = vista.txtNombreClientes.getText();
                    String poblacion = vista.txtPoblacionClientes.getText();
                    modelo.EliminarCliente(codigo);
                    vista.jTableClientes.setModel(modelo.getTablaCliente());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                break;
            case btnArticulosOk:
                try {
                    String codigo = vista.txtCodigoArticulos.getText();
                    String Denominacion = vista.txtDenominacionArticulos.getText();
                    int pvp = Integer.parseInt(vista.txtPVPArticulos.getText());
                    int stock = Integer.parseInt(vista.txtStockArticulos.getText());
                    modelo.InsertarArticulos(codigo, Denominacion, pvp, stock);
                    vista.jTableArticulos.setModel(modelo.getTablaArticulos());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case btnArticulosMod:
                try {
                    String codigo = vista.txtCodigoArticulos.getText();
                    String denominacion = vista.txtDenominacionArticulos.getText();
                    int pvp = Integer.parseInt(vista.txtPVPArticulos.getText());
                    int stock = Integer.parseInt(vista.txtStockArticulos.getText());
                    modelo.ModificarArticulo(codigo, denominacion, pvp, stock);
                    vista.jTableArticulos.setModel(modelo.getTablaArticulos());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case btnArticulosDel:
                try {
                    String codigo = vista.txtCodigoArticulos.getText();
                    modelo.EliminarArticulo(codigo);
                    vista.jTableArticulos.setModel(modelo.getTablaArticulos());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case btnVentasOk:
                try {
                    String numventa = vista.txtNumeroVentas.getText();
                    String codCli = vista.txtClientesVentas.getText();
                    String codArt = vista.txtArticuloVentas.getText();
                    String fecha = vista.txtFechaVentas.getText();
                    int unidades = Integer.parseInt(vista.txtUnidadesVentas.getText());
                    modelo.InsertarVentas(numventa, codArt, codCli, fecha, unidades);
                    vista.jTableVentas.setModel(modelo.getTablaVentas());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case btnVentasMod:
                try {
                    String numventa = vista.txtNumeroVentas.getText();
                    String codCli = vista.txtClientesVentas.getText();
                    String codArt = vista.txtArticuloVentas.getText();
                    String fecha = vista.txtFechaVentas.getText();
                    int unidades = Integer.parseInt(vista.txtUnidadesVentas.getText());
                    modelo.ModificarVentas(numventa, codCli, codArt, fecha, unidades);
                    vista.jTableVentas.setModel(modelo.getTablaVentas());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case btnVentasDel:
                try {
                    String numVenta = vista.txtNumeroVentas.getText();
                    modelo.EliminarVentas(numVenta);
                    vista.jTableVentas.setModel(modelo.getTablaVentas());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    public void eventoClientes(java.awt.event.MouseEvent evt) {
        filaseleccionada = vista.jTableClientes.getSelectedRow();
        String cod = (String) vista.jTableClientes.getValueAt(filaseleccionada, 0);
        String nom = (String) vista.jTableClientes.getValueAt(filaseleccionada, 1);
        String pob = (String) vista.jTableClientes.getValueAt(filaseleccionada, 2);
        vista.txtCodigoClientes.setText(cod);
        vista.txtNombreClientes.setText(nom);
        vista.txtPoblacionClientes.setText(pob);
    }

    public void eventoArticulos(java.awt.event.MouseEvent evt) {
        filaseleccionada = vista.jTableArticulos.getSelectedRow();
        String cod = (String) vista.jTableArticulos.getValueAt(filaseleccionada, 0);
        String den = (String) vista.jTableArticulos.getValueAt(filaseleccionada, 1);
        String pvp = (String) vista.jTableArticulos.getValueAt(filaseleccionada, 2);
        String stock = (String) vista.jTableArticulos.getValueAt(filaseleccionada, 3);
        vista.txtCodigoArticulos.setText(cod);
        vista.txtDenominacionArticulos.setText(den);
        vista.txtPVPArticulos.setText(pvp);
        vista.txtStockArticulos.setText(stock);
    }

    public void eventoVentas(java.awt.event.MouseEvent evt) {
        filaseleccionada = vista.jTableVentas.getSelectedRow();
        String num = (String) vista.jTableVentas.getValueAt(filaseleccionada, 0);
        String codCli = (String) vista.jTableVentas.getValueAt(filaseleccionada, 1);
        String codArt = (String) vista.jTableVentas.getValueAt(filaseleccionada, 2);
        String fecha = (String) vista.jTableVentas.getValueAt(filaseleccionada, 3);
        String unid = (String) vista.jTableVentas.getValueAt(filaseleccionada, 4);
        vista.txtNumeroVentas.setText(num);
        vista.txtClientesVentas.setText(codCli);
        vista.txtArticuloVentas.setText(codArt);
        vista.txtFechaVentas.setText(fecha);
        vista.txtUnidadesVentas.setText(unid);
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

    public void entraNumero(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if ((c < '0') || (c > '9')) {
            evt.consume();
        }
    }
}
