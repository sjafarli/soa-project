package com.utwente.soa.voting.integrations.request;


import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "movieIdmbID",
        "name"
})
@XmlRootElement(name = "MovieRequest")
public class MovieXmlRequest {

    @XmlElement(required = true)
    private String movieIdmbID;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovieIdmbID() {
        return movieIdmbID;
    }

    public void setMovieIdmbID(String movieIdmbID) {
        this.movieIdmbID = movieIdmbID;
    }
}