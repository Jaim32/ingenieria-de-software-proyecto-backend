package org.example.lifesyncbackend.Repository;

import org.example.lifesyncbackend.Exceptions.ModelNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface iGenericRepository<T, ID> extends JpaRepository<T, ID> {
    default public T findByIdOrThrows(ID id) throws Exception {
        return findById(id).orElseThrow(() -> new ModelNotFoundException(getClassName() + "not found"));
    }

    private String getClassName() {
        return this.getClass().
                getGenericInterfaces()[0].
                getTypeName().
                replace("org.example.lifesyncbackend.Repository.iGenericRepository<", "").replace(">", "");
    }
}