package web.app.alupotato.service;

import org.springframework.stereotype.Service;
import web.app.alupotato.dao.GenericDAO;
import web.app.alupotato.entity.Setting;

/**
 *
 * @author Ram Chaulagai <ram.chaulagai.np@gmail.com>
 */
@Service
public class SettingServiceImp extends GenericServiceImp<GenericDAO<Setting, Long>, Setting, Long>
        implements GenericService<Setting, Long> {

}
