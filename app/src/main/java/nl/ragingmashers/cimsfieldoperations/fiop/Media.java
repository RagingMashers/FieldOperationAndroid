package nl.ragingmashers.cimsfieldoperations.fiop;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("org.jsonschema2pojo")
public class Media {

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
    @SerializedName("suggestion")
    @Expose
    private String suggestion;
    @SerializedName("importance")
    @Expose
    private Integer importance;
    @SerializedName("category")
    @Expose
    private String category;

    /**
     * No args constructor for use in serialization
     *
     */
    public Media() {
    }

    /**
     *
     * @param id
     * @param category
     * @param mimetype
     * @param importance
     * @param source
     * @param suggestion
     * @param url
     */
    public Media(Integer id, String mimetype, String url, String source, String suggestion, Integer importance, String category) {
        this.id = id;
        this.mimetype = mimetype;
        this.url = url;
        this.source = source;
        this.suggestion = suggestion;
        this.importance = importance;
        this.category = category;
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
     * The suggestion
     */
    public String getSuggestion() {
        return suggestion;
    }

    /**
     *
     * @param suggestion
     * The suggestion
     */
    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public Media withSuggestion(String suggestion) {
        this.suggestion = suggestion;
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

    /**
     *
     * @return
     * The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    public Media withCategory(String category) {
        this.category = category;
        return this;
    }

}