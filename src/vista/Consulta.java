package vista;

import dao.DaoEstudiante;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Estudiante;

/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame {

    public JLabel lblCarnet, lblNombres, lblEdad, lblApellidos, lblUniversidad, lblEstado;
    public JTextField carnet, nombre, edad, apellidos;
    public JComboBox universidad;
    ButtonGroup estado = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;
    public JPanel table;

    public JButton insertar, actualizar, eliminar, limpiar, buscar;
    private static final int ANCHOC = 130, ALTOC = 30;
    DefaultTableModel tm;

    public Consulta() {
        super("Inscripciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        agregarLabels();
        formulario();
        llenarTabla();

        Container container = getContentPane();
        container.add(lblCarnet);
        container.add(lblNombres);
        container.add(lblEdad);
        container.add(lblApellidos);
        container.add(lblUniversidad);
        container.add(lblEstado);
        container.add(carnet);
        container.add(nombre);
        container.add(apellidos);
        container.add(universidad);
        container.add(edad);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(600, 900);
        eventos();
    }

    public final void agregarLabels() {
        lblCarnet = new JLabel("Carnet");
        lblNombres = new JLabel("Nombre");
        lblApellidos = new JLabel("Apellidos");
        lblEdad = new JLabel("Edad");
        lblEstado = new JLabel("Estado");
        lblUniversidad = new JLabel("Universidad");
        lblCarnet.setBounds(10, 10, ANCHOC, ALTOC);
        lblNombres.setBounds(10, 60, ANCHOC, ALTOC);
        lblApellidos.setBounds(10, 100, ANCHOC, ALTOC);
        lblEdad.setBounds(10, 140, ANCHOC, ALTOC);
        lblUniversidad.setBounds(10, 200, ANCHOC, ALTOC);
        lblEstado.setBounds(10, 240, ANCHOC, ALTOC);

    }

    public final void formulario() {
        carnet = new JTextField();
        universidad = new JComboBox();
        nombre = new JTextField();
        apellidos = new JTextField();
        edad = new JTextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");
        table = new JPanel();
        universidad.addItem("UCA");
        universidad.addItem("UDB");
        universidad.addItem("UES");
        universidad.addItem("UFG");
        universidad.addItem("UGB");

        estado = new ButtonGroup();
        estado.add(si);
        estado.add(no);

        carnet.setBounds(140, 10, ANCHOC, ALTOC);
        nombre.setBounds(140, 60, ANCHOC, ALTOC);
        apellidos.setBounds(140, 100, ANCHOC, ALTOC);
        edad.setBounds(140, 140, ANCHOC, ALTOC);
        universidad.setBounds(140, 200, ANCHOC, ALTOC);
        si.setBounds(160, 240, ANCHOC, ALTOC);
        no.setBounds(270, 240, ANCHOC, ALTOC);

        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 300, ANCHOC, ALTOC);
        actualizar.setBounds(150, 300, ANCHOC, ALTOC);
        eliminar.setBounds(300, 300, ANCHOC, ALTOC);
        limpiar.setBounds(450, 300, ANCHOC, ALTOC);

        resultados = new JTable();
        table.setBounds(10, 340, 500, 400);
        table.add(new JScrollPane(resultados));
    }

    public void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("Carnet");
        tm.addColumn("Nombre");
        tm.addColumn("Apellidos");
        tm.addColumn("Edad");
        tm.addColumn("Universidad");
        tm.addColumn("Estado");

        DaoEstudiante de = new DaoEstudiante();
        ArrayList<Estudiante> filtros = de.readAll();
        for (Estudiante e : filtros) {
            tm.addRow(new Object[]{e.getCarnet(), e.getNombre(), e.getApellido(), e.getEdad(), e.getUniversidad(), e.isEstado()});
        }
        resultados.setModel(tm);
    }

    public void eventos() {

        insertar.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {
                ArrayList<Estudiante> all = new ArrayList<>();
                DaoEstudiante de = new DaoEstudiante();
                all = de.readAll();
                Estudiante est = new Estudiante(carnet.getText(), nombre.getText(), apellidos.getText(), Integer.parseInt(edad.getText()), universidad.getSelectedItem().toString(), true);
                for (Estudiante estv2 : all) {
                    if (est.getCarnet() == estv2.getCarnet()) {
                        JOptionPane.showMessageDialog(null, "Este estudiante ya existe!.");
                        limpiarCampos();
                        return;
                    }
                }
                if (no.isSelected()) {

                    est.setEstado(false);

                }

                if (de.create(est)) {

                    JOptionPane.showMessageDialog(null, "Estudiante inscrito con exito.");

                    limpiarCampos();

                    llenarTabla();

                } else {

                    JOptionPane.showMessageDialog(null, "Ocurrió un problema al momento de inscribir al estudiante.");

                }

            }

        });
        actualizar.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {
               // int flag = 0;
                DaoEstudiante de = new DaoEstudiante();
                Estudiante est = new Estudiante(carnet.getText(), nombre.getText(), apellidos.getText(), Integer.parseInt(edad.getText()), universidad.getSelectedItem().toString(), true);

                if(de.update(est)){
                    
                    JOptionPane.showMessageDialog(null, "Estudiante actualizado con exito");
                    limpiarCampos();
                    llenarTabla();
                    
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Este estudiante no exite");
                }
                
            }
        });
        eliminar.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                DaoEstudiante de = new DaoEstudiante();

                if (de.delete(carnet.getText())) {

                    JOptionPane.showMessageDialog(null, "Estudiante eliminado con éxito.");

                    limpiarCampos();

                    llenarTabla();

                } else {

                    JOptionPane.showMessageDialog(null, "Ocurrió un problema al momento de eliminar el estudiante.");

                }

            }

        });

        buscar.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                DaoEstudiante de = new DaoEstudiante();

                Estudiante est = de.read(carnet.getText());

                if (est == null) {

                    JOptionPane.showMessageDialog(null, "El estudiante buscado no se ha encontrado.");

                } else {

                    carnet.setText(est.getCarnet());
                    nombre.setText(est.getNombre());
                    apellidos.setText(est.getApellido());
                    edad.setText(Integer.toString(est.getEdad()));
                    universidad.setSelectedItem(est.getUniversidad());

                    if (est.isEstado()) {

                        si.setSelected(true);

                    } else {

                        no.setSelected(true);

                    }

                }

            }

        });

        limpiar.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                limpiarCampos();

            }

        });

    }

    public void limpiarCampos() {

        carnet.setText("");
        nombre.setText("");
        apellidos.setText("");
        edad.setText("");
        universidad.setSelectedItem("UCA");

    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override

            public void run() {

                new Consulta().setVisible(true);

            }

        });

    }
}