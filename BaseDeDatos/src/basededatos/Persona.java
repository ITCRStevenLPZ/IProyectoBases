package basededatos;

import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Persona {
    boolean diabetes;
    ArrayList<Enfermedad> enfermedades;
    SimpleIntegerProperty Cedula;
    SimpleStringProperty Nombres;
    SimpleStringProperty Apellido1;
    SimpleStringProperty Apellido2;
    SimpleStringProperty Sexo;
    SimpleStringProperty Provincia;
    SimpleStringProperty Canton;
    SimpleStringProperty Distrito;
    SimpleStringProperty Direccion;
    SimpleIntegerProperty Dia;
    SimpleStringProperty Mes;
    SimpleIntegerProperty Ano;
    SimpleStringProperty Tipo_Sangre;
    SimpleIntegerProperty Cant_Sangre;
    SimpleIntegerProperty Telefono1;
    SimpleIntegerProperty Telefono2;

    public int getCedula() {
        return Cedula.get();
    }

    public void setCedula(int Cedula) {
        this.Cedula.set(Cedula);
    }

    public Persona(int Cedula, String Nombres, String Apellido1, String Apellido2, String Sexo, String Provincia, String Canton, String Distrito, String Direccion, int Dia, String Mes, int Ano, String Tipo_Sangre, int Cant_Sangre, int Telefono1, int Telefono2) {
        this.Cedula = new SimpleIntegerProperty(Cedula);
        this.Nombres = new SimpleStringProperty(Nombres);
        this.Apellido1 = new SimpleStringProperty(Apellido1);
        this.Apellido2 = new SimpleStringProperty(Apellido2);
        this.Sexo = new SimpleStringProperty(Sexo);
        this.Provincia = new SimpleStringProperty(Provincia);
        this.Canton = new SimpleStringProperty(Canton);
        this.Distrito = new SimpleStringProperty(Distrito);
        this.Direccion = new SimpleStringProperty(Direccion);
        this.Dia = new SimpleIntegerProperty(Dia);
        this.Mes = new SimpleStringProperty(Mes);
        this.Ano = new SimpleIntegerProperty(Ano);
        this.Tipo_Sangre = new SimpleStringProperty(Tipo_Sangre);
        this.Cant_Sangre = new SimpleIntegerProperty(Cant_Sangre);
        this.Telefono1 = new SimpleIntegerProperty(Telefono1);
        this.Telefono2 = new SimpleIntegerProperty(Telefono2);
        this.diabetes=false;
    }

    public String getNombres() {
        return Nombres.get();
    }

    public void setNombres(String Nombres) {
        this.Nombres.set(Nombres);
    }

    public String getApellido1() {
        return Apellido1.get();
    }

    public void setApellido1(String Apellido1) {
        this.Apellido1.set(Apellido1);
    }

    public String getApellido2() {
        return Apellido2.get();
    }

    public void setApellido2(String Apellido2) {
        this.Apellido2.set(Apellido2);
    }

    public String getSexo() {
        return Sexo.get();
    }

    public void setSexo(String Sexo) {
        this.Sexo.set(Sexo);
    }

    public String getProvincia() {
        return Provincia.get();
    }

    public void setProvincia(String Provincia) {
        this.Provincia.set(Provincia);
    }

    public String getCanton() {
        return Canton.get();
    }

    public void setCanton(String Canton) {
        this.Canton.set(Canton);
    }

    public String getDistrito() {
        return Distrito.get();
    }

    public void setDistrito(String Distrito) {
        this.Distrito.set(Distrito);
    }

    public String getDireccion() {
        return Direccion.get();
    }

    public void setDireccion(String Direccion) {
        this.Direccion.set(Direccion);
    }

    public int getDia() {
        return Dia.get();
    }

    public void setDia(int Dia) {
        this.Dia.set(Dia);
    }

    public String getMes() {
        return Mes.get();
    }

    public void setMes(String Mes) {
        this.Mes.set(Mes);
    }

    public int getAno() {
        return Ano.get();
    }

    public void setAno(int Ano) {
        this.Ano.set(Ano);
    }

    public String getTipo_Sangre() {
        return Tipo_Sangre.get();
    }

    public void setTipo_Sangre(String Tipo_Sangre) {
        this.Tipo_Sangre.set(Tipo_Sangre);
    }

    public int getCant_Sangre() {
        return Cant_Sangre.get();
    }

    public void setCant_Sangre(int Cant_Sangre) {
        this.Cant_Sangre.set(Cant_Sangre);
    }

    public int getTelefono1() {
        return Telefono1.get();
    }

    public void setTelefono1(int Telefono1) {
        this.Telefono1.set(Telefono1);
    }

    public int getTelefono2() {
        return Telefono2.get();
    }

    public void setTelefono2(int Telefono2) {
        this.Telefono2.set(Telefono2);
    }

}
