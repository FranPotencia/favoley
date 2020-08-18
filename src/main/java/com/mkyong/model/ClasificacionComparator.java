package com.mkyong.model;

import java.util.Comparator;

import org.apache.commons.lang3.builder.CompareToBuilder;

public class ClasificacionComparator implements Comparator<EquipoClasificacion> {
	 
    @Override
    public int compare(EquipoClasificacion o1, EquipoClasificacion o2) {
        return new CompareToBuilder()
                .append(o2.getPuntos(),o1.getPuntos())
                .append(Integer.parseInt(o2.getPartidosGanados()), Integer.parseInt(o1.getPartidosGanados()))
                .append(Integer.parseInt(o2.getTanteosFavor())-Integer.parseInt(o2.getTanteosContra()), 
                		Integer.parseInt(o1.getTanteosFavor())-Integer.parseInt(o1.getTanteosContra())).toComparison();
    }
}