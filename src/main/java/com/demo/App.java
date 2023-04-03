package com.demo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Collection;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static java.time.temporal.ChronoUnit.MINUTES;

public class App {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int choice = 0;

        while (choice != 3) {
            System.out.println("(1) Validar CEP");
            System.out.println("(2) Pesquisar CEP");
            System.out.println("(3) Encerrar consulta");
            System.out.print("Opção: ");
            choice = getUserChoice(3);
            handleChoice(choice);
        }

    }

    public static int getUserChoice(int choiceOptions) {
        int choice = 0;
        try {
            choice = in.nextInt();
            if (!(choice >= 1 && choice <= choiceOptions)) {
                throw new IllegalArgumentException();
            }
            in.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Por favor, informe uma opção válida.");
            in.nextLine();
        }
        return choice;
    }

    public static void handleChoice(int choice) {
        switch (choice) {
            case 1:
                valCEP();
                break;
            case 2:
                searchCEP();
                break;
            case 3:
                choice = 3;
                break;
        }
    }

    public static void valCEP() {
        System.out.print("Digite o CEP a ser pesquisado:");
        String cep = in.nextLine();
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        try {
            HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.of(1, MINUTES)).build();
            HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() == 400) {
                System.out.println("Formato do CEP inválido. Por favor, tente novamente.");
                valCEP();
            } else if (httpResponse.body().contains("\"erro\": true")) {
                System.out.println("Cep inexistente. Por favor, tente novamente.");
                valCEP();
            } else {
                Gson gson = new Gson();
                Cep cepObj = gson.fromJson(httpResponse.body(), Cep.class);
                System.out.println(cepObj.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    public static void searchCEP() {
        System.out.print("Digite o estado: ");
        String estado = in.nextLine();
        estado = estado.replaceAll(" ", "%20");
        System.out.print("Digite a cidade: ");
        String cidade = in.nextLine();
        cidade = cidade.replaceAll(" ", "%20");
        System.out.print("Digite o logradouro: ");
        String logradouro = in.nextLine();
        logradouro = logradouro.replaceAll(" ", "%20");

        String url = "https://viacep.com.br/ws/" + estado + "/" + cidade + "/" + logradouro + "/json/";
        try {
            Gson gson = new Gson();
            HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.of(1, MINUTES)).build();
            HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            java.lang.reflect.Type collectionType = new TypeToken<Collection<Cep>>() {
            }.getType();
            Collection<Cep> ceps = gson.fromJson(httpResponse.body(), collectionType);

            if (httpResponse.statusCode() == 400) {
                System.out.println("Formato das informações inválido. Por favor, tente novamente.");
                searchCEP();
            } else if (httpResponse.body().contains("\"erro\": true")) {
                System.out.println("Nenhum CEP encontrado. Por favor, tente novamente.");
                searchCEP();
            } else {
                for (Cep cep : ceps) {
                    System.out.println(cep.toString());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
