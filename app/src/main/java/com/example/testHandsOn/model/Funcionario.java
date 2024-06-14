/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.testHandsOn.model;

import com.example.testHandsOn.controller.Rotinas;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author kali
 */
public class Funcionario extends Pessoa {
    
    private BigDecimal salario;
    private String funcao;
    
    //Construtor da classe Funcionários para inicializar cada funcionário com seus respectivos dados passados por parâmetro    
    public Funcionario(String nome, LocalDate dtNascimento, BigDecimal salario, String funcao){
        this.setNome(nome);
        this.setDtNacimento(dtNascimento);
        this.salario = salario;
        this.funcao = funcao;        
    }
    
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
    
    public int getIdade() {
        return Period.between(this.getDtNacimento(), LocalDate.now()).getYears();
    }   
    
    //@Override do método toString da classe Funcionário. Dessa forma quando necessário realizar alguma impressão na tela,
    //chamando a função: System.out::println ou System.out.printlm(funcionário.toString()), os dados serão impressos e já formatados
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("Nome: %-15s Data de Nascimento: %-15s Salário: %-15s Função: %s",
                this.getNome(), this.getDtNacimento().format(formatter), Rotinas.formatValores(this.salario), this.funcao);
    }
}
