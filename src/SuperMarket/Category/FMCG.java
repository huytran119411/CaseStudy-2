package SuperMarket.Category;

import java.io.Serializable;
import java.time.LocalDate;

public class FMCG extends Category implements Serializable {
    private LocalDate expirationDate;

    public FMCG(String name, LocalDate expirationDate) {
        super(name);
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public FMCG(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Hạn Sử Dụng=" + expirationDate +
                "} ";
    }
}
