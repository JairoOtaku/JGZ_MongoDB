package Model;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.UpdateResult;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

//@author Jairo_Otaku
public class modelo extends database {

    database db = new database();

    public DefaultTableModel getTablaCliente() {
        DefaultTableModel tablemodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false    
                return false;
            }
        };

        String[] columNames = {"Codigo", "Nombre", "Población"};
        MongoDatabase dbm = db.databaseMongo();
        MongoCollection<Document> collection = dbm.getCollection("Clientes");
        FindIterable<Document> find = collection.find();
        MongoCursor<Document> cursor = find.iterator();

        //se crea una matriz con tantas filas y columnas que necesite
        Object[][] data = new String[Integer.parseInt(String.valueOf(collection.count()))][3];
        int i = 0;

        while (cursor.hasNext()) {
            Document doc = cursor.next();
            data[i][0] = String.valueOf(doc.get("CODCLI"));
            data[i][1] = String.valueOf(doc.get("NOMBRE"));
            data[i][2] = String.valueOf(doc.get("POBLACION"));
            i++;
        }
        tablemodel.setDataVector(data, columNames);

        return tablemodel;
    }

    public DefaultTableModel getTablaArticulos() {
        DefaultTableModel tablemodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false    
                return false;
            }
        };

        String[] columNames = {"Codigo", "Denominacion", "PVP", "STOCK"};

        MongoDatabase dbm = db.databaseMongo();
        MongoCollection<Document> collection = dbm.getCollection("Articulos");
        FindIterable<Document> find = collection.find();
        MongoCursor<Document> cursor = find.iterator();

        //se crea una matriz con tantas filas y columnas que necesite
        Object[][] data = new String[Integer.parseInt(String.valueOf(collection.count()))][4];
        int i = 0;

        while (cursor.hasNext()) {
            Document doc = cursor.next();
            data[i][0] = String.valueOf(doc.get("CODART"));
            data[i][1] = String.valueOf(doc.get("DENOMINACION"));
            data[i][2] = String.valueOf(doc.get("PVP"));
            data[i][3] = String.valueOf(doc.get("STOCK"));
            i++;
        }
        //se añade la matriz de datos en el DefaultTableModel
        tablemodel.setDataVector(data, columNames);

        return tablemodel;
    }

    public DefaultTableModel getTablaVentas() {
        DefaultTableModel tablemodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false    
                return false;
            }
        };

        String[] columNames = {"Número", "Cliente", "Artículo", "Fecha", "Unidades"};

        MongoDatabase dbm = db.databaseMongo();
        MongoCollection<Document> collection = dbm.getCollection("Ventas");
        FindIterable<Document> find = collection.find();
        MongoCursor<Document> cursor = find.iterator();

        //se crea una matriz con tantas filas y columnas que necesite
        Object[][] data = new String[Integer.parseInt(String.valueOf(collection.count()))][5];
        int i = 0;
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            data[i][0] = String.valueOf(doc.get("NUMVENTA"));
            data[i][1] = String.valueOf(doc.get("CODCLI"));
            data[i][2] = String.valueOf(doc.get("CODART"));
            data[i][3] = String.valueOf(doc.get("FECHA"));
            data[i][4] = String.valueOf(doc.get("UNIDADES"));
            i++;
        }
        //se añade la matriz de datos en el DefaultTableModel
        tablemodel.setDataVector(data, columNames);
        return tablemodel;
    }

    public void InsertarCliente(String codigo, String nombre, String poblacion) {
        try {
            MongoCollection<Document> coleccion = db.databaseMongo().getCollection("Clientes");
            Document cliente = new Document();
            cliente.put("CODCLI", codigo);
            cliente.put("NOMBRE", nombre);
            cliente.put("POBLACION", poblacion);
            coleccion.insertOne(cliente);
        } catch (Exception e) {
        }
    }

    public void InsertarArticulos(String codigo, String denominacion, int pvp, int stock) {
        try {
            MongoCollection<Document> coleccion = db.databaseMongo().getCollection("Articulos");
            Document art = new Document();
            art.put("CODART", codigo);
            art.put("DENOMINACION", denominacion);
            art.put("PVP", pvp);
            art.put("STOCK", stock);
            coleccion.insertOne(art);
        } catch (Exception e) {
        }
    }

    public void InsertarVentas(String numero, String cliente, String articulo, String fecha, int unidades) {
        try {
            MongoCollection<Document> coleccion = db.databaseMongo().getCollection("Ventas");
            Document ventas = new Document();
            ventas.put("NUMVENTA", numero);
            ventas.put("CODCLI", cliente);
            ventas.put("CODART", articulo);
            ventas.put("FECHA", fecha);
            ventas.put("UNIDADES", unidades);
            coleccion.insertOne(ventas);
        } catch (Exception e) {
        }
    }

    public boolean ModificarCliente(String codigo, String nombre, String poblacion) {
        try {
            MongoCollection<Document> coleccion = db.databaseMongo().getCollection("Clientes");
            UpdateResult updateResult = coleccion.updateOne(eq("CODCLI", codigo), new Document("$set", new Document("CODCLI", codigo).append("NOMBRE", nombre).append("POBLACION", poblacion)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean ModificarArticulo(String codigo, String denominacion, int pvp, int stock) {
        try {
            MongoCollection<Document> coleccion = db.databaseMongo().getCollection("Articulos");
            UpdateResult updateResult = coleccion.updateOne(eq("CODART", codigo), new Document("$set", new Document("DENOMINACION", denominacion).append("PVP", pvp).append("STOCK", stock)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean ModificarVentas(String numventa, String codCli, String codArt, String fecha, int unidades) {
        try {
            MongoCollection<Document> coleccion = db.databaseMongo().getCollection("Ventas");
            UpdateResult updateResult = coleccion.updateOne(eq("NUMVENTA", numventa), new Document("$set", new Document("CODCLI", codCli).append("CODART", codArt).append("FECHA", fecha).append("UNIDADES", unidades)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean EliminarCliente(String codigo) {
        try {
            MongoCollection<Document> coleccion = db.databaseMongo().getCollection("Clientes");
            MongoCollection<Document> coleccionVentas = db.databaseMongo().getCollection("Ventas");
            coleccion.deleteOne(eq("CODCLI", codigo));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean EliminarArticulo(String codigo) {
        try {
            MongoCollection<Document> coleccion = db.databaseMongo().getCollection("Articulos");
            MongoCollection<Document> coleccionVentas = db.databaseMongo().getCollection("Ventas");
            coleccionVentas.deleteMany(eq("CODART", String.valueOf(codigo)));
            coleccion.deleteOne(eq("CODART", codigo));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean EliminarVentas(String numVenta) {
        try {
            MongoCollection<Document> coleccion = db.databaseMongo().getCollection("Ventas");
            coleccion.deleteOne(eq("NUMVENTA", numVenta));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
