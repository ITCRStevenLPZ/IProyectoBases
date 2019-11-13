package basededatos;

public class Persona {

    int cedula;
    String nombres;
    String apellido1;
    String apellido2;
    String sexo;
    int dia;
    String mes;
    int ano;
    String tipoSangre;
    int ml_sangre;

    public Persona(int cedula, String nombres, String apellido1, String apellido2, String sexo, int dia, String mes, int ano, String tipoSangre, int ml_sangre) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.sexo = sexo;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.tipoSangre = tipoSangre;
        this.ml_sangre = ml_sangre;
    }
}
