package ligamx.carga;

/**
 *
 * @author hector.lopez
 */
public class CocienteLecturaDTO extends BaseLecturaDTO {

    private String ja15;

    private String jc16;

    private String ja16;

    private String jc17;

    private String ja17;

    private String jc18;

    private String tj;

    private String pa15;

    private String pc16;

    private String pa16;

    private String pc17;

    private String pa17;

    private String pc18;

    private String tp;

    private String dga15;

    private String dgc16;

    private String dga16;

    private String dgc17;

    private String dga17;

    private String dgc18;

    private String tdg;

    private String cociente;
    
    private String idEquipo;

    public CocienteLecturaDTO() {
        super.setHeader(new String[]{"IDEQUIPO", "JA15", "JC16", "JA16", "JC17", "JA17", "JC18", "TJ", "PA15", "DGA15",
            "PC16", "DGC16", "PA16", "DGA16", "PC17", "DGC17", "PA17", "DGA17", "PC18", "DGC18", "TP", "TDG", "COCIENTE"});
        super.setMapper(new String[]{"idEquipo", "ja15", "jc16", "ja16", "jc17", "ja17", "jc18", "tj", "pa15", "pc16", "pa16",
            "pc17", "pa17", "pc18", "tp", "dga15", "dgc16", "dga16", "dgc17", "dga17", "dgc18", "tdg", "cociente"});
    }

    public String getJa15() {
        return ja15;
    }

    public void setJa15(String ja15) {
        this.ja15 = ja15;
    }

    public String getJc16() {
        return jc16;
    }

    public void setJc16(String jc16) {
        this.jc16 = jc16;
    }

    public String getJa16() {
        return ja16;
    }

    public void setJa16(String ja16) {
        this.ja16 = ja16;
    }

    public String getJc17() {
        return jc17;
    }

    public void setJc17(String jc17) {
        this.jc17 = jc17;
    }

    public String getJa17() {
        return ja17;
    }

    public void setJa17(String ja17) {
        this.ja17 = ja17;
    }

    public String getJc18() {
        return jc18;
    }

    public void setJc18(String jc18) {
        this.jc18 = jc18;
    }

    public String getTj() {
        return tj;
    }

    public void setTj(String tj) {
        this.tj = tj;
    }

    public String getPa15() {
        return pa15;
    }

    public void setPa15(String pa15) {
        this.pa15 = pa15;
    }

    public String getPc16() {
        return pc16;
    }

    public void setPc16(String pc16) {
        this.pc16 = pc16;
    }

    public String getPa16() {
        return pa16;
    }

    public void setPa16(String pa16) {
        this.pa16 = pa16;
    }

    public String getPc17() {
        return pc17;
    }

    public void setPc17(String pc17) {
        this.pc17 = pc17;
    }

    public String getPa17() {
        return pa17;
    }

    public void setPa17(String pa17) {
        this.pa17 = pa17;
    }

    public String getPc18() {
        return pc18;
    }

    public void setPc18(String pc18) {
        this.pc18 = pc18;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getDga15() {
        return dga15;
    }

    public void setDga15(String dga15) {
        this.dga15 = dga15;
    }

    public String getDgc16() {
        return dgc16;
    }

    public void setDgc16(String dgc16) {
        this.dgc16 = dgc16;
    }

    public String getDga16() {
        return dga16;
    }

    public void setDga16(String dga16) {
        this.dga16 = dga16;
    }

    public String getDgc17() {
        return dgc17;
    }

    public void setDgc17(String dgc17) {
        this.dgc17 = dgc17;
    }

    public String getDga17() {
        return dga17;
    }

    public void setDga17(String dga17) {
        this.dga17 = dga17;
    }

    public String getDgc18() {
        return dgc18;
    }

    public void setDgc18(String dgc18) {
        this.dgc18 = dgc18;
    }

    public String getTdg() {
        return tdg;
    }

    public void setTdg(String tdg) {
        this.tdg = tdg;
    }

    public String getCociente() {
        return cociente;
    }

    public void setCociente(String cociente) {
        this.cociente = cociente;
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

}
