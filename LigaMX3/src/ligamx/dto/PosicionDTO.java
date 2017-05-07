package ligamx.dto;

import java.io.Serializable;
import ligamx.util.BaseDTO;

/**
 *
 * @author hector.lopez
 */
public class PosicionDTO extends BaseDTO implements Serializable, Comparable<PosicionDTO> {

    private Integer jj;

    private Integer jg;

    private Integer je;
    
    private Integer jp;

    private Integer gf;

    private Integer gc;

    private Integer dg;

    private Integer pts;

    public Integer getJj() {
        return jj;
    }

    public void setJj(Integer jj) {
        this.jj = jj;
    }

    public Integer getJg() {
        return jg;
    }

    public void setJg(Integer jg) {
        this.jg = jg;
    }

    public Integer getJe() {
        return je;
    }

    public void setJe(Integer je) {
        this.je = je;
    }

    public Integer getJp() {
        return jp;
    }

    public void setJp(Integer jp) {
        this.jp = jp;
    }

    public Integer getGf() {
        return gf;
    }

    public void setGf(Integer gf) {
        this.gf = gf;
    }

    public Integer getGc() {
        return gc;
    }

    public void setGc(Integer gc) {
        this.gc = gc;
    }

    public Integer getDg() {
        return dg;
    }

    public void setDg(Integer dg) {
        this.dg = dg;
    }

    public Integer getPts() {
        return pts;
    }

    public void setPts(Integer pts) {
        this.pts = pts;
    }

    @Override
    public int compareTo(PosicionDTO o) {
        if(this.getPts().equals(o.getPts())) {
            if(this.getDg().equals(o.getDg())) {
                return o.getGf().compareTo(this.getGf());
            } else {
                return o.getDg().compareTo(this.getDg());
            }
        } else {
            return o.getPts().compareTo(this.getPts());
        }
    }
    
}
