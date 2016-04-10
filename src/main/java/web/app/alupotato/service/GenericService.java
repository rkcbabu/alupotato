package web.app.alupotato.service;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Ram Chaulagai <ram.chaulagai.np@gmail.com>
 * @param <Entity>
 * @param <ID>
 */
public interface GenericService<Entity, ID extends Serializable> {

    public List<Entity> getAll();

    public Entity get(ID id);

    public void create(Entity entity);

    public void update(Entity entity);

    public Entity merge(Entity entity);

    public void delete(Entity entity);

    void deleteById(final ID entityId);
}
