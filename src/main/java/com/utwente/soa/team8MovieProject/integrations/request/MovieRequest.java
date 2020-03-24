package com.utwente.soa.team8MovieProject.integrations.request;


import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "movieIdmbID",
})
@XmlRootElement(name = "MovieRequest")
public class MovieRequest {

    @XmlElement(required = true)
    private String movieIdmbID;

    public String getMovieIdmbID() {
        return movieIdmbID;
    }

    public void setMovieIdmbID(String movieIdmbID) {
        this.movieIdmbID = movieIdmbID;
    }
}
