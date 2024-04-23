package com.example.demo.DTO;

public class LoginDTO {

    private String cedula;
    private String contrasena;

    public LoginDTO(String cedula, String contrasena) {
        this.cedula = cedula;
        this.contrasena = contrasena;
    }

    public LoginDTO(String cedula) {
        this.cedula = cedula;
    }

    public LoginDTO() {
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "cedula='" + cedula + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
    
}
