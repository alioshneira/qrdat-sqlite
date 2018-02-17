/*
 * Copyright (C) 2018 Aliosh Neira <aliosh2006 at gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pe.alinet.barcodelist;

import java.time.Instant;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pe.alinet.usuarios.Usuario;

/**
 *
 * @author Aliosh Neira <aliosh2006 at gmail.com>
 */
public class BarcodeListService {

    public static void add(Usuario usuario){
        
        BarcodeList bar = new BarcodeList(usuario);
        persist(bar);
    }
    
    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("QRdatPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    
}
