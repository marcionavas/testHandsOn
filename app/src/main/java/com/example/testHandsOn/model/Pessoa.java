/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.testHandsOn.model;

import java.time.LocalDate;

/**
 *
 * @author Marcio M Navas Filho
 */
public class Pessoa {
    
    private String nome;
    private LocalDate dtNacimento;
    
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDtNacimento() {
        return dtNacimento;
    }

    public void setDtNacimento(LocalDate dtNacimento) {
        this.dtNacimento = dtNacimento;
    }
}
