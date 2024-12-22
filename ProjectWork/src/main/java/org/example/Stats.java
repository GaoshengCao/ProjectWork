package org.example;
import java.math.BigDecimal;
public class Stats {

    BigDecimal winrate;
    BigDecimal pickrate;
    BigDecimal banrate;
    String tier;

    public void setWinrate(String winrate){ this.winrate = new BigDecimal(winrate); }
    public void setPickrate(String pickrate){ this.pickrate = new BigDecimal(pickrate) ; }
    public void setBanrate(String banrate){ this.banrate = new BigDecimal(banrate) ; }
    public void setTier(String tier){ this.tier = tier; }

    public BigDecimal getWinrate() { return winrate; }
    public BigDecimal getPickrate() { return pickrate; }
    public BigDecimal getBanrate() { return banrate; }
    public String getTier() { return tier; }
}
