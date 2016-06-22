package nl.ragingmashers.cimsfieldoperations.fiop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

//@Generated("org.jsonschema2pojo")
public class Message implements Serializable{



    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("team")
    @Expose
    private Integer team;
    @SerializedName("direction")
    @Expose
    private String direction;
    @SerializedName("media")
    @Expose
    private List<Media> media;

    /**
     * No args constructor for use in serialization
     *
     */
    public Message() {
    }

    /**
     *
     * @param message
     * @param title
     * @param direction
     * @param team
     */
    public Message(String message, String title, Integer team, String direction) {
        this.message = message;
        this.title = title;
        this.team = team;
        this.direction = direction;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public Message withMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public Message withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     *
     * @return
     * The team
     */
    public Integer getTeam() {
        return team;
    }

    /**
     *
     * @param team
     * The team
     */
    public void setTeam(Integer team) {
        this.team = team;
    }

    public Message withTeam(Integer team) {
        this.team = team;
        return this;
    }

    /**
     *
     * @return
     * The direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     *
     * @param direction
     * The direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Message withDirection(String direction) {
        this.direction = direction;
        return this;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }
}