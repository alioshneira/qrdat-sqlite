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
package pe.alinet.util;

import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Aliosh Neira <aliosh2006 at gmail.com>
 */
public class DateTableCellRenderer extends DefaultTableCellRenderer{
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy   HH:mm:ss");
    public DateTableCellRenderer() { super(); }

    @Override
    public void setValue(Object value) {
        
        setText((value == null) ? "" : formatter.format(value));
        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    }   
    
}
