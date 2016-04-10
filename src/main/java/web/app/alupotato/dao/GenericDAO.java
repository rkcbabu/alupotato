package web.app.alupotato.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<Entity, ID extends Serializable> {

    public Entity get(ID id);

    public List<Entity> getAll();

    public void create(Entity entity);

    public void update(Entity entity);

    public Entity merge(Entity entity);

    public void delete(Entity entity);

    void deleteById(final ID entityId);

}
