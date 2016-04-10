/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.app.alupotato.dao;

import org.springframework.stereotype.Repository;
import web.app.alupotato.entity.Setting;

/**
 *
 * @author Ram Chaulagai <ram.chaulagai.np@gmail.com>
 */
@Repository
public class SettingDaoImp extends GenericDaoImpl<Setting, Long>
        implements GenericDAO<Setting, Long> {

}
