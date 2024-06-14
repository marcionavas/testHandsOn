/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.testHandsOn.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author kali
 */
public class Funcionario extends Pessoa {
    
    private BigDecimal salario;
    private String funcao;
    
    public Funcionario(String nome, LocalDate dtNascimento, BigDecimal salario, String funcao){
        this.setNome(nome);
        this.setDtNacimento(dtNascimento);
        this.salario = salario;
        this.funcao = funcao;        
    }
    
    public Funcionario(){}    
    

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
