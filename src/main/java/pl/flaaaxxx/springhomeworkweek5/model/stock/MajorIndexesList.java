package pl.flaaaxxx.springhomeworkweek5.model.stock;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ticker",
    "changes",
    "price",
    "indexName"
})
public class MajorIndexesList {

    @JsonProperty("ticker")
    private String ticker;
    @JsonProperty("changes")
    private Double changes;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("indexName")
    private String indexName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ticker")
    public String getTicker() {
        return ticker;
    }

    @JsonProperty("ticker")
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @JsonProperty("changes")
    public Double getChanges() {
        return changes;
    }

    @JsonProperty("changes")
    public void setChanges(Double changes) {
        this.changes = changes;
    }

    @JsonProperty("price")
    public Double getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Double price) {
        this.price = price;
    }

    @JsonProperty("indexName")
    public String getIndexName() {
        return indexName;
    }

    @JsonProperty("indexName")
    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "MajorIndexesList{" +
                "ticker='" + ticker + '\'' +
                ", changes=" + changes +
                ", price=" + price +
                ", indexName='" + indexName + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
