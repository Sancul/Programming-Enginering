package com.tests.assignments.teamwork;

import com.tests.assignments.teamwork.entities.Movie;
import com.tests.assignments.teamwork.entities.Rating;
import com.tests.assignments.teamwork.sort.Quicksort;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static final Path dataSource = Path.of("src\\com\\tests\\assignments\\teamwork\\data\\data.csv");
    public static final List<Movie> movies = new ArrayList<>();

    public static Comparator<Movie> idComparator = Comparator.comparingLong(Movie::id);
    public static Comparator<Movie> titleComparator = Comparator.comparing(Movie::title);

    public static Comparator<Movie> kpComparator = Comparator.comparingDouble(m -> m.rating().kp());
    public static Comparator<Movie> imdbComparator = Comparator.comparingDouble(m -> m.rating().imdb());

    public static Comparator<Movie> filmCriticsComparator = Comparator.comparingDouble(m -> m.rating().filmCritics());
    public static Comparator<Movie> russianFilmCriticsComparator = Comparator.comparingDouble(m -> m.rating().russianFilmCritics());

    public static Comparator<Movie> awaitComparator = Comparator.comparingDouble(m -> m.rating().await());


    public static void main(String[] args) {

        parse();

        Quicksort<Movie> movieQuicksort = new Quicksort<>(kpComparator.reversed());

        Movie[] moviesArray = movies.toArray(new Movie[0]);
        movieQuicksort.quicksort(moviesArray, 0, moviesArray.length - 1);

        Arrays.stream(moviesArray).forEach(movie -> System.out.println(movie.rating().kp() + "\t" + movie.title()));
    }

    public static void parse() {
        try (BufferedReader reader = Files.newBufferedReader(dataSource)) {
            reader.readLine();
            reader.lines().forEach(s -> {
                String[] fields = s.split("\\|");
                Rating rating = new Rating(Double.parseDouble(fields[0]),
                        Double.parseDouble(fields[1]),
                        Double.parseDouble(fields[2]),
                        Double.parseDouble(fields[3]),
                        Double.parseDouble(fields[4]));
                movies.add(new Movie(Long.parseLong(fields[5]), fields[6], rating));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}