package ligamx.dto;

import java.io.Serializable;
import ligamx.util.BaseDTO;

/**
 *
 * @author hector.lopez
 */
public class CocienteDTO extends BaseDTO implements Serializable, Comparable<CocienteDTO> {

    private Integer ja14;

    private Integer jc15;

    private Integer ja15;

    private Integer jc16;

    private Integer ja16;

    private Integer jc17;

    private Integer tj;

    private Integer pa14;

    private Integer pc15;

    private Integer pa15;

    private Integer pc16;

    private Integer pa16;

    private Integer pc17;

    private Integer tp;

    private Integer dga14;

    private Integer dgc15;

    private Integer dga15;

    private Integer dgc16;

    private Integer dga16;

    private Integer dgc17;

    private Integer tdg;

    private Double cociente;

    public Integer getJa14() {
        return ja14;
    }

    public void setJa14(Integer ja14) {
        this.ja14 = ja14;
    }

    public Integer getJc15() {
        return jc15;
    }

    public void setJc15(Integer jc15) {
        this.jc15 = jc15;
    }

    public Integer getJa15() {
        return ja15;
    }

    public void setJa15(Integer ja15) {
        this.ja15 = ja15;
    }

    public Integer getJc16() {
        return jc16;
    }

    public void setJc16(Integer jc16) {
        this.jc16 = jc16;
    }

    public Integer getJa16() {
        return ja16;
    }

    public void setJa16(Integer ja16) {
        this.ja16 = ja16;
    }

    public Integer getJc17() {
        return jc17;
    }

    public void setJc17(Integer jc17) {
        this.jc17 = jc17;
    }

    public Integer getTj() {
        return tj;
    }

    public void setTj(Integer tj) {
        this.tj = tj;
    }

    public Integer getPa14() {
        return pa14;
    }

    public void setPa14(Integer pa14) {
        this.pa14 = pa14;
    }

    public Integer getPc15() {
        return pc15;
    }

    public void setPc15(Integer pc15) {
        this.pc15 = pc15;
    }

    public Integer getPa15() {
        return pa15;
    }

    public void setPa15(Integer pa15) {
        this.pa15 = pa15;
    }

    public Integer getPc16() {
        return pc16;
    }

    public void setPc16(Integer pc16) {
        this.pc16 = pc16;
    }

    public Integer getPa16() {
        return pa16;
    }

    public void setPa16(Integer pa16) {
        this.pa16 = pa16;
    }

    public Integer getPc17() {
        return pc17;
    }

    public void setPc17(Integer pc17) {
        this.pc17 = pc17;
    }

    public Integer getTp() {
        return tp;
    }

    public void setTp(Integer tp) {
        this.tp = tp;
    }

    public Double getCociente() {
        return cociente;
    }

    public void setCociente(Double cociente) {
        this.cociente = cociente;
    }

    public Integer getDga14() {
        return dga14;
    }

    public void setDga14(Integer dga14) {
        this.dga14 = dga14;
    }

    public Integer getDgc15() {
        return dgc15;
    }

    public void setDgc15(Integer dgc15) {
        this.dgc15 = dgc15;
    }

    public Integer getDga15() {
        return dga15;
    }

    public void setDga15(Integer dga15) {
        this.dga15 = dga15;
    }

    public Integer getDgc16() {
        return dgc16;
    }

    public void setDgc16(Integer dgc16) {
        this.dgc16 = dgc16;
    }

    public Integer getDga16() {
        return dga16;
    }

    public void setDga16(Integer dga16) {
        this.dga16 = dga16;
    }

    public Integer getDgc17() {
        return dgc17;
    }

    public void setDgc17(Integer dgc17) {
        this.dgc17 = dgc17;
    }

    public Integer getTdg() {
        return tdg;
    }

    public void setTdg(Integer tdg) {
        this.tdg = tdg;
    }

    @Override
    public int compareTo(CocienteDTO o) {
        if (this.getCociente().equals(o.getCociente())) {
            return o.getTdg().compareTo(this.getTdg());
        } else {
            return o.getCociente().compareTo(this.getCociente());
        }
    }

}
