package pl.flaaaxxx.springhomeworkweek5.model.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "majorIndexesList"
})
public class StockInfo {

    @JsonProperty("majorIndexesList")
    private List<MajorIndexesList> majorIndexesList = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("majorIndexesList")
    public List<MajorIndexesList> getMajorIndexesList() {
        return majorIndexesList;
    }

    @JsonProperty("majorIndexesList")
    public void setMajorIndexesList(List<MajorIndexesList> majorIndexesList) {
        this.majorIndexesList = majorIndexesList;
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
        return "StockInfo{" +
                "majorIndexesList=" + majorIndexesList +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
