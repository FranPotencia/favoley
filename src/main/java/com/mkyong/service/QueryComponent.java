package com.mkyong.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class QueryComponent {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	public void initJdbcTemplate() {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public String getPartidosJugadosByEquipo(String equipo) {
		String sql = "SELECT id FROM PARTIDO WHERE (EQUIPO_LOCAL='" + equipo + "' or EQUIPO_VISITANTE='" + equipo
				+ "') " + "and (EQUIPO_LOCAL<>'Descansa' and EQUIPO_VISITANTE<>'Descansa') and resultado is not null";
		List<Map<String, Object>> row = jdbcTemplate.queryForList(sql);
		if (!CollectionUtils.isEmpty(row)) {
			return row.size() + "";
		} else {
			return "0";
		}
	}

	public String getPartidosGanadosByEquipo(String equipo) {
		String sql = "SELECT id FROM partido WHERE (EQUIPO_LOCAL='" + equipo + "' or EQUIPO_VISITANTE='" + equipo
				+ "') \r\n"
				+ "and (EQUIPO_LOCAL<>'Descansa' and EQUIPO_VISITANTE<>'Descansa') and resultado is not null and "
				+ "(EQUIPO_LOCAL='" + equipo + "' and resultado like '3%') or (EQUIPO_VISITANTE='" + equipo
				+ "' and resultado  like '%3');";
		List<Map<String, Object>> row = jdbcTemplate.queryForList(sql);
		if (!CollectionUtils.isEmpty(row)) {
			return row.size() + "";
		} else {
			return "0";
		}
	}

	public String getPartidosPerdidosByEquipo(String equipo) {
		String sql = "SELECT id FROM partido WHERE (EQUIPO_LOCAL='" + equipo + "' or EQUIPO_VISITANTE='" + equipo
				+ "') \r\n"
				+ "and (EQUIPO_LOCAL<>'Descansa' and EQUIPO_VISITANTE<>'Descansa') and resultado is not null and "
				+ "(EQUIPO_LOCAL='" + equipo + "' and resultado like '%3') or (EQUIPO_VISITANTE='" + equipo
				+ "' and resultado  like '3%');";
		List<Map<String, Object>> row = jdbcTemplate.queryForList(sql);
		if (!CollectionUtils.isEmpty(row)) {
			return row.size() + "";
		} else {
			return "0";
		}
	}

	public String getTanteosFavorLocalByEquipo(String equipo) {
		String sql = "SELECT SET1, SET2, SET3, SET4, SET5 FROM partido WHERE (EQUIPO_LOCAL='" + equipo
				+ "' and EQUIPO_VISITANTE<>'Descansa' ) and resultado is not null";

		List<Map<String, Object>> row = jdbcTemplate.queryForList(sql);
		if (!CollectionUtils.isEmpty(row)) {
			Long resultado=0L;
			for (Map<String, Object> map : row) {
				if(map.get("SET1")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET1").toString().split("/")[0]);
				}
				if(map.get("SET2")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET2").toString().split("/")[0]);
				}
				if(map.get("SET3")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET3").toString().split("/")[0]);
				}
				if(map.get("SET4")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET4").toString().split("/")[0]);
				}
				if(map.get("SET5")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET5").toString().split("/")[0]);
				}
			}
			return resultado + "";
		} else {
			return "0";
		}
	}

	public String getTanteosFavorVisitanteByEquipo(String equipo) {
		String sql = "SELECT SET1, SET2, SET3, SET4, SET5 FROM partido WHERE (EQUIPO_VISITANTE='" + equipo
				+ "' and EQUIPO_LOCAL<>'Descansa' ) and resultado is not null";

		List<Map<String, Object>> row = jdbcTemplate.queryForList(sql);
		if (!CollectionUtils.isEmpty(row)) {
			Long resultado=0L;
			for (Map<String, Object> map : row) {
				if(map.get("SET1")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET1").toString().split("/")[1]);
				}
				if(map.get("SET2")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET2").toString().split("/")[1]);
				}
				if(map.get("SET3")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET3").toString().split("/")[1]);
				}
				if(map.get("SET4")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET4").toString().split("/")[1]);
				}
				if(map.get("SET5")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET5").toString().split("/")[1]);
				}
			}
			return resultado + "";
		} else {
			return "0";
		}
	}
	
	public String getTanteosContraLocalByEquipo(String equipo) {
		String sql = "SELECT SET1, SET2, SET3, SET4, SET5 FROM partido WHERE (EQUIPO_LOCAL='" + equipo
				+ "' and EQUIPO_VISITANTE<>'Descansa' ) and resultado is not null";

		List<Map<String, Object>> row = jdbcTemplate.queryForList(sql);
		if (!CollectionUtils.isEmpty(row)) {
			Long resultado=0L;
			for (Map<String, Object> map : row) {
				if(map.get("SET1")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET1").toString().split("/")[1]);
				}
				if(map.get("SET2")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET2").toString().split("/")[1]);
				}
				if(map.get("SET3")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET3").toString().split("/")[1]);
				}
				if(map.get("SET4")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET4").toString().split("/")[1]);
				}
				if(map.get("SET5")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET5").toString().split("/")[1]);
				}
			}
			return resultado + "";
		} else {
			return "0";
		}
	}

	public String getTanteosContraVisitanteByEquipo(String equipo) {
		String sql = "SELECT SET1, SET2, SET3, SET4, SET5 FROM partido WHERE (EQUIPO_VISITANTE='" + equipo
				+ "' and EQUIPO_LOCAL<>'Descansa' ) and resultado is not null";

		List<Map<String, Object>> row = jdbcTemplate.queryForList(sql);
		if (!CollectionUtils.isEmpty(row)) {
			Long resultado=0L;
			for (Map<String, Object> map : row) {
				if(map.get("SET1")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET1").toString().split("/")[0]);
				}
				if(map.get("SET2")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET2").toString().split("/")[0]);
				}
				if(map.get("SET3")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET3").toString().split("/")[0]);
				}
				if(map.get("SET4")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET4").toString().split("/")[0]);
				}
				if(map.get("SET5")!=null) {
					resultado=resultado+Long.parseLong(map.get("SET5").toString().split("/")[0]);
				}
			}
			return resultado + "";
		} else {
			return "0";
		}
	}
}
