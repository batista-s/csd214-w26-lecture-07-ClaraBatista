package csd214.bookstore.pojos;

import csd214.bookstore.entities.MusicCollectionEntity;

import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

/**
 * DTO for {@link MusicCollectionEntity}
 */
public abstract class MusicCollection extends Product {
    private String title;
    private String artist;
    private String genre;
    private int year;

    public MusicCollection() {
    }

    public MusicCollection(String title, String artist, String genre, int year, double price, String name) {
        super(UUID.randomUUID().toString(), name,price);
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public void edit(Scanner input) {
        System.out.println("Edit Title [" + this.title + "]:");
        setTitle(getInput(input, this.title));
        System.out.println("Edit Artist [" + this.artist + "]:");
        setArtist(getInput(input, this.artist));
        System.out.println("Edit Genre [" + this.genre + "]:");
        setGenre(getInput(input, this.genre));
        System.out.println("Edit Year [" + this.year + "]:");
        setYear(getInput(input, this.year));
        super.edit(input);
    }

    @Override
    public void initialize(Scanner input) {
        super.setProductId(UUID.randomUUID().toString());
        System.out.println("Enter Title:");
        setTitle(getInput(input, "Generic"));
        System.out.println("Enter Artist:");
        setArtist(getInput(input, "Generic"));
        System.out.println("Enter Genre:");
        setGenre(getInput(input, "Generic"));
        System.out.println("Enter Year:");
        setYear(getInput(input, 0));
        super.initialize(input);
    }

    @Override
    public String toString() {
        return "MusicCollection{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MusicCollection that)) return false;
        return year == that.year && Objects.equals(title, that.title) && Objects.equals(artist, that.artist) && Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist, genre, year);
    }
}