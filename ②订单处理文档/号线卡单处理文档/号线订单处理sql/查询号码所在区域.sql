SELECT a.all_line_nbr, s.name
  FROM logical_dn a, office_code oo, service_area s
 WHERE a.office_id = oo.id
   and oo.service_area_id = s.id0
   and a.all_line_nbr = 65168866
