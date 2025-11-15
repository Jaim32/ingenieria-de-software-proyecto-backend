package org.example.lifesyncbackend.Util;

public final class Constants {

    private Constants() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    //* Ruta base de api
    public static final String API = "/api";
    public static final String INGREDIENTE_CONTROLLER = "/ingrediente";
    public static final String USUARIO_CONTROLLER = "/usuarios";
    public static final String STATUS_CONTROLLER = "/status";
    public static final String PLATILLO_CONTROLLER = "/platillo";
    public static final String RECETA_CONTROLLER = "/receta";
    public static final String AUTH_CONTROLLER = "/auth";
    //* Metodos comunes
    public static final String CREATE = "/create";
    public static final String GET_ALL = "/getAll";
    public static final String GET_BY_ID = "/getById";

    public static final String DELETE = "/delete";

    public static final String UPDATE = "/update";

    //* Metodos para Usuario
    //public static final String UPDATE_FIRSTNAME = "/update";
}
