package com.rest.movie;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

public class Client {
    private String baseURL = "http://localHost:8080";
    private Scanner input;
    private RestTemplate restTemplate = new RestTemplate();
    @PersistenceContext
    private EntityManager entMan;


    public static void main(String[] args)
    {
        Client cl = new Client();
        cl.input = new Scanner(System.in);
        boolean again = true;
        do
        {
            int choice = cl.getChoice();
            switch(choice)
            {
                case 1:
                {
                    cl.getMovie();
                    break;
                }
                case 2:
                {
                    cl.createMovie();
                    break;
                }
                case 3:
                {
                    cl.updateMovie();
                    break;
                }
                case 4:
                {
                    cl.deleteMovie();
                    break;
                }
                case 5:{
                    cl.getMovies();
                    break;
                }
                default:
                {
                    again = false;
                    break;
                }
            }

        }while(again);

        cl.input.close();
    }

    public void getMovie() {
        try {
            int id = getID("retrieve");
            String url = baseURL + "/movies/" + id;

            Movie movie = restTemplate.getForObject(url, Movie.class);
            System.out.println(movie.toString());
        }
        catch(HttpClientErrorException hce) {
            System.out.println(hce.getMessage());
        }
    }

    public void getMovies() {
        try {
            String url = baseURL + "/movies/";

            Movie[] movies = restTemplate.getForObject(url, Movie[].class);
//            Array a = movies;
            for(Movie movie : movies) {
                System.out.println(movie.toString());
            }
        }
        catch(HttpClientErrorException hce) {
            System.out.println(hce.getMessage());
        }
    }
    public void createMovie()
    {
        String url = baseURL + "/movies";
        try {
            Movie movie = restTemplate.postForObject(url, new Movie(0,"New Movie"), Movie.class);
            System.out.println(movie.toString());
        }
        catch(HttpClientErrorException hce) {
            System.out.println(hce.getMessage());
        }
    }

    public void updateMovie()
    {
        try {
            Movie movieToSend = new Movie();
            int id = getID("update");
            movieToSend.setId(id);
            movieToSend.setTitle("Updated Movie");
            String url = baseURL + "/movies/" + id;
            restTemplate.put(url, movieToSend, Movie.class);
            Movie movie = restTemplate.getForObject(url, Movie.class);
            System.out.println(movie.toString());
        }
        catch(HttpClientErrorException hce) {
            System.out.println(hce.getMessage());
        }

    }

    public void deleteMovie()
    {
        try
        {
            int id = getID("delete");
            String url = baseURL + "/movies/{id}";
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", id+"");
            restTemplate.delete(url,params);
            System.out.println("ID " + id + " deleted.");
        }
        catch(HttpClientErrorException hce) {
            System.out.println(hce.getMessage());
        }
    }

    private int getChoice()
    {

        int choice = 0;
        do
        {
            try
            {
                System.out.println("1.  Run a GET request");
                System.out.println("2.  Run a POST request");
                System.out.println("3.  Run a PUT request");
                System.out.println("4.  Run a DELETE request");
                System.out.println("5.  Run a GET for the list");
                System.out.println("6. Exit");
                System.out.println("\nPlease enter your choice.");
                choice = input.nextInt();
            }
            catch (InputMismatchException ime)
            {
                System.out.println("You must enter an integer between 1 and 5");
            }
            finally
            {
                input.nextLine();
            }
        } while (choice < 1 || choice > 6);
        return choice;
    }

    private int getID(String action)
    {
        boolean valid = false;
        int id = 0;
        do
        {
            try
            {
                System.out.println("Please enter the ID of the Movie to " + action);
                id = input.nextInt();
                valid = true;
            }
            catch(InputMismatchException ime)
            {
                System.out.println("You must enter an integer for the id");
            }
            finally
            {
                input.nextLine();
            }
        }while (!valid);
        return id;
    }
}

