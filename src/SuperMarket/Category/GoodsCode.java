package SuperMarket.Category;

import java.io.Serializable;

public class GoodsCode implements Serializable {
    private String code;
    private String content;

    public GoodsCode(String code, String content) {
        this.code = code;
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CategoryCode{" +
                "code='" + code + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
