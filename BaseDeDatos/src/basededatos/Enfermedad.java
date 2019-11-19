/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

public class Enfermedad {
    String nombre;
    String gravedad;
    int ano_padecimiento;

    public Enfermedad(String nombre, String gravedad, int ano_padecimiento) {
        this.nombre = nombre;
        this.gravedad = gravedad;
        this.ano_padecimiento = ano_padecimiento;
    }
}
