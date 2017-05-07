package ligamx.carga;

/**
 *
 * @author hector.lopez
 */
public abstract class BaseLecturaDTO extends BaseArchivoDTO {

    protected String[] header;
    protected String[] mapper;

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public String[] getMapper() {
        return mapper;
    }

    public void setMapper(String[] mapper) {
        this.mapper = mapper;
    }

    public void setNumLinea(Integer numLinea) {
        this.numLinea = numLinea;
    }

}
