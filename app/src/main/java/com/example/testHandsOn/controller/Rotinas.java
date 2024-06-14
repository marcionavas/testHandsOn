/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.testHandsOn.controller;

import com.example.testHandsOn.model.Funcionario;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author kali
 */
public class Rotinas {
    
    private final List<Funcionario> listaFuncionarios = new ArrayList<>();    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
        
    
    //Método construtor para adicionar todos os funcionários e seus respectivos dados a lista "listaFuncionarios"
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
    
    //Remoção de funcionário da lista usando abordagem Java Streams (Java 8+)
    public void rmFuncionario(String nome) {        
        
        Optional<Funcionario> funDeletar = listaFuncionarios.stream()
                .filter(funcionario -> funcionario.getNome().equals(nome))
                .findFirst();

        funDeletar.ifPresent(listaFuncionarios::remove);
    }
    
    //Imprime a tabela de funcionários
    public void impFuncionarios(String titulo){
        
        System.out.println("\n" + titulo);
        System.out.println("------------");      
            
            
            listaFuncionarios.forEach(System.out::println);
    }
    
    //Realiza aumento do salário dos funcionários
    public void atualizarSalario(String aumento){
        
        for (Funcionario funcionario : listaFuncionarios) {            
            funcionario.setSalario(funcionario.getSalario().
                    add(funcionario.getSalario().multiply((new BigDecimal(aumento.
                            replace("%", "")).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP)))));
        }        
    }
    
    //Função MAP para agrupar os funcionários por função; Retorna um MAP desse agrupamento de funcionários por função chamado "funcionariosPorFuncao"
    private Map<String,List<Funcionario>> agruparFuncionarioPorFuncao(){
        
        Map<String, List<Funcionario>> funcionariosPorFuncao = listaFuncionarios.stream()
            .collect(Collectors.groupingBy(Funcionario::getFuncao));
        
        return funcionariosPorFuncao;       
    }
    
    //Imprime o agrupamento de funcionários com base no MAP retornado pela função "agruparFuncionariosPorFuncao"
    public void impAgrupadosFuncao(){
        
        System.out.println("\nFuncionários Agrupados Por Função:");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        agruparFuncionarioPorFuncao().forEach((funcao, funcionarios) -> {
            System.out.println("Função: " + funcao);
            funcionarios.forEach(System.out::println);
            System.out.println("---------------------------------------------------------------------------------------------------------");
        });
    }
    
    

    
}
