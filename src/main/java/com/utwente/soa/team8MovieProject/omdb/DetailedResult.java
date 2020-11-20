package com.utwente.soa.team8MovieProject.omdb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="root")
public class DetailedResult {

    @XmlAttribute
    private String response;

    @XmlElement(name="movie")
    private MovieDetails movieDetails;

    public MovieDetails details() {
        return movieDetails;
    }

    public static class MovieDetails {

        @XmlAttribute
        private String title;

        @XmlAttribute
        private String year;

        @XmlAttribute
        private String imdbID;

        @XmlAttribute
        private String plot;

        public String getTitle() {
            return title;
        }

        public String getYear() {
            return year;
        }

        public String getImdbID() {
            return imdbID;
        }

        public String getPlot() { return plot; }
    }
}
