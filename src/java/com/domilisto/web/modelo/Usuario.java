package com.domilisto.web.modelo;

import java.util.Date;

public class Usuario {
    private int id;
    private String nombre;
    private String correo;
    private String contraseña;
    private String rol;
    private String direccion;
    private int edad;
    private Date fechaNacimiento;
    private String numero;  // Cambié de 'telefono' a 'numero'

    // Constructor vacío
    public Usuario() {}

    // Constructor con los parámetros principales
    public Usuario(String nombre, String correo, String contraseña, String rol, 
                   String direccion, int edad, Date fechaNacimiento, String numero) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
        this.direccion = direccion;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.numero = numero;  // Cambié de 'telefono' a 'numero'
    }

    // Getters y setters para cada campo

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNumero() {  // Cambié de 'telefono' a 'numero'
        return numero;
    }

    public void setNumero(String numero) {  // Cambié de 'telefono' a 'numero'
        this.numero = numero;
    }
}

