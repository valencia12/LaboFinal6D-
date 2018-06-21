import dao.DaoEstudiante;
import modelo.Estudiante;
import vista.Consulta;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LN710Q
 */
public class Main {
     public static void main(String[] args) {

       //DaoEstudiante estudiantes= new DaoEstudiante();
       //estudiantes.create(new Estudiante("00097017", "Alejandro", "Olmedo", 19, "Uca", true));
       new Consulta().setVisible(true);
       
    }
}