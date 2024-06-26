/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.testHandsOn.controller;

import com.example.testHandsOn.model.Funcionario;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Marcio M. Navas Filho
 */
public class Rotinas {
    
    private final List<Funcionario> listaFuncionarios = new ArrayList<>();
    private ArrayList<Integer> meses = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    //A variavel mesesMap é inicializada como uma nova instância HashMap
    private static final Map<String, Integer> mesesMap = new HashMap<>();    
    
    //Bloco static é utilizado para é usado para preencher mesesMap com pares chave-valor
    static {
        mesesMap.put("janeiro", Month.JANUARY.getValue());
        mesesMap.put("fevereiro", Month.FEBRUARY.getValue());
        mesesMap.put("março", Month.MARCH.getValue());
        mesesMap.put("abril", Month.APRIL.getValue());
        mesesMap.put("maio", Month.MAY.getValue());
        mesesMap.put("junho", Month.JUNE.getValue());
        mesesMap.put("julho", Month.JULY.getValue());
        mesesMap.put("agosto", Month.AUGUST.getValue());
        mesesMap.put("setembro", Month.SEPTEMBER.getValue());
        mesesMap.put("outubro", Month.OCTOBER.getValue());
        mesesMap.put("novembro", Month.NOVEMBER.getValue());
        mesesMap.put("dezembro", Month.DECEMBER.getValue());
    }       
    
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
    
    public static String formatValores(BigDecimal num){
        
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
    
    //Remoção de funcionário da lista usando abordagem Java Streams (Java 8+)
    public void rmFuncionario(String nome) {        
        
        Optional<Funcionario> funDeletar = listaFuncionarios.stream()
                .filter(funcionario -> funcionario.getNome().equals(nome))
                .findFirst();

        funDeletar.ifPresent(listaFuncionarios::remove);
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
    
    //Função com declaração VARARGS, onde é possível passar os meses/Niver que deseja filtrar os funcionários
    public void impNiverMes(String... strings){
        
        for (String mes : strings) {
            if (mesesMap.containsKey(mes.toLowerCase())) {
            meses.add(mesesMap.get(mes.toLowerCase()));
            } else {
            throw new IllegalArgumentException("Mês inválido: " + mes);
            } 
        }
        
        String titulo = "Funcionários Aniversariantes dos meses: ";

        for (int mes : meses) {
            String nomeMes = getKeyFromValue(mesesMap, mes);
            titulo += nomeMes + ", ";
        }
        
        System.out.println("\n"+titulo);
        System.out.println("---------------------------------------------------------------------------------------------------------");
        for (Funcionario funcionario : listaFuncionarios) {
            int mesAniversario = funcionario.getDtNacimento().getMonthValue();
            for (int mes : meses) {
                if (mesAniversario == mes) {
                    System.out.println(funcionario.toString());
                    break;
                }
            }
        }         
        meses = new ArrayList<>();
    }
    
    //Função para realizar uma busca inversa no MAP e retornar a chave
    private static <K, V> K getKeyFromValue(Map<K, V> map, V value) {
        Optional<K> chaveEncontrada = map.entrySet().stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst();
        return chaveEncontrada.orElse(null); // Retorna null se o valor não for encontrado
    }
    
    //Imprime a tabela de funcionários
    public void impFuncionarios(String titulo){
        
        System.out.println("\n" + titulo);
        System.out.println("------------");
        
        listaFuncionarios.forEach(System.out::println);
            
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
    
    //Imprimi o nome e idade do funcionário mais velho por meio da utilizzação de uma função stream() na lsita de funcionários,
    //com o emprego da função max() que realiza uma comparação nos elementos da lsita de funcionarios.
    //Essa comparação tem o emprego da função getIdade() da classe Funcionário que devolve o calculo de idade, calculando a data
    //de nascimento do funcionário com a data atual. 
    public void impFuncionarioMaisVelho(){
        
        Funcionario maisVelho = listaFuncionarios.stream()
                .max((f1, f2) -> Integer.compare(f1.getIdade(), f2.getIdade()))
                .orElse(null);

        if (maisVelho != null) {
            System.out.println("\nFuncionário com a maior idade:");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("Nome: " + maisVelho.getNome());
            System.out.println("Idade: " + maisVelho.getIdade());
        } else {
            System.out.println("Nenhum funcionário encontrado.");
        }
    }
    
    //Função ordena a lista em ordem alfabética por meio do emprego da função sort() aplicado à lista de funcionários e utilizando a
    //biblioteca Comparator do Java esse faz uso da função comparing onde é passado a classe Funcionário e por meio da referência ao método getNome() (::) da instancia
    //funcionário.
    public void impListaEmOrdemAlfabetica(){        
                
        listaFuncionarios.sort(Comparator.comparing(Funcionario::getNome));
        
        impFuncionarios("Lista de Funcionários em Ordem Alfabética:");
    }
    
    //Função realiza um stream() na listaFuncionários e usando .map pegando pelo salário de cada funcionário é chamado a função .reduce
    //inicializando o somatório em zero e somando cada sealario. Logo o somatório é atribuido a variável totalSalario, função formatValores
    //realiza a formatação do total que é finalmente impresso na tela. 
    public void impTotalSalario(){
        
        BigDecimal totalSalarios = listaFuncionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
       
        System.out.println("\nTotal dos Salários dos Funcionários: ");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println(formatValores(totalSalarios));
    }
    
    //Função recebe uma string por parâmetro, realiza um parse para BigDecimal, percorre a lista de funcionários dividindo pelo salário mínimo informado e
    //realiza a impresssão de quantos salários mínimo cada funcionário recebe
    public void impQtdSalariosMinimos(String minimo){        
        
        BigDecimal salarioMinimo = new BigDecimal(minimo);
        
        System.out.println("\nQtd de Salários Mínimos que Cada Funcionário Recebe: ");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Salário Mínimo Informado: " + formatValores(salarioMinimo));
        System.out.println("---------------------------------------------------------------------------------------------------------");

        
        for (Funcionario funcionario : listaFuncionarios) {
            BigDecimal quantidadeSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println("\n" + funcionario.getNome() + " ganha " + quantidadeSalariosMinimos + " salários mínimos.");
                    System.out.println("-------------------------------------------------");

        }
    }
    
    
    
    
    
    
    

    
}
