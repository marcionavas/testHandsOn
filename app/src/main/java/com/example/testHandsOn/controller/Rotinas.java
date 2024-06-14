/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.testHandsOn.controller;

import com.example.testHandsOn.model.Funcionario;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 *
 * @author kali
 */
public class Rotinas {
    
    private final List<Funcionario> listaFuncionarios = new ArrayList<>();
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    
    
    
    public Rotinas() {    
                
        listaFuncionarios.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", formatter), new BigDecimal("2009.44"), "Operador"));
        listaFuncionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", formatter), new BigDecimal("2284.38"), "Operador"));
        listaFuncionarios.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", formatter), new BigDecimal("9836.14"), "Coordenador"));
        listaFuncionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", formatter), new BigDecimal("19119.88"), "Diretor"));
        listaFuncionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", formatter), new BigDecimal("2234.68"), "Recepcionista"));
        listaFuncionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", formatter), new BigDecimal("1582.72"), "Operador"));
        listaFuncionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", formatter), new BigDecimal("4071.84"), "Contador"));
        listaFuncionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", formatter), new BigDecimal("3017.45"), "Gerente"));
        listaFuncionarios.add(new Funcionario("Heloísa", LocalDate.parse("24/05/2003", formatter), new BigDecimal("1606.85"), "Eletricista"));
        listaFuncionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", formatter), new BigDecimal("2799.93"), "Gerente"));
        
    }
    
    public String formatValores(BigDecimal num){
        
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
    
    public String formatDt(LocalDate data) {
        return data.format(formatter);
    }
    
    //Remoção de funcionário da lista usando abordagem Java Streams (Java 8+)
    public void rmFuncionario(String nome) {        
        
        Optional<Funcionario> personToDelete = listaFuncionarios.stream()
                .filter(funcionario -> funcionario.getNome().equals(nome))
                .findFirst();

        personToDelete.ifPresent(listaFuncionarios::remove);
    }
    
    public void imprimirFuncionarios(){
        //System.out.println("\nFormatted printing:");
        System.out.println("\nLista de Funcionários:");
        System.out.println("------------");
        for (Funcionario funcionario : listaFuncionarios) {
            System.out.println(String.format("Nome: %-15s Data de Nascimento: %-15s Salário: %-15s Função: %s", funcionario.getNome(), formatDt(funcionario.getDtNacimento()),
                    formatValores(funcionario.getSalario()), funcionario.getFuncao()));
        }
    }
    
    public void atualizarSalario(String aumento){
        
                
        for (Funcionario funcionario : listaFuncionarios) {
            
            funcionario.setSalario(funcionario.getSalario().add(funcionario.getSalario().multiply(new BigDecimal(aumento.replace("%", "")))));
        }
        
    }
    
    

    
}
