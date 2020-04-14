package fr.olaqin.pfd.web.mapper;

import java.util.List;

public interface ApiObjectMapper<T_API, T_MODEL> {
    T_MODEL toModel(T_API api);

    List<T_MODEL> toModel(List<T_API> apis);

    T_API toApi(T_MODEL model);

    List<T_API> toApi(List<T_MODEL> models);
}
