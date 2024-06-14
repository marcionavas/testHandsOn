/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.testHandsOn.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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
    
    private String formatValores(BigDecimal num){
        
        DecimalFormatSymbols symbols;
        
        // Definir símbolos personalizados para separadores de milhar e decimal
        symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');

        // Criar o formato desejado
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);        

        // Exibir o número formatado
        //System.out.println(decimalFormat.format(num));
        
        return decimalFormat.format(num);        
    }
    
    public int getIdade() {
        return Period.between(this.getDtNacimento(), LocalDate.now()).getYears();
    }
    
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("Nome: %-15s Data de Nascimento: %-15s Salário: %-15s Função: %s",
                this.getNome(), this.getDtNacimento().format(formatter), formatValores(this.salario), this.funcao);
    }
}
