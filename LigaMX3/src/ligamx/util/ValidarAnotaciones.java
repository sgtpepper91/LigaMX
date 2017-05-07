package ligamx.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hector.lopez
 * @param <T>
 */
public class ValidarAnotaciones<T extends BaseLecturaDTO> {

    public void validarAnotaciones(List<T> source) throws ExcepcionAplicacion {
        for (T elem : source) {
            if (agregarMensajeNotNull(elem)) {
                if (agregarMensajeSize(elem)) {
                    agregarMensajeType(elem);
                }
            }
            agregarMensajeAssertTrue(elem);
        }
    }

    private boolean agregarMensajeNotNull(T source) throws ExcepcionAplicacion {
        Field[] campos = source.getClass().getDeclaredFields();
        for (Field campo : campos) {
            NotNullAndEmpty anotacion = campo.getAnnotation(NotNullAndEmpty.class);
            if (null != anotacion) {
                campo.setAccessible(true);
                String valor = obtenerValor(campo, source);
                if (null == valor || valor.isEmpty()) {
                    source.getMensajes().add(anotacion.mensaje());
                    return false;
                }
            }
        }
        return true;
    }

    private boolean agregarMensajeSize(T source) throws ExcepcionAplicacion {
        Field[] campos = source.getClass().getDeclaredFields();
        for (Field campo : campos) {
            Size anotacion = campo.getAnnotation(Size.class);
            if (null != anotacion) {
                campo.setAccessible(true);
                String valor = obtenerValor(campo, source);
                if (valor.length() > anotacion.size()) {
                    source.getMensajes().add(anotacion.mensaje());
                    return false;
                }
            }
        }
        return true;
    }

    private void agregarMensajeAssertTrue(T source) throws ExcepcionAplicacion {
        Method[] metodos = source.getClass().getDeclaredMethods();
        for (Method metodo : metodos) {
            AssertTrue anotacion = metodo.getAnnotation(AssertTrue.class);
            if (null != anotacion) {
                metodo.setAccessible(true);
                if (!obtenerValor(metodo, source)) {
                    source.getMensajes().add(anotacion.mensaje());
                }
            }
        }
    }

    private String obtenerValor(Field campo, T source) throws ExcepcionAplicacion {
        try {
            String nombre = "get" + campo.getName().substring(0, 1).toUpperCase() + campo.getName().substring(1);
            Method getMethod = source.getClass().getMethod(nombre);
            return (String) invocarMetodo(getMethod, source);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(ValidarAnotaciones.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcepcionAplicacion(ex.getMessage());
        }
    }

    private Boolean obtenerValor(Method metodo, T source) throws ExcepcionAplicacion {
        try {
            return (Boolean) metodo.invoke(source);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ValidarAnotaciones.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcepcionAplicacion(ex.getMessage());
        }
    }

    private boolean agregarMensajeType(T source) throws ExcepcionAplicacion {
        Field[] campos = source.getClass().getDeclaredFields();
        for (Field campo : campos) {
            Type anotacion = campo.getAnnotation(Type.class);
            if (null != anotacion) {
                campo.setAccessible(true);
                String valor = obtenerValor(campo, source);
                String tipo = anotacion.tipo();
                switch (tipo) {
                    case "String":
                        return true;
                    case "Integer":
                        try {
                            if (Integer.parseInt(valor) <= 0) {
                                source.getMensajes().add(anotacion.mensaje());
                                return false;
                            }
                        } catch (Exception e) {
                            source.getMensajes().add(anotacion.mensaje());
                            return false;
                        }
                    case "Double":
                        try {
                            Double.parseDouble(valor);
                        } catch (Exception e) {
                            source.getMensajes().add(anotacion.mensaje());
                            return false;
                        }
                }

                return false;
            }
        }
        return true;
    }

    private Object invocarMetodo(Method method, T source) throws ExcepcionAplicacion {
        try {
            return method.invoke(source);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ValidarAnotaciones.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcepcionAplicacion(ex.getMessage());
        }
    }
}
