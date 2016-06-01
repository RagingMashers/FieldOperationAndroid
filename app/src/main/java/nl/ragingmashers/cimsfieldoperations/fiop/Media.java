package nl.ragingmashers.cimsfieldoperations.fiop;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

//@Generated("org.jsonschema2pojo")
public class Media {

    /*id": 8,
"mimetype": "image/jpeg",
"url": "http://localhost:8080/MediaDownload.ashx?id=8",
"source": "sms",
"importance": 1,
"date": "2016-04-13T00:00:00"*/

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("mimetype")
    @Expose
    private String mimetype;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("importance")
    @Expose
    private Integer importance;
    @SerializedName("date")
    @Expose
    private Date date;

    /**
     * No args constructor for use in serialization
     *
     */
    public Media() {
    }

    /**
     *  @param id
     * @param mimetype
     * @param url
     * @param source
     * @param importance
     * @param date
     */
    public Media(Integer id, String mimetype, String url, String source, Integer importance, Date date) {
        this.id = id;
        this.mimetype = mimetype;
        this.url = url;
        this.source = source;
        this.importance = importance;
        this.date = date;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public Media withId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @return
     * The mimetype
     */
    public String getMimetype() {
        return mimetype;
    }

    /**
     *
     * @param mimetype
     * The mimetype
     */
    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public Media withMimetype(String mimetype) {
        this.mimetype = mimetype;
        return this;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public Media withUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     *
     * @return
     * The source
     */
    public String getSource() {
        return source;
    }

    /**
     *
     * @param source
     * The source
     */
    public void setSource(String source) {
        this.source = source;
    }

    public Media withSource(String source) {
        this.source = source;
        return this;
    }

    /**
     *
     * @return
     * The importance
     */
    public Integer getImportance() {
        return importance;
    }

    /**
     *
     * @param importance
     * The importance
     */
    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public Media withImportance(Integer importance) {
        this.importance = importance;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}