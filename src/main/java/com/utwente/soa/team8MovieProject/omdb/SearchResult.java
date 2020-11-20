package com.utwente.soa.team8MovieProject.omdb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="root")
public class SearchResult {

    @XmlAttribute
    private String response;

    @XmlAttribute
    private Integer totalResults;

    @XmlElement(name = "result")
    private List<Item> results = new ArrayList<>();

    public List<Item> getResults() {
        return results;
    }

    public static class Item {
        @XmlAttribute
        private String title;

        @XmlAttribute
        private String year;

        @XmlAttribute
        private String imdbID;

        @XmlAttribute
        private String type;

        @XmlAttribute
        private String poster;

        public String getTitle() {
            return title;
        }

        public String getYear() {
            return year;
        }

        public String getImdbID() {
            return imdbID;
        }

        public String getType() {
            return type;
        }

        public String getPoster() {
            return poster;
        }

    }
}
